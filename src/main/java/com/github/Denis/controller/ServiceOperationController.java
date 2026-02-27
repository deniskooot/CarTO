package com.github.Denis.controller;

import com.github.Denis.dto.ServiceOperationDTO;
import com.github.Denis.entity.ServiceOperation;
import com.github.Denis.mapper.ServiceOperationMapper;
import com.github.Denis.repository.ServiceOperationsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class ServiceOperationController {

  private final ServiceOperationsRepository serviceOperationsRepository;
  private final ServiceOperationMapper serviceOperationMapper;

  @Autowired
  ServiceOperationController(
      ServiceOperationsRepository serviceOperationsRepository,
      ServiceOperationMapper serviceOperationMapper) {
    this.serviceOperationsRepository = serviceOperationsRepository;
    this.serviceOperationMapper = serviceOperationMapper;
  }

  // Read
  @GetMapping("/serviceOperations")
  public List<ServiceOperation> getServiceOperation() {
    return serviceOperationsRepository.findAll();
  }

  // Read by id
  @GetMapping("/serviceOperations/{id}")
  public ServiceOperation getServiceOperationByID(@PathVariable int id) {
    return serviceOperationsRepository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
  }

  // Create / Update
  @PostMapping("/serviceOperations")
  @Transactional
  public int saveNewServiceOperation(@RequestBody @Valid ServiceOperationDTO serviceOperationDTO) {
    ServiceOperation entity = serviceOperationMapper.toEntity(serviceOperationDTO);
    ServiceOperation serviceOperation = serviceOperationsRepository.save(entity);
    return serviceOperation.getId();
  }

  // Delete
  @DeleteMapping("/serviceOperations/{id}")
  public void deleteServiceOperation(@PathVariable int id) {
    serviceOperationsRepository.deleteById(id);
  }
}
