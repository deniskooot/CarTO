package com.github.Denis.service;

import com.github.Denis.dto.CarToServiceScheduleDTO;
import com.github.Denis.dto.ScheduleListDTO;
import com.github.Denis.entity.CarToServiceSchedule;
import com.github.Denis.entity.Part;
import com.github.Denis.entity.ServiceSchedule;
import com.github.Denis.mapper.CarToServiceScheduleMapper;
import com.github.Denis.repository.CarToServiceScheduleRepository;
import com.github.Denis.repository.ServiceScheduleRepository;
import com.github.Denis.utils.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class CarToServiceScheduleService {

    private final CarToServiceScheduleRepository carToServiceScheduleRepository;
    private final CarToServiceScheduleMapper carToServiceScheduleMapper;
    private final ServiceScheduleRepository serviceScheduleRepository;

    @Autowired
    public CarToServiceScheduleService(CarToServiceScheduleRepository repository, CarToServiceScheduleMapper carToServiceScheduleMapper, ServiceScheduleRepository serviceScheduleRepository) {
        this.carToServiceScheduleRepository = repository;
        this.carToServiceScheduleMapper = carToServiceScheduleMapper;
        this.serviceScheduleRepository = serviceScheduleRepository;
    }

    // Read
    @Transactional
    public List<CarToServiceSchedule> getCarToServiceSchedule() {
        return carToServiceScheduleRepository.findAll();
    }

    // Read by id
    @Transactional
    public CarToServiceSchedule getCarToServiceScheduleByID(int id) {
        return carToServiceScheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
    }

    @Transactional
    public void deleteCarToServiceSchedule(int id) {
        carToServiceScheduleRepository.deleteById(id);
    }

    @Transactional
    public int saveCarToServiceSchedule(CarToServiceScheduleDTO dto) {
    // получили DTO со строкой dto.getServiceScheduleName();
        int serviceScheduleId;
        // спросили есть ли в базе запись с такой строкой
        Optional<ServiceSchedule> findName = serviceScheduleRepository.findByNameIgnoreCase(dto.getServiceScheduleName().trim());

        if (findName.isPresent()) {
            // если такая работа в базе есть, получаем id
            serviceScheduleId = findName.get().getId();
        } else {
            // если такой работы в базе нет, создаем новую запись
            ServiceSchedule newServiceSchedule = new ServiceSchedule();
            // Приводим первый символ строки в верхний регистр
            newServiceSchedule.setName(StringUtils.normalizeServiceScheduleName(dto.getServiceScheduleName().trim()));
            newServiceSchedule.setDefaultPeriodKm(dto.getPeriodicityKm());
            newServiceSchedule.setDefaultPeriodTimeDays(Duration.ofDays(dto.getPeriodicityTimeDays()));
            newServiceSchedule.setRequired(dto.getIsRequired());

            // сохраняем новую работу в базу и получаем id
            serviceScheduleId = serviceScheduleRepository.save(newServiceSchedule).getId();
        }

        // берем работу по ее id
        ServiceSchedule serviceSchedule = serviceScheduleRepository.getReferenceById(serviceScheduleId);

        CarToServiceSchedule entity = carToServiceScheduleMapper.toEntity(dto);
        //сохраняем в CarToServiceSchedule entity сущность ServiceSchedule serviceSchedule
        entity.setServiceSchedule(serviceSchedule);
        return carToServiceScheduleRepository.save(entity).getId();
    }

    // Get for list of works to schedule history page on front (by selected car)
    @Transactional
    public Map<Integer, String> getServiceScheduleListForHistoryForm(@PathVariable int car_id) {
        Map<Integer, String> result = new HashMap<>();
        // carToServiceScheduleRepository.findAll();
        List<CarToServiceSchedule> carToServiceScheduleList = carToServiceScheduleRepository.findAllByCarId(car_id);

        // проверка на null заставляет hibernate впервые обратиться к связи и ее подгрузить. После чего, при следующем обращении lazy связь уже подгружена
        for (CarToServiceSchedule entity : carToServiceScheduleList) {
            if (entity.getServiceSchedule() != null) {
                result.put(entity.getId(), entity.getServiceSchedule().getName());

                // Если name ещё не встречался — добавляем
                // nameToIdMap.putIfAbsent(name, id);
            }
        }
        return result;
    }

    // Get tasks (schedules) list for main page (forward by selected car, mileage and time, with show required/ non required parameter) type Mode = "km" | "years";
    @Transactional
    public List<ScheduleListDTO> getTaskList(int car_id, String schedule_perspective_mileage_or_year, int schedule_perspective_value, boolean show_required){

//        получили список работ по car_id (пока что без учета флага обязательности, просто все работы по машине)
        List<CarToServiceSchedule> carToServiceScheduleList = carToServiceScheduleRepository.findAllByCarId(car_id);
//carToServiceScheduleList - все работы по car_id из CarToServiceSchedule
        if (carToServiceScheduleList.isEmpty()) {
            throw new EntityNotFoundException("Нет расписаний для машины с id: " + car_id);
        }
//tasksList - список задач, возвращаемых во фронт     private String scheduleName;
//    Integer scheduleMileageKm; ZonedDateTime scheduleDate; String scheduleNotes; Boolean scheduleIsRequired; List<Part> scheduleParts;
        List<ScheduleListDTO> tasksList = new ArrayList<>();
        for (CarToServiceSchedule carToServiceSchedule: carToServiceScheduleList){


            Integer startMileage = carToServiceSchedule.getCar().getStartMileage(); //стартовый пробег

            ZonedDateTime startDate = carToServiceSchedule.getCar().getStartDate(); //стартовая дата

            Integer yearlyMileage = carToServiceSchedule.getCar().getYearlyMileage(); //годовой пробег

            Integer car_mileage = carToServiceSchedule.getCar().getMileage(); //текущий пробег

            ZonedDateTime current_date = ZonedDateTime.now(); //текущая дата

            String name = carToServiceSchedule.getServiceSchedule().getName(); //наименование работы

            String notes = carToServiceSchedule.getNotes(); //заметки

            Boolean isRequired = carToServiceSchedule.getServiceSchedule().isRequired(); //обязательность

            List<Part> parts = carToServiceSchedule.getParts(); //список запчастей


            Integer periodicityKm = carToServiceSchedule.getPeriodicityKm();//периодичность работы по пробегу

            Integer periodicityDefault = carToServiceSchedule.getServiceSchedule().getDefaultPeriodKm();//периодичность работы по пробегу по умолчанию

            Duration periodicityDays = carToServiceSchedule.getPeriodicityTimeDays();//периодичность работы по дате

            Duration periodicityDaysDefault = carToServiceSchedule.getServiceSchedule().getDefaultPeriodTimeDays(); //периодичность работы по дате по умолчанию

            //schedule_perspective_mileage_or_year - в чем перспектива
            //schedule_perspective_value - значение перспективы

            Integer mileage = car_mileage; //пробег для выполнения работы

//            ZonedDateTime date = ZonedDateTime.parse("2025-01-01T00:00:00+03:00"); //дата выполнения работы

            ZonedDateTime date = current_date; //дата выполнения работы


            if(schedule_perspective_value < 0 && schedule_perspective_value > 1_000_000){
                //TODO: add validation
            }
            if(schedule_perspective_mileage_or_year.equals("km")){
                while(mileage < schedule_perspective_value){

                    tasksList.add(new ScheduleListDTO(name, mileage, date, notes, isRequired, parts));
                    mileage += periodicityKm;
                    date.plusDays(periodicityDays.toDays());

                    //TODO: рассмотреть случай, когда нет даты
                }
            }

//            if(schedule_perspective_mileage_or_year.equals("years")){}




//            tasksList.add(new ScheduleListDTO(name, mileage, date, notes, isRequired, parts));
        }
//        for (CarToServiceSchedule carToServiceSchedule: carToServiceScheduleList){
//            tasksList.add(putScheduleData(carToServiceSchedule));
//        }
        Comparator<ScheduleListDTO> scheduleListDTOComparator = Comparator
                .comparing(ScheduleListDTO::getScheduleMileageKm)
                .thenComparing(ScheduleListDTO::getScheduleDate);
//        tasksList.sort(scheduleListDTOComparator);
//        tasksList.sort(scheduleListDTOComparator);
        Collections.sort(tasksList, scheduleListDTOComparator);
//        List.sort(scheduleListDTOComparator);
        return tasksList;


    }

//    public ScheduleListDTO setOneSchedule(Integer startMileage, ZonedDateTime startDate, Integer yearlyMileage,
//                                          Integer car_mileage, String name, Integer mileage, ZonedDateTime date, String notes,
//                                          Boolean isRequired, List<Part> parts){
//        return new ScheduleListDTO(name, mileage, date, notes, isRequired, parts);
//    }
    //javaDOC
//    /**
//     *
//     * @param carToServiceSchedule
//     * @return
//     */
//    метод для создания одной строки в работе
//    public ScheduleListDTO putScheduleData(CarToServiceSchedule carToServiceSchedule){
//        //        создаем и наполняем DTO для одной работы по обслуживанию
//        ScheduleListDTO result = new ScheduleListDTO();
//        //Наименование работы //private String scheduleName;
//        result.setScheduleName(carToServiceSchedule.getServiceSchedule().getName());
//        // Пробег //private Integer scheduleMileageKm;
////        пока просто беру данные и выдаю за результат, тут логика добавления пробега должна быть
//        result.setScheduleMileageKm(carToServiceSchedule.getPeriodicityKm());
//        //    Дата //private Date scheduleDate;
//        ZonedDateTime startDate = ZonedDateTime.parse("2025-01-01T00:00:00+03:00");
//        result.setScheduleDate(startDate.plusDays(carToServiceSchedule.getPeriodicityTimeDays().toDays()));
////    Примечание //private String scheduleNotes;
//        result.setScheduleNotes(carToServiceSchedule.getNotes());
////    * - обязательна ли работа? //private Boolean scheduleIsRequired;
//        result.setScheduleIsRequired(carToServiceSchedule.getServiceSchedule().isRequired());
////    private List<Part> scheduleParts;
//        result.setScheduleParts(carToServiceSchedule.getParts());
//        return result;
//    }
//
}


//    @Table(name = "car_to_service_schedules")

//    @Column(name = "car_to_service_schedule_id")
//    private int id;
//    @JsonProperty("periodicity_km")
//    private int periodicityKm;
//    private Duration periodicityTimeDays;
//    private String notes;
//
//    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
//    private Car car;
//
//    @JoinColumn(name = "service_schedule_id", referencedColumnName = "service_schedule_id")
//    private ServiceSchedule serviceSchedule;
//
//    @OneToMany(mappedBy = "carToServiceSchedule", cascade = CascadeType.ALL, orphanRemoval = false)
//    private List<ServiceOperation> serviceOperations; //= new ArrayList<>();
//
//    @OneToMany(mappedBy = "carToServiceSchedule", cascade = CascadeType.ALL, orphanRemoval = false)
//    private List<Part> parts; // = new ArrayList<>();
