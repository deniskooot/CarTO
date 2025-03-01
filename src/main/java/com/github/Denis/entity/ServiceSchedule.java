package com.github.Denis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Denis.converter.DurationJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.Duration;
import java.util.List;

//service_schedules (service_schedule_id SERIAL PRIMARY KEY, name VARCHAR(100), is_required BOOLEAN, default_period_km INT, default_period_time_days INTERVAL DAY);

@Entity
@Table(name = "service_schedules")

public class ServiceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_schedule_id")
    private int id;
    @NotBlank(message = "Select name")
    private String name;
    private boolean is_required;
    @PositiveOrZero(message = "Default_period must bee >=0")
    private int default_period_km;
    @Schema(type = "integer", format = "int64", example = "10")
    @JsonSerialize(using = DurationJsonConverter.DurationSerializer.class)
    @JsonDeserialize(using = DurationJsonConverter.DurationDeserializer.class)
    @Column(name = "default_period_time_days")
    private Duration default_period_time_days; // (INTERVAL '1095' DAY)

    //    OneToMany CarToServiceSchedule reference, CarToServiceSchedule is owner reference (CarToServiceSchedule side is Many).
    @OneToMany(mappedBy = "serviceSchedule", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<CarToServiceSchedule> carToServiceSchedules;


    public ServiceSchedule(){

    }

    public ServiceSchedule(int id, String name, boolean is_required, int default_period_km, int default_period_time_days_int){
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDefault_period_time_days(Duration default_period_time_days) {
        this.default_period_time_days = default_period_time_days;
    }
}

