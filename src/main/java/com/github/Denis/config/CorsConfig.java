package com.github.Denis.config;

import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Configure to allow frontend go to ports of backend
@Configuration
public class CorsConfig implements WebMvcConfigurer {
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry
            .addMapping("/api/**") // Разрешаем все запросы к API
            .allowedOrigins("http://localhost:3000") // Разрешаем фронту
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешенные методы
            .allowedHeaders("*"); // Разрешаем любые заголовки
      }
    };
  }

  /** Перенаправление на Swagger UI при доступе к корню приложения. Просто удобно. */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
  }
}
