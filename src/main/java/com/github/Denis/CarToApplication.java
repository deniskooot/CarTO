package com.github.Denis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
