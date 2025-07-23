package com.github.Denis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Denis.converter.DurationJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "car_to_service_schedules")
public class CarToServiceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_to_service_schedule_id")
    private int id;
    @JsonProperty("periodicity_km")
    private int periodicityKm;
    @Schema(type = "integer", format = "int64", example = "10")

    @JsonSerialize(using = DurationJsonConverter.DurationSerializer.class)
    @JsonDeserialize(using = DurationJsonConverter.DurationDeserializer.class)
    @JsonProperty("periodicity_time_days")
    private Duration periodicityTimeDays;
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne Car reference, CarToServiceSchedule is owner reference (CarToServiceSchedule side is Many).
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne ServiceSchedule reference, CarToServiceSchedule is owner reference (CarToServiceSchedule side is Many).
    @JoinColumn(name = "service_schedule_id", referencedColumnName = "service_schedule_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private ServiceSchedule serviceSchedule;

    @OneToMany(mappedBy = "carToServiceSchedule", cascade = CascadeType.ALL, orphanRemoval = false) // OneToMany ServiceOperation reference, ServiceOperation is owner reference (ServiceOperation side is Many).
    @JsonIgnore
    private List<ServiceOperation> serviceOperations;

    @OneToMany(mappedBy = "carToServiceSchedule", cascade = CascadeType.ALL, orphanRemoval = false) // OneToMany Part reference, Part is owner reference (Part side is Many).
    @JsonIgnore
    private List<Part> parts;

    public CarToServiceSchedule() {
    }

    public CarToServiceSchedule(int id, int periodicityKm, int periodicity_time_days_int, String notes) {
        this.id = id;
        this.periodicityKm = periodicityKm;
        this.periodicityTimeDays = Duration.ofDays(periodicity_time_days_int);
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeriodicityKm() {
        return periodicityKm;
    }

    public void setPeriodicityKm(int periodicityKm) {
        this.periodicityKm = periodicityKm;
    }

    public Duration getPeriodicityTimeDays() {
        return periodicityTimeDays;
    }

    public void setPeriodicityTimeDays(Duration periodicityTimeDays) {
        this.periodicityTimeDays = periodicityTimeDays;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ServiceSchedule getServiceSchedule() {
        return serviceSchedule;
    }

    public void setServiceSchedule(ServiceSchedule serviceSchedule) {
        this.serviceSchedule = serviceSchedule;
    }

    public List<ServiceOperation> getServiceOperations() {
        return serviceOperations;
    }

    public void setServiceOperations(List<ServiceOperation> serviceOperations) {
        this.serviceOperations = serviceOperations;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return "CarToServiceSchedule{" +
                "id=" + id +
                ", periodicity_km=" + periodicityKm +
                ", periodicity_time_days=" + periodicityTimeDays +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarToServiceSchedule that = (CarToServiceSchedule) o;
        return id == that.id && periodicityKm == that.periodicityKm && Objects.equals(periodicityTimeDays, that.periodicityTimeDays) && Objects.equals(notes, that.notes) && Objects.equals(car, that.car) && Objects.equals(serviceSchedule, that.serviceSchedule) && Objects.equals(serviceOperations, that.serviceOperations) && Objects.equals(parts, that.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, periodicityKm, periodicityTimeDays, notes, car, serviceSchedule, serviceOperations, parts);
    }
}
