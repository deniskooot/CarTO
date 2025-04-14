package com.github.Denis.dto;

import java.util.Date;

public class ServiceOperationDTO {


    private Integer id;
    private Integer car_to_service_schedule_id;
    private Integer mileageServiceOperation;
    private Date dateServiceOperation;
    private String notes;

    public ServiceOperationDTO(){

    }

    public ServiceOperationDTO(Integer id, Integer car_to_service_schedule_id, Integer mileageServiceOperation, Date dateServiceOperation, String notes) {
        this.id = id;
        this.car_to_service_schedule_id = car_to_service_schedule_id;
        this.mileageServiceOperation = mileageServiceOperation;
        this.dateServiceOperation = dateServiceOperation;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCar_to_service_schedule_id() {
        return car_to_service_schedule_id;
    }

    public void setCar_to_service_schedule_id(Integer car_to_service_schedule_id) {
        this.car_to_service_schedule_id = car_to_service_schedule_id;
    }

    public Integer getMileageServiceOperation() {
        return mileageServiceOperation;
    }

    public void setMileageServiceOperation(Integer mileageServiceOperation) {
        this.mileageServiceOperation = mileageServiceOperation;
    }

    public Date getDateServiceOperation() {
        return dateServiceOperation;
    }

    public void setDateServiceOperation(Date dateServiceOperation) {
        this.dateServiceOperation = dateServiceOperation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
