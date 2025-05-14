package com.github.Denis.repository;

import com.github.Denis.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

////репозиторий – класс, который умеет работать с базой данных
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    // Методы JpaRepository уже включают основные операции:
    // save(), findById(), findAll(), deleteById(), и т.д.

    // Также можно определить кастомные методы:
//    Car findByName(String name);

//    :userId — именованный параметр, который заменяется значением переменной userId, переданной в метод findByUserId
//    Это JPQL (Java Persistence Query Language), а не обычный SQL.
//	•	В JPQL работают с классами и их полями, а не с таблицами и колонками.
//	•	Car — это имя Java-класса (например, com.github.Denis.entity.Car), а c — его alias (псевдоним).
//	•	c.user.id — это доступ к полю user в классе Car, у которого затем берётся id.
    @Query("SELECT c FROM Car c WHERE c.carUser.id = :userId")
    List<Car> getCarsByUserId(Integer userId);
}
