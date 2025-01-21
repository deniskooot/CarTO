package com.github.Denis.repository;

import com.github.Denis.entity.CarUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarUserRepository extends JpaRepository<CarUser, Integer> {

}
