package com.github.Denis.controller;

//import com.github.Denis.entity.Cars;
//import com.github.Denis.repository.CarRepository;
import com.github.Denis.repository.CarUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; //.GetMapping .RequestMapping .RestController

import java.util.List;

//контроллер - класс с аннотацией @RestController, который умеет что-то выводить на экран
//@RestController = @Controller + @ResponseBody. Аннотация @Controller умеет слушать, получать и отвечать на запросы.
//А @ResponseBody  дает фреймворку понять, что объект, который вы вернули из метода надо прогнать через HttpMessageConverter, чтобы получить готовое к отправке клиенту представление.

@RestController

//@RequestMapping говорит, по какому URL будут доступны наши контроллеры.
// базовый путь для всех запросов в этом контроллере
@RequestMapping("/api") // /v1/cars работает //@RequestMapping("/api") - gpt предложил так
//@RequestMapping("/") //не работает

public class CarToController {

//    private final CarRepository carRepository;
    private final CarUserRepository carUserRepository;

    @Autowired
    public CarToController(CarUserRepository carUserRepository) {// CarRepository carRepository бот говорит передать аргумент в конструктор, но с ним не компилится
        //this.carRepository = carRepository;
        this.carUserRepository = carUserRepository;
        System.out.println("Controller created with repository: " + carUserRepository);
    }
    //Cannot invoke "com.github.Denis.repository.CarRepository.findAll()" because "this.carRepository" is null

//    @GetMapping - сообщает SpringBoot, что это get метод и он умеет что-то возвращать.
// APPLICATION_JSON_VALUE говорит о том, что данные возвращаются в формате json
    @GetMapping("/getcar")  //(produces = APPLICATION_JSON_VALUE) = "application/json"; //    @GetMapping("/hello")
//    public String getName(){

    //public Car getCar() {
    public String getCar() {
        //return new Car(001, "volvo", 001, 40_000, "there's some notes");
    return "Golf 6";
    }
//    @GetMapping("/getcars")

//    public List<Cars> getAllCars(){
//        return carRepository.findAll(); // выдает Whitelabel Error Page
////        return carRepository.findById(5);// выдает Whitelabel Error Page
////        return List.of(); // это выдает пустой список
//    }

//    @PostMapping
}
