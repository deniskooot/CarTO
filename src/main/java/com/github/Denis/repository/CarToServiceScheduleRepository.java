package com.github.Denis.repository;

import com.github.Denis.entity.CarToServiceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarToServiceScheduleRepository extends JpaRepository<CarToServiceSchedule, Integer> {
    List<CarToServiceSchedule> findAllByCarId(Integer carId);
}
