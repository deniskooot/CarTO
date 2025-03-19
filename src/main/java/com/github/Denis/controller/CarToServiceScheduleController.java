package com.github.Denis.controller;

import com.github.Denis.entity.CarToServiceSchedule;
import com.github.Denis.service.CarToServiceScheduleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api")
public class CarToServiceScheduleController {

    @PersistenceContext
    private EntityManager entityManager;

    private final CarToServiceScheduleService carToServiceScheduleService;

    @Autowired
    CarToServiceScheduleController(CarToServiceScheduleService carToServiceScheduleService){
        this.carToServiceScheduleService = carToServiceScheduleService;
    }

    // Read
    @GetMapping("/cartoserviceschedules")
    public List<CarToServiceSchedule> getCarToServiceSchedule() {
        return carToServiceScheduleService.getCarToServiceSchedule();
    }

    // Read by id
    @GetMapping("/cartoserviceschedules/{id}")
    public CarToServiceSchedule getCarToServiceScheduleByID(@PathVariable int id) {
        return carToServiceScheduleService.getCarToServiceScheduleByID(id);
    }

    //    Create / Update
    @PostMapping("/cartoserviceschedules")
    @Transactional
    public int saveNewCarToServiceSchedule(@RequestBody @Valid CarToServiceSchedule carToServiceSchedule) {
        return carToServiceScheduleService.saveCarToServiceSchedule(carToServiceSchedule);
    }

    //    Delete
    @DeleteMapping("/cartoserviceschedulecs/{id}")
    public void deleteCarToServiceSchedule(@PathVariable int id) {
        carToServiceScheduleService.deleteCarToServiceSchedule(id);
    }

}
