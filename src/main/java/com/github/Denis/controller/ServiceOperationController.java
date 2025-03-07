package com.github.Denis.controller;

import com.github.Denis.entity.ServiceOperation;
import com.github.Denis.repository.ServiceOperationsRepository;
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
public class ServiceOperationController {

    @PersistenceContext
    private EntityManager entityManager;

    private final ServiceOperationsRepository serviceOperationsRepository;

    @Autowired
    ServiceOperationController(ServiceOperationsRepository serviceOperationsRepository){
        this.serviceOperationsRepository = serviceOperationsRepository;
    }

    // Read
    @GetMapping("/serviceoperations")

    public List<ServiceOperation> getServiceOperation() {
        return serviceOperationsRepository.findAll();
    }

    // Read by id
    @GetMapping("/serviceoperations/{id}")
    public ServiceOperation getServiceOperationByID(@PathVariable int id) {
        return serviceOperationsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
    }

    //    Create / Update
    @PostMapping("/serviceoperations")
    @Transactional
    public int saveNewServiceOperation(@RequestBody @Valid ServiceOperation serviceOperation) {
        serviceOperation = serviceOperationsRepository.save(serviceOperation);
        return serviceOperation.getId();
    }

    //    Delete
    @DeleteMapping("/serviceoperations/{id}")
    public void deleteServiceOperation(@PathVariable int id) {
        serviceOperationsRepository.deleteById(id);
    }

}
