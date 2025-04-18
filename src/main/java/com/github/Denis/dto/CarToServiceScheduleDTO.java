package com.github.Denis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarToServiceScheduleDTO {

    private Integer id;
    @JsonProperty("periodicity_km")
    private Integer periodicityKm;
    @JsonProperty("periodicity_time_days")
    private Integer periodicityTimeDays;
    private String notes;
    private Integer carId;
    private String serviceScheduleName;
    private boolean isRequired;

    public CarToServiceScheduleDTO(){

    }
    public CarToServiceScheduleDTO(Integer id, Integer periodicityKm, Integer periodicityTimeDays, String notes, Integer carId, String serviceScheduleName, boolean isRequired) {
        this.id = id;
        this.periodicityKm = periodicityKm;
        this.periodicityTimeDays = periodicityTimeDays;
        this.notes = notes;
        this.carId = carId;
        this.serviceScheduleName = serviceScheduleName;
        this.isRequired = isRequired;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPeriodicityKm() {
        return periodicityKm;
    }

    public void setPeriodicityKm(Integer periodicityKm) {
        this.periodicityKm = periodicityKm;
    }

    public Integer getPeriodicityTimeDays() {
        return periodicityTimeDays;
    }

    public void setPeriodicityTimeDays(Integer periodicityTimeDays) {
        this.periodicityTimeDays = periodicityTimeDays;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getServiceScheduleName() {
        return serviceScheduleName;
    }

    public void setServiceScheduleName(String serviceScheduleName) {
        this.serviceScheduleName = serviceScheduleName;
    }

    public boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean required) {
        isRequired = required;
    }
}
