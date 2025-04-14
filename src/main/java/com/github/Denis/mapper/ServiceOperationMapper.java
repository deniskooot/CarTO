package com.github.Denis.mapper;

import com.github.Denis.dto.ServiceOperationDTO;
import com.github.Denis.entity.CarToServiceSchedule;
import com.github.Denis.entity.ServiceOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface ServiceOperationMapper {



//    toDTO
    @Mapping(source = "carToServiceSchedule.id", target = "car_to_service_schedule_id")
    ServiceOperationDTO toDTO(ServiceOperation serviceOperation);

//    toEntity
    @Mapping(source = "car_to_service_schedule_id", target = "carToServiceSchedule", qualifiedByName = "mapCarToServiceIdByServiceSchedule")
//    @Mapping(source = "id", target = "id")
    @Mapping(source = "mileageServiceOperation", target = "mileage_service_operation")
    @Mapping(source = "dateServiceOperation", target = "date_service_operation")
    ServiceOperation toEntity(ServiceOperationDTO serviceOperationDTO);



    @Named("mapCarToServiceIdByServiceSchedule")
    default CarToServiceSchedule mapCarToServiceIdByServiceSchedule(Integer car_to_service_schedule_id){
        if (car_to_service_schedule_id == null) return null;
        CarToServiceSchedule carToServiceSchedule = new CarToServiceSchedule();
        carToServiceSchedule.setId(car_to_service_schedule_id);
        return carToServiceSchedule;
    }

//    Entity:

//    private int id;=
//    private int mileage_service_operation;+
//    private Date date_service_operation;+
//    private String notes;=
//    private CarToServiceSchedule carToServiceSchedule;+

//    DTO:

//    private Integer id;=
//    private Integer car_to_service_schedule_id;+
//    private Integer mileageServiceOperation;+
//    private Date dateServiceOperation;+
//    private String notes;=

//    React:

//    car_to_service_schedule_id: number;
//    mileageServiceOperation: number;
//    dateServiceOperation: string; //"YYYY-MM-DD"
//    notes?: string;

}
