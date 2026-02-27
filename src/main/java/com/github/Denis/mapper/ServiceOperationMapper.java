package com.github.Denis.mapper;

import com.github.Denis.dto.ServiceOperationDTO;
import com.github.Denis.entity.ServiceOperation;
import com.github.Denis.entity.ServiceScheduleItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ServiceOperationMapper {

  //    toDTO
  @Mapping(source = "carToServiceSchedule.id", target = "carToServiceScheduleId")
  ServiceOperationDTO toDTO(ServiceOperation serviceOperation);

  //    toEntity
  @Mapping(
      source = "carToServiceScheduleId",
      target = "carToServiceSchedule",
      qualifiedByName = "mapCarToServiceIdByServiceSchedule")
  ServiceOperation toEntity(ServiceOperationDTO serviceOperationDTO);

  @Named("mapCarToServiceIdByServiceSchedule")
  default ServiceScheduleItem mapCarToServiceIdByServiceSchedule(
      Integer car_to_service_schedule_id) {
    if (car_to_service_schedule_id == null) return null;
    ServiceScheduleItem serviceScheduleItem = new ServiceScheduleItem();
    serviceScheduleItem.setId(car_to_service_schedule_id);
    return serviceScheduleItem;
  }
}
