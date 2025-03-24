package com.github.Denis.service;

import com.github.Denis.dto.CarToServiceScheduleDTO;
import com.github.Denis.entity.CarToServiceSchedule;
import com.github.Denis.entity.ServiceSchedule;
import com.github.Denis.mapper.CarToServiceScheduleMapper;
import com.github.Denis.repository.CarToServiceScheduleRepository;
import com.github.Denis.repository.ServiceScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class CarToServiceScheduleService {

    private final CarToServiceScheduleRepository carToServiceScheduleRepository;
    private final CarToServiceScheduleMapper carToServiceScheduleMapper;
    private final ServiceScheduleRepository serviceScheduleRepository;

    @Autowired
    public CarToServiceScheduleService(CarToServiceScheduleRepository repository, CarToServiceScheduleMapper carToServiceScheduleMapper, ServiceScheduleRepository serviceScheduleRepository) {
        this.carToServiceScheduleRepository = repository;
        this.carToServiceScheduleMapper = carToServiceScheduleMapper;
        this.serviceScheduleRepository = serviceScheduleRepository;
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
//        получили DTO со строкой dto.getServiceScheduleName();
        CarToServiceSchedule carToServiceSchedule;
        int serviceScheduleId;
//        спросили есть ли в базе запись с такой строкой
        Optional<ServiceSchedule> findName = serviceScheduleRepository.findByName(dto.getServiceScheduleName().trim());


        if (findName.isPresent()) {
            // если такая работа в базе есть, получаем id
            serviceScheduleId = findName.get().getId();
//            serviceScheduleId = findName.get();
        } else {
            // если такой работы в базе нет, создаем новую запись
            ServiceSchedule newServiceSchedule = new ServiceSchedule();
            newServiceSchedule.setName(dto.getServiceScheduleName().trim());
            newServiceSchedule.setDefault_period_km(dto.getPeriodicity_km());
            newServiceSchedule.setDefault_period_time_days(Duration.ofDays(dto.getPeriodicity_time_days()));
            newServiceSchedule.setIs_required(dto.getIsRequired());

            // сохраняем новую работу в базу и получаем id
//            ServiceSchedule serviceScheduleId = serviceScheduleRepository.save(newServiceSchedule);
            serviceScheduleId = serviceScheduleRepository.save(newServiceSchedule).getId();
        }

//        берем работу по ее id
        ServiceSchedule serviceSchedule = serviceScheduleRepository.getReferenceById(serviceScheduleId);

//        далее тестить


        CarToServiceSchedule entity = carToServiceScheduleMapper.toEntity(dto);
        //сохраняем в CarToServiceSchedule entity сущность ServiceSchedule serviceSchedule
        entity.setServiceSchedule(serviceSchedule);
        return carToServiceScheduleRepository.save(entity).getId();
    }

}
