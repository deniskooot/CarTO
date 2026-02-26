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
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CarToServiceScheduleService {

  private final CarToServiceScheduleRepository carToServiceScheduleRepository;
  private final CarToServiceScheduleMapper carToServiceScheduleMapper;
  private final ServiceScheduleRepository serviceScheduleRepository;

  @Autowired
  public CarToServiceScheduleService(
      CarToServiceScheduleRepository repository,
      CarToServiceScheduleMapper carToServiceScheduleMapper,
      ServiceScheduleRepository serviceScheduleRepository) {
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
    return carToServiceScheduleRepository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
  }

  // Delete by id
  @Transactional
  public void deleteCarToServiceSchedule(int id) {
    carToServiceScheduleRepository.deleteById(id);
  }

  /**
   * Method to save new CarToServiceSchedule to database. Method checks exist of ServiceSchedule
   * with name == dto.getServiceScheduleName(). If ServiceSchedule exist id returned, if don't id
   * new saved entity returned.
   *
   * @param dto CarToServiceScheduleDTO object
   * @return ID if ServiceSchedule dto.getServiceScheduleName() already exist in database or ID of
   *     new saved entity
   */
  @Transactional
  public int saveCarToServiceSchedule(CarToServiceScheduleDTO dto) {
    // получили DTO со строкой dto.getServiceScheduleName();
    int serviceScheduleId;
    // спросили есть ли в базе запись с такой строкой
    Optional<ServiceSchedule> findName =
        serviceScheduleRepository.findByNameIgnoreCase(dto.getServiceScheduleName().trim());

    if (findName.isPresent()) {
      // если такая работа в базе есть, получаем id
      serviceScheduleId = findName.get().getId();
    } else {
      // если такой работы в базе нет, создаем новую запись
      ServiceSchedule newServiceSchedule = new ServiceSchedule();
      // Приводим первый символ строки в верхний регистр
      newServiceSchedule.setName(
          StringUtils.normalizeServiceScheduleName(dto.getServiceScheduleName().trim()));
      newServiceSchedule.setDefaultPeriodKm(dto.getPeriodicityKm());
      newServiceSchedule.setDefaultPeriodTimeDays(dto.getPeriodicityTimeDays());
      newServiceSchedule.setRequired(dto.getIsRequired());
      // сохраняем новую работу в базу и получаем id
      serviceScheduleId = serviceScheduleRepository.save(newServiceSchedule).getId();
    }

    // берем работу по ее id
    ServiceSchedule serviceSchedule = serviceScheduleRepository.getReferenceById(serviceScheduleId);

    CarToServiceSchedule entity = carToServiceScheduleMapper.toEntity(dto);
    // сохраняем в CarToServiceSchedule entity сущность ServiceSchedule serviceSchedule
    entity.setServiceSchedule(serviceSchedule);
    return carToServiceScheduleRepository.save(entity).getId();
  }

  /**
   * Method to Get for list of works to schedule history page on frontend (by selected car)
   *
   * @param carId id of car to find schedules
   * @return Map of ID and name of service schedules
   */
  // Transactional
  public Map<Integer, String> getServiceScheduleListForHistoryForm(@PathVariable int carId) {
    Map<Integer, String> result = new HashMap<>();
    List<CarToServiceSchedule> carToServiceScheduleList =
        carToServiceScheduleRepository.findAllByCarId(carId);

    // Проверка на null заставляет hibernate впервые обратиться к связи и ее подгрузить. После чего,
    // при следующем обращении lazy связь уже подгружена
    for (CarToServiceSchedule entity : carToServiceScheduleList) {
      if (entity.getServiceSchedule() != null) {
        result.put(entity.getId(), entity.getServiceSchedule().getName());
      }
    }
    return result;
  }

  /**
   * Method for get tasks (schedules) list for main page (forward by selected car, mileage and time,
   * with show required/ non required parameter) type Mode = "km" | "years";
   *
   * @param car_id id of selected car
   * @param schedule_perspective_mileage_or_year perspective parameter selection flag (years or
   *     mileage)
   * @param schedule_perspective_value value parameter using
   * @param show_required is show non-required tasks (recommended tasks)?
   * @return List of tasks
   */
  @Transactional
  public List<ScheduleListDTO> getTaskList(
      int car_id,
      String schedule_perspective_mileage_or_year,
      int schedule_perspective_value,
      boolean show_required) {

    // TODO: здесь используется текущая дата, однако на странице есть возможность поменять дату,
    // нужно учитывать дату со страницы, если она отличается от текущей
    // получили список работ по car_id (пока что без учета флага обязательности, просто все работы
    // по машине)
    List<CarToServiceSchedule> carToServiceScheduleList =
        carToServiceScheduleRepository.findAllByCarId(car_id);
    if (carToServiceScheduleList.isEmpty()) {
      throw new EntityNotFoundException("Нет расписаний для машины с id: " + car_id);
    }
    List<ScheduleListDTO> tasksList = new ArrayList<>(); // список задач, возвращаемых во фронт
    for (CarToServiceSchedule carToServiceSchedule : carToServiceScheduleList) {

      Integer startMileage = carToServiceSchedule.getCar().getStartMileage(); // стартовый пробег
      ZonedDateTime startDate = carToServiceSchedule.getCar().getStartDate(); // стартовая дата
      Integer yearlyMileage = carToServiceSchedule.getCar().getYearlyMileage(); // годовой пробег
      Integer car_mileage = carToServiceSchedule.getCar().getMileage(); // текущий пробег
      ZonedDateTime current_date = ZonedDateTime.now(); // текущая дата
      String name = carToServiceSchedule.getServiceSchedule().getName(); // наименование работы
      String notes = carToServiceSchedule.getNotes(); // заметки
      Boolean isRequired = carToServiceSchedule.getServiceSchedule().isRequired(); // обязательность
      List<Part> parts = carToServiceSchedule.getParts(); // список запчастей
      Integer periodicityKm =
          carToServiceSchedule.getPeriodicityKm(); // периодичность работы по пробегу
      Integer periodicityDefault =
          carToServiceSchedule
              .getServiceSchedule()
              .getDefaultPeriodKm(); // периодичность работы по пробегу по умолчанию
      Duration periodicityDays =
          carToServiceSchedule.getPeriodicity(); // периодичность работы по дате
      // schedule_perspective_mileage_or_year - в чем перспектива
      // schedule_perspective_value - значение перспективы либо км либо количество лет
      Integer mileage = car_mileage; // пробег для выполнения работы
      ZonedDateTime date =
          current_date; // дата выполнения работы ZonedDateTime.parse("2025-01-01T00:00:00+03:00");

      if (!show_required && !isRequired) {
        continue;
      }

      if (schedule_perspective_value < 0 && schedule_perspective_value > 1_000_000) {
        throw new IllegalArgumentException(
            "Incorrect perspective parameter: "
                + schedule_perspective_value
                + " must be < 0 or > 1_000_000)");
      }
      // schedule list by km
      if (schedule_perspective_mileage_or_year.equals("km")) {
        while (mileage < schedule_perspective_value) {
          tasksList.add(new ScheduleListDTO(name, mileage, date, notes, isRequired, parts));
          mileage += periodicityKm;
          date = date.plusDays(periodicityDays.toDays());
        }
        // schedule list by years
      } else if (schedule_perspective_mileage_or_year.equals("years")) {
        while (date.isBefore(current_date.plusYears(schedule_perspective_value))) {
          tasksList.add(new ScheduleListDTO(name, mileage, date, notes, isRequired, parts));
          date = date.plusDays(periodicityDays.toDays());
          mileage += periodicityKm;
        }
      }
      // TODO: рассмотреть случай, когда нет даты и когда нет пробега
    }

    // Comparator to sort task list
    Comparator<ScheduleListDTO> scheduleListDTOComparator =
        Comparator.comparing(ScheduleListDTO::getScheduleMileageKm)
            .thenComparing(ScheduleListDTO::getScheduleDate);
    Collections.sort(tasksList, scheduleListDTOComparator);
    return tasksList;
  }
}
