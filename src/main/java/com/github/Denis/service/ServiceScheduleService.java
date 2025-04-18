package com.github.Denis.service;

import com.github.Denis.entity.ServiceSchedule;
import com.github.Denis.repository.ServiceScheduleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceScheduleService {

    private final ServiceScheduleRepository serviceScheduleRepository;

    @Autowired
    ServiceScheduleService(ServiceScheduleRepository serviceScheduleRepository) {
        this.serviceScheduleRepository = serviceScheduleRepository;
    }


    //    Read all
    @Transactional
    public List<ServiceSchedule> getServiceSchedule() {
        return serviceScheduleRepository.findAll();
    }

    // Endpoint for AddSchedule page on front (list of work names)
    @Transactional
    public List<String> getServiceScheduleNames(){
        List<String> serviceScheduleNames = new ArrayList<>();
        for (ServiceSchedule ss : serviceScheduleRepository.findAll()) {
            serviceScheduleNames.add(ss.getName());
        }
        return serviceScheduleNames;
    }

    // Read by id
    @Transactional
    public ServiceSchedule getServiceScheduleByID(@PathVariable int id) {
        return serviceScheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
    }

    //    Create / Update
    @Transactional
    public int saveNewServiceSchedule(@RequestBody @Valid ServiceSchedule serviceSchedule) {
        serviceSchedule = serviceScheduleRepository.save(serviceSchedule);
        return serviceSchedule.getId();
    }

    //    Delete
    @Transactional
    public void deleteServiceSchedule(@PathVariable int id) {
        serviceScheduleRepository.deleteById(id);
    }
}
