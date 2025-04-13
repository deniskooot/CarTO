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
    @Mapping(source = "carToServiceSchedule.id", target = "carToServiceScheduleId")
    ServiceOperationDTO toDTO(ServiceOperation serviceOperation);

//    toEntity
    @Mapping(source = "carToServiceScheduleId", target = "carToServiceSchedule", qualifiedByName = "mapServiceOperationIdByServiceOperation")
//    @Mapping(source = "id", target = "id")
    @Mapping(source = "mileageServiceOperation", target = "mileage_service_operation")
    @Mapping(source = "dateServiceOperation", target = "date_service_operation")
    ServiceOperation toEntity(ServiceOperationDTO serviceOperationDTO);



    @Named("mapServiceOperationIdByServiceOperation")
    default CarToServiceSchedule mapServiceOperationIdByServiceOperation(Integer id){
        if (id == null) return null;
        CarToServiceSchedule carToServiceSchedule = new CarToServiceSchedule();
        carToServiceSchedule.setId(id);
        return carToServiceSchedule;
    }

//    private Integer id;
//    private Integer mileageServiceOperation;
//    private Date dateServiceOperation;
//    private String notes;
//    private Integer carToServiceScheduleId;


//    React:

//    car_id: 0,
//    serviceScheduleId: 0,
//    mileage_service_operation: 0,
//    date_service_operation: "2025-04-01", //"YYYY-MM-DD"
//    notes: ""

}
