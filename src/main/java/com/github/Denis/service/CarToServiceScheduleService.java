package com.github.Denis.service;

import com.github.Denis.dto.CarToServiceScheduleDTO;
import com.github.Denis.entity.CarToServiceSchedule;
import com.github.Denis.mapper.CarToServiceScheduleMapper;
import com.github.Denis.repository.CarToServiceScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarToServiceScheduleService {

    private final CarToServiceScheduleRepository carToServiceScheduleRepository;
    private final CarToServiceScheduleMapper carToServiceScheduleMapper;

    @Autowired
    public CarToServiceScheduleService(CarToServiceScheduleRepository repository, CarToServiceScheduleMapper carToServiceScheduleMapper) {
        this.carToServiceScheduleRepository = repository;
        this.carToServiceScheduleMapper = carToServiceScheduleMapper;
    }

    // Read
    @Transactional
    public List<CarToServiceSchedule> getCarToServiceSchedule() {
        return carToServiceScheduleRepository.findAll();
    }

    // Read by id
    @Transactional
    public CarToServiceSchedule getCarToServiceScheduleByID(int id) {
        return carToServiceScheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Entity not found"));
    }

    @Transactional
    public void deleteCarToServiceSchedule(int id) {
        carToServiceScheduleRepository.deleteById(id);
    }

    @Transactional
    public int saveCarToServiceSchedule(CarToServiceScheduleDTO dto) {
        CarToServiceSchedule entity = carToServiceScheduleMapper.toEntity(dto);
        return carToServiceScheduleRepository.save(entity).getId();
    }

}
