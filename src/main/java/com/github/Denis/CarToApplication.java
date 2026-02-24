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

    /**
     * Class for launch application in IDE
     */
    @SpringBootApplication
    public static class LocalRun {
        static void main() {
            SpringApplication app = new SpringApplication(CarToApplication.class);
            app.setAdditionalProfiles("local");
            app.run();
        }
    }
}

