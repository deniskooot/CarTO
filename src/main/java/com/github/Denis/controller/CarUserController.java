package com.github.Denis.controller;

import com.github.Denis.entity.Car;
import com.github.Denis.entity.CarUser;
import com.github.Denis.repository.CarRepository;
import com.github.Denis.repository.CarUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class CarUserController {
  private final CarRepository carRepository;
  @PersistenceContext private EntityManager entityManager;
  private final CarUserRepository carUserRepository;

  @Autowired
  CarUserController(CarUserRepository carUserRepository, CarRepository carRepository) {
    this.carUserRepository = carUserRepository;
    this.carRepository = carRepository;
  }

  // Read
  @GetMapping("/carUsers")
  public List<CarUser> getCarUser() {
    return carUserRepository.findAll();
  }

  // Read by id
  @GetMapping("/carUsers/{id}")
  public CarUser getCarUserByID(@PathVariable int id) {
    return carUserRepository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
  }

  @GetMapping("/carUsers/{userId}/cars")
  public List<Car> getCarList(@PathVariable Integer userId) {
    return carRepository.getCarsByUserId(userId);
  }

  // Create / Update
  @PostMapping("/carUsers")
  @Transactional
  public int saveNewCarUser(@RequestBody @Valid CarUser carUser) {
    carUser = carUserRepository.save(carUser);
    return carUser.getId();
  }

  // Delete
  @DeleteMapping("/carUsers/{id}")
  public void deleteCarUser(@PathVariable int id) {
    carUserRepository.deleteById(id);
  }
}
