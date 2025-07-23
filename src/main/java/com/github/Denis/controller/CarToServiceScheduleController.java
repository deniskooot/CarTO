package com.github.Denis.controller;

import com.github.Denis.dto.CarToServiceScheduleDTO;
import com.github.Denis.dto.ScheduleListDTO;
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
    CarToServiceScheduleController(CarToServiceScheduleService carToServiceScheduleService) {
        this.carToServiceScheduleService = carToServiceScheduleService;
    }

    @GetMapping("/cartoserviceschedules")
    public List<CarToServiceSchedule> getCarToServiceSchedule() {
        return carToServiceScheduleService.getCarToServiceSchedule();
    }

    // Read by id
    @GetMapping("/cartoserviceschedules/{id}")
    public CarToServiceSchedule getCarToServiceScheduleByID(@PathVariable int id) {
        return carToServiceScheduleService.getCarToServiceScheduleByID(id);
    }

    // Create / Update
    @PostMapping("/cartoserviceschedules")
    @Transactional
    public int saveNewCarToServiceSchedule(@RequestBody @Valid CarToServiceScheduleDTO carToServiceScheduleDTO) {
        return carToServiceScheduleService.saveCarToServiceSchedule(carToServiceScheduleDTO);
    }

    // Delete
    @DeleteMapping("/cartoserviceschedulecs/{id}")
    public void deleteCarToServiceSchedule(@PathVariable int id) {
        carToServiceScheduleService.deleteCarToServiceSchedule(id);
    }

    // Get for list of works to schedule history page on front (by selected car)
    @GetMapping("/service-schedule-history-list/{car_id}")
    public Map<Integer, String> getServiceScheduleListForHistoryForm(@PathVariable int car_id) {
        return carToServiceScheduleService.getServiceScheduleListForHistoryForm(car_id);
    }

    /**
     * Get task list for main page
     *
     * @param car_id id of selected car
     * @param schedule_perspective_mileage_or_year perspective parameter selection flag (years or mileage)
     * @param schedule_perspective_value value parameter using
     * @param show_required is show non-required tasks (recommended tasks)?
     * @return List of tasks
     */
    @GetMapping("/tasks")
    public List<ScheduleListDTO> getTasks(
            @RequestParam int car_id,
            @RequestParam String schedule_perspective_mileage_or_year,
            @RequestParam int schedule_perspective_value,
            @RequestParam boolean show_required
    ) {
        return carToServiceScheduleService.getTaskList(car_id, schedule_perspective_mileage_or_year, schedule_perspective_value, show_required);
    }
}
