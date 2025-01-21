package com.github.Denis.controller;

import com.github.Denis.entity.Car;
import com.github.Denis.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//контроллер - класс с аннотацией @RestController, который умеет что-то выводить на экран
//@RestController = @Controller + @ResponseBody. Аннотация @Controller умеет слушать, получать и отвечать на запросы.
//А @ResponseBody  дает фреймворку понять, что объект, который вы вернули из метода надо прогнать через HttpMessageConverter, чтобы получить готовое к отправке клиенту представление.

@RestController

//@RequestMapping говорит, по какому URL будут доступны наши контроллеры.
// базовый путь для всех запросов в этом контроллере
@RequestMapping("/api")

public class CarController {

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
//@PathVariable - подставляет переменную пути
    @GetMapping("/cars/{id}")
    public Car getCarByID(@PathVariable int id) {
        return carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
    }

    @PostMapping("/cars")
    public int saveCar(@RequestBody Car car){
        car = carRepository.save(car);
        return car.getId();
    }
}
