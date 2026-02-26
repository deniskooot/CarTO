package com.github.Denis.controller;

import com.github.Denis.entity.Car;
import com.github.Denis.repository.CarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  @PersistenceContext private EntityManager entityManager;
  private final CarRepository carRepository;

  @Autowired
  public CarController(CarRepository carRepository) {
    this.carRepository = carRepository;
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

  @GetMapping("/get-car-list")
  public Map<Integer, String> getCarList(@RequestParam Integer userId) {
    HashMap<Integer, String> result = new HashMap<>();
    List<Car> cars = carRepository.getCarsByUserId(userId);
    for (Car car : cars) {
      if (car.getCarUser() != null) {
        result.put(car.getId(), car.getName());
      }
    }
    return result;
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

  /**
   * Удаление сущности Car
   *
   * @param id сущности
   */
  @DeleteMapping("/cars/{id}")
  public void deleteCar(@PathVariable int id) {
    carRepository.deleteById(id);
  }

  //    обновление имеющейся в базе сущности
  //    @PatchMapping("/cars/{id}")
  //    public void updateCarData(@PathVariable int id, Car car){
  //        carRepository.find
  //        Car newCar = getCarByID(id); //findByID
  //        carRepository.save(car);
  //    }

}
