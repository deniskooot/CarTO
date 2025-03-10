package com.github.Denis.controller;

import com.github.Denis.entity.Car;
import com.github.Denis.repository.CarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//контроллер - класс с аннотацией @RestController, который умеет что-то выводить на экран
//@RestController = @Controller + @ResponseBody. Аннотация @Controller умеет слушать, получать и отвечать на запросы.
//А @ResponseBody  дает фреймворку понять, что объект, который вы вернули из метода надо прогнать через HttpMessageConverter, чтобы получить готовое к отправке клиенту представление.
@CrossOrigin(origins = "http://localhost:3000") // Разрешаем фронту
@RestController

//@RequestMapping говорит, по какому URL будут доступны наши контроллеры.
// базовый путь для всех запросов в этом контроллере
@RequestMapping("/api")

public class CarController {

    @PersistenceContext
    private EntityManager entityManager;

    private final CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

//    @GetMapping - сообщает SpringBoot, что это get метод и он умеет что-то возвращать.
// APPLICATION_JSON_VALUE говорит о том, что данные возвращаются в формате json
    @GetMapping("/cars")  //(produces = APPLICATION_JSON_VALUE) = "application/json"; //    @GetMapping("/hello")

    public List<Car> getCar() {
        return carRepository.findAll();
    }

////@PathVariable - подставляет переменную пути
    @GetMapping("/cars/{id}")
    public Car getCarByID(@PathVariable int id) {
        return carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
//    getReferenceById()
    }
////    Предназначен для создания новой сущности
    // post mappind не работает 2025-01-26T15:25:03.355+03:00 ERROR 78545 : Servlet.service() for servlet [dispatcherServlet]
// in context with path [] threw exception [Request processing failed: org.springframework.orm.ObjectOptimisticLockingFailureException:
// Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect): [com.github.Denis.entity.Car#4]] with root cause
    @PostMapping("/cars")
    @Transactional
//    @Valid - проверяет
    public int saveNewCar(@RequestBody @Valid Car car){

        car = carRepository.save(car);
        return car.getId();
    }
//       Удаление сущности

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable int id){
        carRepository.deleteById(id);
    }

    //обновление имеющейся в базе сущности
//    @PatchMapping("/cars/{id}")
//    public void updateCarData(@PathVariable int id, Car car){
//        carRepository.find
//        Car newCar = getCarByID(id); //findByID
//        carRepository.save(car);
//    }
//    PUT меняет объект целиком
//    PATCH изменяет отдельные поля ресурса.
}
