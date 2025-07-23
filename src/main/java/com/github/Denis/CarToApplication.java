package com.github.Denis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.github.Denis.*") // .repository.*
@EntityScan(basePackages = "com.github.Denis.entity") // нужен если репозиторий находится в другом пакете
@SpringBootApplication //Аннотация @SpringBootApplication эквивалентна использованию @Configuration, @EnableAutoConfiguration и @ComponentScan с их атрибутами по умолчанию
public class CarToApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarToApplication.class, args);
    }

}

