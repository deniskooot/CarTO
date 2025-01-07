package com.github.Denis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//создании класса конфигурации и запуска приложения. Spring Boot поддерживает новую аннотацию @SpringBootApplication,
// которая эквивалентна использованию @Configuration, @EnableAutoConfiguration и @ComponentScan с их атрибутами по умолчанию

//Таким образом, вам просто нужно создать класс, аннотированный с помощью @SpringBootApplication, а Spring Boot включит
// автоматическую настройку и отсканирует ваши ресурсы в текущем пакете:
@SpringBootApplication
//@EntityScan("com.github.Denis.*")
@EnableJpaRepositories("com.github.Denis.*")

// Добавление строки запускает поиск в ресурсах файла index.html:
//@ComponentScan(basePackages = {"com.github.Denis.*"})




public class CarToApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarToApplication.class, args);
	}

}


// Для запуска приложения выполните: ./gradlew bootRun
// остановка ./gradlew -stop
//С помощью Postman или curl
//
//Отправьте GET-запрос на http://localhost:8080/api/v1/cars
