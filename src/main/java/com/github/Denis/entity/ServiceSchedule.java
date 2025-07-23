package com.github.Denis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Denis.converter.DurationJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "service_schedules")

public class ServiceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_schedule_id")
    private int id;
    @NotBlank(message = "Select name")
    private String name;
    @JsonProperty("is_required")
    private boolean isRequired;
    @PositiveOrZero(message = "Default_period must bee >=0")
    @JsonProperty("default_period_km")
    private int defaultPeriodKm;
    @Schema(type = "integer", format = "int64", example = "10")
    @JsonSerialize(using = DurationJsonConverter.DurationSerializer.class)
    @JsonDeserialize(using = DurationJsonConverter.DurationDeserializer.class)
    @Column(name = "default_period_time_days")
    @JsonProperty("default_period_time_days")
    private Duration defaultPeriodTimeDays; // (INTERVAL '1095' DAY)
    @OneToMany(mappedBy = "serviceSchedule", cascade = CascadeType.ALL, orphanRemoval = false) // OneToMany CarToServiceSchedule reference, CarToServiceSchedule is owner reference (CarToServiceSchedule side is Many).
    @JsonIgnore
    private List<CarToServiceSchedule> carToServiceSchedules;

    public ServiceSchedule() {
    }

    public ServiceSchedule(int id, String name, boolean isRequired, int defaultPeriodKm, int defaultPeriodTimeDaysInt) {
        this.id = id;
        this.name = name;
        this.isRequired = isRequired;
        this.defaultPeriodKm = defaultPeriodKm;
        this.defaultPeriodTimeDays = Duration.ofDays(defaultPeriodTimeDaysInt);
    }

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

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        this.isRequired = required;
    }

    public int getDefaultPeriodKm() {
        return defaultPeriodKm;
    }

    public void setDefaultPeriodKm(int defaultPeriodKm) {
        this.defaultPeriodKm = defaultPeriodKm;
    }

    public Duration getDefaultPeriodTimeDays() {
        return defaultPeriodTimeDays;
    }

    public void setDefaultPeriodTimeDays(Duration defaultPeriodTimeDays) {
        this.defaultPeriodTimeDays = defaultPeriodTimeDays;
    }

    public List<CarToServiceSchedule> getCarToServiceSchedules() {
        return carToServiceSchedules;
    }

    public void setCarToServiceSchedules(List<CarToServiceSchedule> carToServiceSchedules) {
        this.carToServiceSchedules = carToServiceSchedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceSchedule that = (ServiceSchedule) o;
        return id == that.id && isRequired == that.isRequired && defaultPeriodKm == that.defaultPeriodKm && Objects.equals(name, that.name) && Objects.equals(defaultPeriodTimeDays, that.defaultPeriodTimeDays) && Objects.equals(carToServiceSchedules, that.carToServiceSchedules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isRequired, defaultPeriodKm, defaultPeriodTimeDays, carToServiceSchedules);
    }
}

