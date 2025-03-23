package com.github.Denis.dto;

public class CarToServiceScheduleDTO {

    private Integer id;
    private Integer periodicity_km;
    private Integer periodicity_time_days;
    private String notes;
    private Integer carId;
    private Integer serviceScheduleId;

    public CarToServiceScheduleDTO(){

    }
    public CarToServiceScheduleDTO(Integer id, Integer periodicity_km, Integer periodicity_time_days, String notes, Integer carId, Integer serviceScheduleId) {
        this.id = id;
        this.periodicity_km = periodicity_km;
        this.periodicity_time_days = periodicity_time_days;
        this.notes = notes;
        this.carId = carId;
        this.serviceScheduleId = serviceScheduleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPeriodicity_km() {
        return periodicity_km;
    }

    public void setPeriodicity_km(Integer periodicity_km) {
        this.periodicity_km = periodicity_km;
    }

    public Integer getPeriodicity_time_days() {
        return periodicity_time_days;
    }

    public void setPeriodicity_time_days(Integer periodicity_time_days) {
        this.periodicity_time_days = periodicity_time_days;
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

    public Integer getServiceScheduleId() {
        return serviceScheduleId;
    }

    public void setServiceScheduleId(Integer serviceScheduleId) {
        this.serviceScheduleId = serviceScheduleId;
    }
}
