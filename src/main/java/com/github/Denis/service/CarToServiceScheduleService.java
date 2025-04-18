package com.github.Denis.service;

import com.github.Denis.dto.CarToServiceScheduleDTO;
import com.github.Denis.entity.CarToServiceSchedule;
import com.github.Denis.entity.ServiceSchedule;
import com.github.Denis.mapper.CarToServiceScheduleMapper;
import com.github.Denis.repository.CarToServiceScheduleRepository;
import com.github.Denis.repository.ServiceScheduleRepository;
import com.github.Denis.utils.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    // получили DTO со строкой dto.getServiceScheduleName();
        int serviceScheduleId;
        // спросили есть ли в базе запись с такой строкой
        Optional<ServiceSchedule> findName = serviceScheduleRepository.findByNameIgnoreCase(dto.getServiceScheduleName().trim());

        if (findName.isPresent()) {
            // если такая работа в базе есть, получаем id
            serviceScheduleId = findName.get().getId();
        } else {
            // если такой работы в базе нет, создаем новую запись
            ServiceSchedule newServiceSchedule = new ServiceSchedule();
            // Приводим первый символ строки в верхний регистр
            newServiceSchedule.setName(StringUtils.normalizeServiceScheduleName(dto.getServiceScheduleName().trim()));
            newServiceSchedule.setDefaultPeriodKm(dto.getPeriodicityKm());
            newServiceSchedule.setDefaultPeriodTimeDays(Duration.ofDays(dto.getPeriodicityTimeDays()));
            newServiceSchedule.setRequired(dto.getIsRequired());

            // сохраняем новую работу в базу и получаем id
            serviceScheduleId = serviceScheduleRepository.save(newServiceSchedule).getId();
        }

        // берем работу по ее id
        ServiceSchedule serviceSchedule = serviceScheduleRepository.getReferenceById(serviceScheduleId);

        CarToServiceSchedule entity = carToServiceScheduleMapper.toEntity(dto);
        //сохраняем в CarToServiceSchedule entity сущность ServiceSchedule serviceSchedule
        entity.setServiceSchedule(serviceSchedule);
        return carToServiceScheduleRepository.save(entity).getId();
    }

    // Get for list of works to schedule history page on front (by selected car)
    @Transactional
    public Map<Integer, String> getServiceScheduleListForHistoryForm(@PathVariable int car_id) {
        Map<Integer, String> result = new HashMap<>();
        // carToServiceScheduleRepository.findAll();
        List<CarToServiceSchedule> carToServiceScheduleList = carToServiceScheduleRepository.findAllByCarId(car_id);

        // проверка на null заставляет hibernate впервые обратиться к связи и ее подгрузить. После чего, при следующем обращении lazy связь уже подгружена
        for (CarToServiceSchedule entity : carToServiceScheduleList) {
            if (entity.getServiceSchedule() != null) {
                result.put(entity.getId(), entity.getServiceSchedule().getName());

                // Если name ещё не встречался — добавляем
                // nameToIdMap.putIfAbsent(name, id);
            }
        }
        return result;
    }

}
