package com.github.Denis.controller;

import com.github.Denis.dto.ServiceOperationDTO;
import com.github.Denis.entity.ServiceOperation;
import com.github.Denis.mapper.ServiceOperationMapper;
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

    private final ServiceOperationMapper serviceOperationMapper;

    @Autowired
    ServiceOperationController(ServiceOperationsRepository serviceOperationsRepository, ServiceOperationMapper serviceOperationMapper){
        this.serviceOperationsRepository = serviceOperationsRepository;
        this.serviceOperationMapper = serviceOperationMapper;
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
    public int saveNewServiceOperation(@RequestBody @Valid ServiceOperationDTO serviceOperationDTO) {
        ServiceOperation entity = serviceOperationMapper.toEntity(serviceOperationDTO);
        ServiceOperation serviceOperation = serviceOperationsRepository.save(entity);
        return serviceOperation.getId();
    }

    //    Delete
    @DeleteMapping("/serviceoperations/{id}")
    public void deleteServiceOperation(@PathVariable int id) {
        serviceOperationsRepository.deleteById(id);
    }

}
