package com.github.Denis.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.Duration;

@Entity
@Table(name = "service_schedules")

//CREATE TABLE service_shedule (service_shedule_id SERIAL PRIMARY KEY, name VARCHAR(100), is_required BOOLEAN, default_period_km INT, default_period_time_days INTERVAL DAY);
//ALTER TABLE service_schedule RENAME COLUMN service_shedule_id TO service_schedule_id;

public class ServiceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_schedule_id")
    private int service_schedule_id;
    @NotBlank(message = "Select name")
    private String name;
    private boolean is_required;
    @PositiveOrZero(message = "Default_period must bee >=0")
    private int default_period_km;

    @Column(name = "default_period_time_days")
    private Duration default_period_time_days; // (INTERVAL '1095' DAY)

    public ServiceSchedule(){

    }

    public ServiceSchedule(int service_schedule_id, String name, boolean is_required, int default_period_km, int default_period_time_days_int){
        this.service_schedule_id = service_schedule_id;
        this.name = name;
        this.is_required = is_required;
        this.default_period_km = default_period_km;
        this.default_period_time_days = Duration.ofDays(default_period_time_days_int);
    }

    // INSERT INTO service_schedule VALUES (2, 'Замена свечей зажигания', true, 30_000, INTERVAL '1095' DAY);

//    name VARCHAR(100)
//    is_required BOOLEAN
//    default_period_km INT
//    default_period_time_days INTERVAL DAY

    public int getService_schedule_id() {
        return service_schedule_id;
    }

    public void setService_schedule_id(int service_schedule_id) {
        this.service_schedule_id = service_schedule_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_required() {
        return is_required;
    }

    public void setIs_required(boolean is_required) {
        this.is_required = is_required;
    }

    public int getDefault_period_km() {
        return default_period_km;
    }

    public void setDefault_period_km(int default_period_km) {
        this.default_period_km = default_period_km;
    }

    public Duration getDefault_period_time_days() {
        return default_period_time_days;
    }

    public void setDefault_period_time_days(int default_period_time_days_int) {
        this.default_period_time_days = Duration.ofDays(default_period_time_days_int);
    }
}

