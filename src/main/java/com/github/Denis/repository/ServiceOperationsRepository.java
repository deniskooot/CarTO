package com.github.Denis.repository;

import com.github.Denis.entity.ServiceOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOperationsRepository extends JpaRepository<ServiceOperation, Integer> {
}
