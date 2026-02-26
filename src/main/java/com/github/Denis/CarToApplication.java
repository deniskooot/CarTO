package com.github.Denis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class CarToApplication {

    static void main(String[] args) {
        SpringApplication.run(CarToApplication.class, args);
    }
}

