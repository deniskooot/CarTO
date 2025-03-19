package com.github.Denis.service;

import com.github.Denis.entity.CarToServiceSchedule;
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
//    private final ServiceScheduleRepository serviceScheduleRepository;

    @Autowired
    public CarToServiceScheduleService(CarToServiceScheduleRepository repository) {
        this.carToServiceScheduleRepository = repository;
//        this.serviceScheduleRepository = serviceScheduleRepository;
    }

    // Read
//    @GetMapping("/cartoserviceschedules")
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

    //    public int saveNewCarToServiceSchedule(CarToServiceSchedule carToServiceSchedule){
//        carToServiceSchedule = carToServiceScheduleRepository.save(carToServiceSchedule);
//        return carToServiceSchedule.getId();
//    }
    @Transactional
    public int saveCarToServiceSchedule(CarToServiceSchedule carToServiceSchedule) {
        return carToServiceScheduleRepository.save(carToServiceSchedule).getId();
    }


//    public Integer saveCarToServiceSchedule(CarToServiceSchedule carToServiceSchedule) {
//        // Проверяем, передан ли serviceSchedule и не является ли он null
//        if (carToServiceSchedule.getServiceSchedule() != null
//                && carToServiceSchedule.getServiceSchedule().getId() != null) {
//            System.out.println("Переданный ServiceSchedule: " + carToServiceSchedule.getServiceSchedule().getId());
//        } else {
//            System.out.println("ServiceSchedule не передан!");
//        }
//            // Загружаем ServiceSchedule из базы
//            ServiceSchedule existingSchedule = serviceScheduleRepository
//                    .findById(carToServiceSchedule.getServiceSchedule().getId())
//                    .orElseThrow(() -> new EntityNotFoundException("ServiceSchedule not found"));
//
//            // Устанавливаем загруженный объект
//            carToServiceSchedule.setServiceSchedule(existingSchedule);
//
//
//
//        // Сохраняем объект в БД
//        carToServiceSchedule = carToServiceScheduleRepository.save(carToServiceSchedule);
//
//        return carToServiceSchedule.getId();
//    }


}
