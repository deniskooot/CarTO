package com.github.Denis.repository;

import com.github.Denis.entity.ServiceScheduleItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceScheduleItemRepository extends JpaRepository<ServiceScheduleItem, Integer> {
  List<ServiceScheduleItem> findAllByCarId(Integer carId);

  List<ServiceScheduleItem> findAllByServiceScheduleId(int serviceScheduleId);
}
