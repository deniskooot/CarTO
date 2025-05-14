package com.github.Denis.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Denis.converter.DurationJsonConverter;
import com.github.Denis.dto.CarToServiceScheduleDTO;
import com.github.Denis.dto.ScheduleListDTO;
import com.github.Denis.entity.*;
import com.github.Denis.service.CarToServiceScheduleService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/api")
public class CarToServiceScheduleController {

    @PersistenceContext
    private EntityManager entityManager;

    private final CarToServiceScheduleService carToServiceScheduleService;

    @Autowired
    CarToServiceScheduleController(CarToServiceScheduleService carToServiceScheduleService){
        this.carToServiceScheduleService = carToServiceScheduleService;
    }


    // Read
    @GetMapping("/cartoserviceschedules")
    public List<CarToServiceSchedule> getCarToServiceSchedule() {
        return carToServiceScheduleService.getCarToServiceSchedule();
    }

    // Read by id
    @GetMapping("/cartoserviceschedules/{id}")
    public CarToServiceSchedule getCarToServiceScheduleByID(@PathVariable int id) {
        return carToServiceScheduleService.getCarToServiceScheduleByID(id);
    }

    //    Create / Update
    @PostMapping("/cartoserviceschedules")
    @Transactional
    public int saveNewCarToServiceSchedule(@RequestBody @Valid CarToServiceScheduleDTO carToServiceScheduleDTO) {
        return carToServiceScheduleService.saveCarToServiceSchedule(carToServiceScheduleDTO);
    }

    //    Delete
    @DeleteMapping("/cartoserviceschedulecs/{id}")
    public void deleteCarToServiceSchedule(@PathVariable int id) {
        carToServiceScheduleService.deleteCarToServiceSchedule(id);
    }

    // Get for list of works to schedule history page on front (by selected car)
    @GetMapping("/service-schedule-history-list/{car_id}")
    public Map<Integer, String> getServiceScheduleListForHistoryForm(@PathVariable int car_id){
        return carToServiceScheduleService.getServiceScheduleListForHistoryForm(car_id);
    }

// endpoint выдачи списка работ в main страницу по параметрам

//    @GetMapping("/api/tasks")
//public List<ScheduleListDTO> getTasks(
//    @RequestParam long car_id,
//    @RequestParam String schedule_perspective_mileage_or_year,
//    @RequestParam int schedule_perspective_value,
//    @RequestParam boolean show_required
//) {
//
//        return List<CarToServiceSchedule>
//    }

//    @Table(name = "car_to_service_schedules")

//    @Column(name = "car_to_service_schedule_id")
//    private int id;
//    @JsonProperty("periodicity_km")
//    private int periodicityKm;
//    private Duration periodicityTimeDays;
//    private String notes;
//
//    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
//    private Car car;
//
//    @JoinColumn(name = "service_schedule_id", referencedColumnName = "service_schedule_id")
//    private ServiceSchedule serviceSchedule;
//
//    @OneToMany(mappedBy = "carToServiceSchedule", cascade = CascadeType.ALL, orphanRemoval = false)
//    private List<ServiceOperation> serviceOperations; //= new ArrayList<>();
//
//    @OneToMany(mappedBy = "carToServiceSchedule", cascade = CascadeType.ALL, orphanRemoval = false)
//    private List<Part> parts; // = new ArrayList<>();


    // Обработка логики и возврат списка работ
//        <tr>
//                <th className="border px-2 py-1 text-left">Наименование работы</th>
//                <th className="border px-2 py-1 text-left">Пробег</th>
//                <th className="border px-2 py-1 text-left">Дата</th>
//                <th className="border px-2 py-1 text-left">Примечание</th>
//                <th className="border px-2 py-1 text-left">*</th>
//              </tr>
//            </thead>
//            <tbody>
//              <tr>
//                <td className="border px-2 py-1">Замена масла</td>
//                <td className="border px-2 py-1">50000</td>
//                <td className="border px-2 py-1">25.04.2025</td>
//                <td className="border px-2 py-1">Последний раз залил Shell, шайба на сливную пробку не нужна, есть в запасе</td>
//                <td className="border px-2 py-1 text-center">
//                  <input type="checkbox" checked readOnly />
//                </td>
//              </tr>
//              <tr>
//                <td className="border px-2 py-1">Замена масла</td>
//                <td className="border px-2 py-1">57500</td>
//                <td className="border px-2 py-1">25.04.2026</td>
//                <td className="border px-2 py-1">Заметки пользователя</td>
//                <td className="border px-2 py-1 text-center">
//                  <input type="checkbox" readOnly />
//                </td>
//              </tr>



}
