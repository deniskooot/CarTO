package com.github.Denis.service;

import com.github.Denis.dto.SchedulePerspectiveType;
import com.github.Denis.dto.ScheduledTaskForCar;
import com.github.Denis.dto.ServiceScheduleItemDTO;
import com.github.Denis.entity.ServiceSchedule;
import com.github.Denis.entity.ServiceScheduleItem;
import com.github.Denis.mapper.CarToServiceScheduleMapper;
import com.github.Denis.repository.ServiceScheduleItemRepository;
import com.github.Denis.repository.ServiceScheduleRepository;
import com.github.Denis.utils.StringUtils;
import jakarta.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceScheduleItemService {

  private final ServiceScheduleItemRepository serviceScheduleItemRepository;
  private final CarToServiceScheduleMapper carToServiceScheduleMapper;
  private final ServiceScheduleRepository serviceScheduleRepository;

  @Autowired
  public ServiceScheduleItemService(
      ServiceScheduleItemRepository repository,
      CarToServiceScheduleMapper carToServiceScheduleMapper,
      ServiceScheduleRepository serviceScheduleRepository) {
    this.serviceScheduleItemRepository = repository;
    this.carToServiceScheduleMapper = carToServiceScheduleMapper;
    this.serviceScheduleRepository = serviceScheduleRepository;
  }

  // Read
  @Transactional
  public List<ServiceScheduleItem> getCarToServiceSchedule() {
    return serviceScheduleItemRepository.findAll();
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
  public int saveCarToServiceSchedule(ServiceScheduleItemDTO dto) {
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

    ServiceScheduleItem entity = carToServiceScheduleMapper.toEntity(dto);
    // сохраняем в CarToServiceSchedule entity сущность ServiceSchedule serviceSchedule
    entity.setServiceSchedule(serviceSchedule);
    return serviceScheduleItemRepository.save(entity).getId();
  }

  /**
   * Method for get tasks (schedules) list for main page (forward by selected car, mileage and time,
   * with show required/ non required parameter) type Mode = "km" | "years";
   *
   * @param car_id id of selected car
   * @param schedule_perspective_mileage_or_year perspective parameter selection flag (years or
   *     mileage)
   * @param schedule_perspective_value value parameter using
   * @param show_required should show non-required tasks (recommended tasks)?
   * @return List of tasks
   */
  @Transactional
  public List<ScheduledTaskForCar> getTaskList(
      int car_id,
      SchedulePerspectiveType schedule_perspective_mileage_or_year,
      int schedule_perspective_value,
      boolean show_required) {

    // TODO: здесь используется текущая дата, однако на странице есть возможность поменять дату,
    // нужно учитывать дату со страницы, если она отличается от текущей
    // получили список работ по car_id (пока что без учета флага обязательности, просто все работы
    // по машине)
    List<ServiceScheduleItem> serviceScheduleItemList =
        serviceScheduleItemRepository.findAllByCarId(car_id);
    List<ScheduledTaskForCar> tasksList = new ArrayList<>();
    if (serviceScheduleItemList.isEmpty()) {
      return tasksList;
    }

    for (ServiceScheduleItem serviceScheduleItem : serviceScheduleItemList) {
      boolean isRequired = serviceScheduleItem.getServiceSchedule().isRequired(); // обязательность
      if (!show_required && !isRequired) {
        continue;
      }

      List<ScheduledTaskForCar> currentList =
          switch (schedule_perspective_mileage_or_year) {
            case km -> prepareTaskListForMileage(serviceScheduleItem, schedule_perspective_value);
            case years ->
                prepareTaskListForPeriodicity(serviceScheduleItem, schedule_perspective_value);
          };
      tasksList.addAll(currentList);
    }

    // Comparator to sort task list
    Comparator<ScheduledTaskForCar> scheduleListDTOComparator =
        Comparator.comparing(ScheduledTaskForCar::getScheduleMileageKm)
            .thenComparing(ScheduledTaskForCar::getScheduleDate);
    tasksList.sort(scheduleListDTOComparator);
    return tasksList;
  }

  private List<ScheduledTaskForCar> prepareTaskListForMileage(
      ServiceScheduleItem serviceScheduleItem, int schedule_perspective_value) {
    List<ScheduledTaskForCar> tasksList = new ArrayList<>();
    ZonedDateTime date = ZonedDateTime.now();
    long daysPeriodicity = serviceScheduleItem.getPeriodicity().toDays();

    int mileage = serviceScheduleItem.getCar().getMileage(); // пробег для выполнения работы
    while (mileage < schedule_perspective_value) {
      tasksList.add(new ScheduledTaskForCar(mileage, date, serviceScheduleItem));
      mileage += serviceScheduleItem.getPeriodicityKm();
      date = date.plusDays(daysPeriodicity);
    }
    return tasksList;
  }

  private List<ScheduledTaskForCar> prepareTaskListForPeriodicity(
      ServiceScheduleItem serviceScheduleItem, int schedule_perspective_value) {
    List<ScheduledTaskForCar> tasksList = new ArrayList<>();
    long daysPeriodicity = serviceScheduleItem.getPeriodicity().toDays();

    int mileage = serviceScheduleItem.getCar().getMileage();
    ZonedDateTime date = ZonedDateTime.now();
    final ZonedDateTime limit = date.plusYears(schedule_perspective_value);
    while (date.isBefore(limit)) {
      tasksList.add(new ScheduledTaskForCar(mileage, date, serviceScheduleItem));
      mileage += serviceScheduleItem.getPeriodicityKm();
      date = date.plusDays(daysPeriodicity);
    }
    return tasksList;
  }
}
