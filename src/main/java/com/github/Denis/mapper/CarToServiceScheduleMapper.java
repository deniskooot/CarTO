package com.github.Denis.mapper;

import com.github.Denis.dto.CarToServiceScheduleDTO;
import com.github.Denis.entity.Car;
import com.github.Denis.entity.CarToServiceSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Duration;

@Mapper(componentModel = "spring")
public interface CarToServiceScheduleMapper {

    // Преобразование из Entity в DTO
    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "periodicity", target = "periodicityTimeDays", qualifiedByName = "mapDurationToInteger")
    CarToServiceScheduleDTO toDTO(CarToServiceSchedule entity);

    // Преобразование из DTO в Entity
    @Mapping(source = "carId", target = "car", qualifiedByName = "mapCarIdToCar")
    @Mapping(source = "periodicityTimeDays", target = "periodicityTimeDays")
    CarToServiceSchedule toEntity(CarToServiceScheduleDTO dto);

    // Преобразование Duration to Integer
    @Named("mapDurationToInteger")
    default Integer mapDurationToInteger(Duration durationInDays) {
        return durationInDays != null ? (int) durationInDays.toDays() : null;
    }

    // Преобразование Integer to Duration
    @Named("mapIntegerToDuration")
    default Duration mapIntegerToDuration(Integer integerInDays) {
        return integerInDays != null ? Duration.ofDays(integerInDays) : null;
    }

    // Метод для преобразования carId в Car
    @Named("mapCarIdToCar")
    default Car mapCarIdToCar(Integer id) {
        if (id == null) return null;
        Car car = new Car();
        car.setId(id);
        return car;
    }

}




