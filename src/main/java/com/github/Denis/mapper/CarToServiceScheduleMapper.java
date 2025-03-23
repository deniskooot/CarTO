package com.github.Denis.mapper;

import com.github.Denis.dto.CarToServiceScheduleDTO;
import com.github.Denis.entity.Car;
import com.github.Denis.entity.CarToServiceSchedule;
import com.github.Denis.entity.ServiceSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Duration;

// @Mapper — говорит MapStruct, что этот интерфейс является маппером
// componentModel = "spring" — интегрирует маппер с Spring, чтобы его можно было внедрять через @Autowired
// MapStruct автоматически реализует интерфейс CarToServiceScheduleMapper
@Mapper(componentModel = "spring")
public interface CarToServiceScheduleMapper {

    // Преобразование из Entity в DTO
    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "serviceSchedule.id", target = "serviceScheduleId")
    @Mapping(source = "periodicity_time_days", target = "periodicity_time_days", qualifiedByName = "mapDurationToInteger")
//    MapStruct автоматически генерирует реализацию этого метода на основе аннотаций @Mapping
//    В скомпилированном коде будет реальный метод, который берет entity, извлекает из него нужные поля и создает DTO
    CarToServiceScheduleDTO toDTO(CarToServiceSchedule entity);

    // Преобразование из DTO в Entity
    @Mapping(source = "carId", target = "car", qualifiedByName = "mapCarIdToCar")
    @Mapping(source = "serviceScheduleId", target = "serviceSchedule", qualifiedByName = "mapServiceScheduleIdToServiceSchedule")
    @Mapping(source = "periodicity_time_days", target = "periodicity_time_days", qualifiedByName = "mapIntegerToDuration")
    CarToServiceSchedule toEntity(CarToServiceScheduleDTO dto);
    // Метод для преобразования carId в Car

    @Named("mapDurationToInteger")
    default Integer mapDurationToInteger(Duration durationInDays){
        return durationInDays != null? (int) durationInDays.toDays() : null;
    }

    @Named("mapIntegerToDuration")
    default Duration mapIntegerToDuration(Integer integerInDays){
        return integerInDays != null ? Duration.ofDays(integerInDays) : null;
    }

    @Named("mapCarIdToCar")
    default Car mapCarIdToCar(Integer id) {
        if (id == null) return null;
        Car car = new Car();
        car.setId(id);
        return car;
    }

    // Метод для преобразования serviceScheduleId в ServiceSchedule
    @Named("mapServiceScheduleIdToServiceSchedule")
    default ServiceSchedule mapServiceScheduleIdToServiceSchedule(Integer id) {
        if (id == null) return null;
        ServiceSchedule serviceSchedule = new ServiceSchedule();
        serviceSchedule.setId(id);
        return serviceSchedule;
    }

}




