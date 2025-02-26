package com.github.Denis.controller;

import com.github.Denis.entity.ServiceSchedule;
import com.github.Denis.repository.ServiceScheduleRepository;
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
@RequestMapping
public class ServiceScheduleController {
    @PersistenceContext
    private EntityManager entityManager;

    private final ServiceScheduleRepository serviceScheduleRepository;

    @Autowired
    ServiceScheduleController(ServiceScheduleRepository serviceScheduleRepository){
        this.serviceScheduleRepository = serviceScheduleRepository;
    }

    // Read
    @GetMapping("/serviceschedulerepository")

    public List<ServiceSchedule> getServiceOperation() {
        return serviceScheduleRepository.findAll();
    }

    // Read by id
    @GetMapping("/serviceschedulerepository/{id}")
    public ServiceSchedule getServiceOperationByID(@PathVariable int id) {
        return serviceScheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
    }

    //    Create / Update
    @PostMapping("/serviceschedulerepository")
    @Transactional
    public int saveNewServiceOperation(@RequestBody @Valid ServiceSchedule serviceSchedule) {
        serviceSchedule = serviceScheduleRepository.save(serviceSchedule);
        return serviceSchedule.getId();
    }

    //    Delete
    @DeleteMapping("/serviceschedulerepository/{id}")
    public void deleteServiceOperation(@PathVariable int id) {
        serviceScheduleRepository.deleteById(id);
    }

}
