package com.github.Denis.controller;

import com.github.Denis.entity.ServiceSchedule;
import com.github.Denis.entity.ServiceScheduleItem;
import com.github.Denis.repository.ServiceScheduleItemRepository;
import com.github.Denis.service.ServiceScheduleItemService;
import com.github.Denis.service.ServiceScheduleService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class ServiceScheduleController {

  private final ServiceScheduleService serviceScheduleService;
  private final ServiceScheduleItemService serviceScheduleItemService;
  private final ServiceScheduleItemRepository serviceScheduleItemRepository;

  @Autowired
  ServiceScheduleController(
      ServiceScheduleService serviceScheduleService,
      ServiceScheduleItemService serviceScheduleItemService,
      ServiceScheduleItemRepository serviceScheduleItemRepository) {
    this.serviceScheduleService = serviceScheduleService;
    this.serviceScheduleItemService = serviceScheduleItemService;
    this.serviceScheduleItemRepository = serviceScheduleItemRepository;
  }

  // Read
  @GetMapping("/serviceSchedules")
  public List<ServiceSchedule> getServiceSchedule() {
    return serviceScheduleService.getServiceSchedule();
  }

  // Endpoint for AddSchedule page on front (list of work names)
  @GetMapping("/serviceSchedules/names")
  public List<String> getServiceScheduleNames() {
    return serviceScheduleService.getServiceScheduleNames();
  }

  // Read by id
  @GetMapping("/serviceSchedules/{id}")
  public ServiceSchedule getServiceScheduleByID(@PathVariable int id) {
    return serviceScheduleService.getServiceScheduleByID(id);
  }

  // Create / Update
  @PostMapping("/serviceSchedules")
  @Transactional
  public int saveNewServiceSchedule(@RequestBody @Valid ServiceSchedule serviceSchedule) {
    return serviceScheduleService.saveNewServiceSchedule(serviceSchedule);
  }

  // Delete
  @DeleteMapping("/serviceSchedules/{id}")
  public void deleteServiceSchedule(@PathVariable int id) {
    serviceScheduleService.deleteServiceSchedule(id);
  }

  @GetMapping("/serviceSchedules/{scheduleId}/items")
  public List<ServiceScheduleItem> getCarToServiceScheduleItemsByID(@PathVariable int scheduleId) {
    return serviceScheduleItemRepository.findAllByServiceScheduleId(scheduleId);
  }

  @GetMapping("/serviceSchedules/{scheduleId}/items/{itemId}")
  public ServiceScheduleItem getCarToServiceScheduleByID(@PathVariable int itemId) {
    return serviceScheduleItemRepository
        .findById(itemId)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
  }
}
