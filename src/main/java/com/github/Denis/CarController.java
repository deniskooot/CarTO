package com.github.Denis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

//контроллер - класс с аннотацией @RestController, который умеет что-то выводить на экран
//@RestController = @Controller + @ResponseBody. Аннотация @Controller умеет слушать, получать и отвечать на запросы.
//А @ResponseBody  дает фреймворку понять, что объект, который вы вернули из метода надо прогнать через HttpMessageConverter, чтобы получить готовое к отправке клиенту представление.

@RestController



//@RequestMapping("/api/v1/cars")


public class CarController {

    private CarRepository carRepo;
//    @Value("ннннн")
//    private String name;
//    public CarController(CarRepository carRepo) {
    public CarController() {
//        this.carRepo = carRepo;
    }

//    @GetMapping - сообщает SpringBoot, что это get метод и он умеет что-то возвращать.
    @GetMapping //(produces = APPLICATION_JSON_VALUE)
//    public String getName(){
    public List<Car> getAll(){
        return List.of();
//        return name;
//        return carRepo.findAll();

    }
}
