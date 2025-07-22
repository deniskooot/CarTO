package com.github.Denis.controller;

import com.github.Denis.entity.CarUser;
import com.github.Denis.repository.CarUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarUserController {
    @PersistenceContext
    private EntityManager entityManager;

    private final CarUserRepository carUserRepository;

    @Autowired
    CarUserController(CarUserRepository carUserRepository) {
        this.carUserRepository = carUserRepository;
    }

    // Read
    @GetMapping("/carusers")

    public List<CarUser> getCarUser() {
        return carUserRepository.findAll();
    }

    // Read by id
    @GetMapping("/carusers/{id}")
    public CarUser getCarUserByID(@PathVariable int id) {
        return carUserRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
    }

    //Create / Update
    @PostMapping("/carusers")
    @Transactional
    public int saveNewCarUser(@RequestBody @Valid CarUser carUser) {
        carUser = carUserRepository.save(carUser);
        return carUser.getId();
    }

    // Delete
    @DeleteMapping("/carusers/{id}")
    public void deleteCarUser(@PathVariable int id) {
        carUserRepository.deleteById(id);
    }

}
