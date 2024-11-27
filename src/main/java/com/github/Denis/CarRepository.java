package com.github.Denis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//репозиторий – класс, который умеет работать с базой данных
//@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}
