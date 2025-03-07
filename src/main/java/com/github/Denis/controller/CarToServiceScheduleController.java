package com.github.Denis.controller;

import com.github.Denis.entity.CarToServiceSchedule;
import com.github.Denis.repository.CarToServiceScheduleRepository;
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
public class CarToServiceScheduleController {

    @PersistenceContext
    private EntityManager entityManager;

    private final CarToServiceScheduleRepository carToServiceScheduleRepository;

    @Autowired
    CarToServiceScheduleController(CarToServiceScheduleRepository carToServiceScheduleRepository){
        this.carToServiceScheduleRepository = carToServiceScheduleRepository;
    }
    // Read
    @GetMapping("/cartoserviceschedules")

    public List<CarToServiceSchedule> getCarToServiceSchedule() {
        return carToServiceScheduleRepository.findAll();
    }

    // Read by id
    @GetMapping("/cartoserviceschedules/{id}")
    public CarToServiceSchedule getCarToServiceScheduleByID(@PathVariable int id) {
        return carToServiceScheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
    }

    //    Create / Update
    @PostMapping("/cartoserviceschedules")
    @Transactional
    public int saveNewCarToServiceSchedule(@RequestBody @Valid CarToServiceSchedule carToServiceSchedule) {
        carToServiceSchedule = carToServiceScheduleRepository.save(carToServiceSchedule);
        return carToServiceSchedule.getId();
    }

    //    Delete
    @DeleteMapping("/cartoserviceschedulecs/{id}")
    public void deleteCarToServiceSchedule(@PathVariable int id) {
        carToServiceScheduleRepository.deleteById(id);
    }

}
