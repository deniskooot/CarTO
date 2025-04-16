package com.github.Denis.controller;

import com.github.Denis.dto.CarToServiceScheduleDTO;
import com.github.Denis.entity.CarToServiceSchedule;
import com.github.Denis.service.CarToServiceScheduleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public int saveNewCarToServiceSchedule(@RequestBody @Valid CarToServiceScheduleDTO carToServiceScheduleDTO) {
        return carToServiceScheduleService.saveCarToServiceSchedule(carToServiceScheduleDTO);
    }

    //    Delete
    @DeleteMapping("/cartoserviceschedulecs/{id}")
    public void deleteCarToServiceSchedule(@PathVariable int id) {
        carToServiceScheduleService.deleteCarToServiceSchedule(id);
    }

    // Get for list of works to schedule history page on front (by selected car)
    @GetMapping("/service-schedule-history-list/{car_id}")
    public Map<Integer, String> getServiceScheduleListForHistoryForm(@PathVariable int car_id){
        return carToServiceScheduleService.getServiceScheduleListForHistoryForm(car_id);
    }


}
