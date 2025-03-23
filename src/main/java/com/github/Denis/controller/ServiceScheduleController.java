package com.github.Denis.controller;

import com.github.Denis.entity.ServiceSchedule;
import com.github.Denis.service.ServiceScheduleService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceScheduleController {

    private final ServiceScheduleService serviceScheduleService;

    @Autowired
    ServiceScheduleController(ServiceScheduleService serviceScheduleService) {
        this.serviceScheduleService = serviceScheduleService;
    }

    // Read
    @GetMapping("/serviceschedules")

    public List<ServiceSchedule> getServiceSchedule() {
        return serviceScheduleService.getServiceSchedule();
    }

    // Read by id
    @GetMapping("/serviceschedules/{id}")
    public ServiceSchedule getServiceScheduleByID(@PathVariable int id) {
        return serviceScheduleService.getServiceScheduleByID(id);
    }

    //    Create / Update
    @PostMapping("/serviceschedules")
    @Transactional
    public int saveNewServiceSchedule(@RequestBody @Valid ServiceSchedule serviceSchedule) {
        return serviceScheduleService.saveNewServiceSchedule(serviceSchedule);
    }

    //    Delete
    @DeleteMapping("/serviceschedules/{id}")
    public void deleteServiceSchedule(@PathVariable int id) {
        serviceScheduleService.deleteServiceSchedule(id);
    }

}
