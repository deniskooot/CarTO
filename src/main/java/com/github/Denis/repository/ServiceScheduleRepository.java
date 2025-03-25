package com.github.Denis.repository;

import com.github.Denis.entity.ServiceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceScheduleRepository extends JpaRepository<ServiceSchedule, Integer> {

//    В Spring Data JPA можно просто объявить метод в интерфейсе, и он автоматически сгенерирует SQL-запрос на основе имени метода.
//    Optional<ServiceSchedule> — метод вернёт объект ServiceSchedule, если он найден, или Optional.empty(), если нет.
//findByName(String name) — Spring Data JPA сам сгенерирует SQL-запрос:
//    SELECT * FROM service_schedule WHERE name = ? LIMIT 1;
    Optional<ServiceSchedule> findByNameIgnoreCase(String name);
}
