package com.github.Denis.controller;

import com.github.Denis.dto.SchedulePerspectiveType;
import com.github.Denis.dto.ScheduledTaskForCar;
import com.github.Denis.dto.ServiceScheduleItemDTO;
import com.github.Denis.entity.Car;
import com.github.Denis.entity.ServiceScheduleItem;
import com.github.Denis.repository.CarRepository;
import com.github.Denis.repository.ServiceScheduleItemRepository;
import com.github.Denis.service.ServiceScheduleItemService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/*
@RestController = @Controller + @ResponseBody. Аннотация @Controller умеет слушать, получать и отвечать на запросы.
@ResponseBody дает фреймворку понять, что объект, который вы вернули из метода надо прогнать через HttpMessageConverter,
чтобы получить готовое к отправке клиенту представление.
*/
@RestController
@RequestMapping(
    "/api") // @RequestMapping говорит, по какому URL будут доступны наши контроллеры - базовый путь
// для всех запросов в этом контроллере
public class CarController {

  private final ServiceScheduleItemRepository serviceScheduleItemRepository;
  private final CarRepository carRepository;
  private final ServiceScheduleItemService serviceScheduleItemService;

  @Autowired
  public CarController(
      CarRepository carRepository,
      ServiceScheduleItemService serviceScheduleItemService,
      ServiceScheduleItemRepository serviceScheduleItemRepository) {
    this.carRepository = carRepository;
    this.serviceScheduleItemRepository = serviceScheduleItemRepository;
    this.serviceScheduleItemService = serviceScheduleItemService;
  }

  @GetMapping(
      "/cars") // @GetMapping - сообщает SpringBoot, что это get метод и он умеет что-то возвращать.
  public List<Car> getCar() {
    return carRepository.findAll();
  }

  // @PathVariable - подставляет переменную пути - комментарий к функции
  @GetMapping("/cars/{id}")
  public Car getCarByID(@PathVariable int id) {
    return carRepository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
  }

  /**
   * Предназначен для создания новой сущности Car
   *
   * @param car сущность, которую необходимо сохранить в базе
   * @return id созданной сущности Car
   */
  @PostMapping("/cars")
  @Transactional
  public int saveNewCar(@RequestBody @Valid Car car) {
    // @Valid - валидация Car
    car = carRepository.save(car);
    return car.getId();
  }

  @GetMapping("/cars/{carId}/serviceScheduleItems")
  public List<ServiceScheduleItem> getServiceScheduleListForHistoryForm(@PathVariable int carId) {
    return serviceScheduleItemRepository.findAllByCarId(carId);
  }

  /**
   * Удаление сущности Car
   *
   * @param id сущности
   */
  @DeleteMapping("/cars/{id}")
  public void deleteCar(@PathVariable int id) {
    carRepository.deleteById(id);
  }

  // Create / Update
  @PostMapping("/serviceSchedules/{scheduleId}/items")
  @Transactional
  public int saveNewServiceScheduleItem(
      @RequestBody @Valid ServiceScheduleItemDTO serviceScheduleItemDTO) {
    return serviceScheduleItemService.saveCarToServiceSchedule(serviceScheduleItemDTO);
  }

  // Delete
  @DeleteMapping("/serviceSchedules/{scheduleId}/items/{itemId}")
  public void deleteServiceScheduleItem(@PathVariable int itemId) {
    serviceScheduleItemRepository.deleteById(itemId);
  }

  /**
   * Get a task list for the main page
   *
   * @param carId id of the selected car
   * @param perspectiveType perspective parameter selection flag (years or mileage)
   * @param perspectiveLimit value parameter using
   * @param show_required is show non-required tasks (recommended tasks)?
   * @return List of tasks
   */
  @GetMapping("/cars/{carId}/tasks/by/{perspectiveType}/{perspectiveLimit}")
  public List<ScheduledTaskForCar> getTasks(
      @PathVariable int carId,
      @PathVariable SchedulePerspectiveType perspectiveType,
      @PathVariable
          @Positive(message = "limit should be positive")
          @Max(value = 1_000_000L, message = "limit should be less than million")
          int perspectiveLimit,
      @RequestParam(defaultValue = "false") boolean show_required) {
    return serviceScheduleItemService.getTaskList(
        carId, perspectiveType, perspectiveLimit, show_required);
  }
}
