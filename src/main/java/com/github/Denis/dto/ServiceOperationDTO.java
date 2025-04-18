package com.github.Denis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ServiceOperationDTO {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("car_to_service_schedule_id")
    private Integer carToServiceScheduleId;
    private Integer mileageServiceOperation;
    private Date dateServiceOperation;
    private String notes;

    public ServiceOperationDTO(){

    }

    public ServiceOperationDTO(Integer id, Integer carToServiceScheduleId, Integer mileageServiceOperation, Date dateServiceOperation, String notes) {
        this.id = id;
        this.carToServiceScheduleId = carToServiceScheduleId;
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

    public Integer getCarToServiceScheduleId() {
        return carToServiceScheduleId;
    }

    public void setCarToServiceScheduleId(Integer carToServiceScheduleId) {
        this.carToServiceScheduleId = carToServiceScheduleId;
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
