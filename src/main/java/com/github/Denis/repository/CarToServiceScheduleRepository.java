package com.github.Denis.repository;

import com.github.Denis.entity.CarToServiceSchedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarToServiceScheduleRepository
    extends JpaRepository<CarToServiceSchedule, Integer> {
  List<CarToServiceSchedule> findAllByCarId(Integer carId);
}
