package com.github.Denis.repository;

import com.github.Denis.entity.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

  /**
   * Get List of Cars by userId. Custom JPQL query. Car - java class. c.user.id - access to the user
   * field in the Car class, from which the id is then taken. ":userId" — named parameter, with
   * change with userId var, passed to findByUserId method.
   *
   * @param userId User ID to find his cars
   * @return List of user cars
   */
  @Query(
      "SELECT c FROM Car c WHERE c.carUser.id = :userId") // :userId — именованный параметр, который
  // заменяется значением переменной userId,
  // переданной в метод findByUserId
  List<Car> getCarsByUserId(Integer userId);
}
