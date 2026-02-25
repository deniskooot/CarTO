package com.github.Denis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.github.Denis.*")
@EntityScan(basePackages = "com.github.Denis.entity")
@SpringBootApplication
public class CarToApplication {

    static void main(String[] args) {
        SpringApplication.run(CarToApplication.class, args);
    }
}

