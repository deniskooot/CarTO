package com.github.Denis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Denis.converter.DurationJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

// car_to_service_schedules (car_to_service_schedule_id SERIAL PRIMARY KEY, service_schedule_id INT, car_id INT, periodicity_km INT, periodicity_time_days INTERVAL DAY, notes VARCHAR(200),
//        CONSTRAINT service_schedule_id_fk FOREIGN KEY (service_schedule_id) REFERENCES service_schedules (service_schedule_id),
//        CONSTRAINT car_id_fk FOREIGN KEY (car_id) REFERENCES cars (car_id);

@Entity
@Table(name = "car_to_service_schedules")

public class CarToServiceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_to_service_schedule_id")
    private int id;
    private int periodicity_km;
    /*@JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)*/
    @Schema(type = "integer", format = "int64", example = "10")
    @JsonSerialize(using = DurationJsonConverter.DurationSerializer.class)
    @JsonDeserialize(using = DurationJsonConverter.DurationDeserializer.class)
    private Duration periodicity_time_days;
    private String notes;

    //    ManyToOne Car reference, CarToServiceSchedule is owner reference (CarToServiceSchedule side is Many).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Car car;

    //    ManyToOne ServiceSchedule reference, CarToServiceSchedule is owner reference (CarToServiceSchedule side is Many).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_schedule_id", referencedColumnName = "service_schedule_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private ServiceSchedule serviceSchedule;

    //    OneToMany ServiceOperation reference, ServiceOperation is owner reference (ServiceOperation side is Many).
    @OneToMany(mappedBy = "carToServiceSchedule", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<ServiceOperation> serviceOperations; //= new ArrayList<>();

    //    OneToMany Part reference, Part is owner reference (Part side is Many).
    @OneToMany(mappedBy = "carToServiceSchedule", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<Part> parts; // = new ArrayList<>();


    public CarToServiceSchedule() {
    }

    public CarToServiceSchedule(int id, int periodicity_km, int periodicity_time_days_int, String notes) {
        this.id = id;
        this.periodicity_km = periodicity_km;
        this.periodicity_time_days = Duration.ofDays(periodicity_time_days_int);
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeriodicity_km() {
        return periodicity_km;
    }

    public void setPeriodicity_km(int periodicity_km) {
        this.periodicity_km = periodicity_km;
    }

    public Duration getPeriodicity_time_days() {
        return periodicity_time_days;
    }

    public void setPeriodicity_time_days(Duration periodicity_time_days) {
        this.periodicity_time_days = periodicity_time_days;
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
                ", periodicity_km=" + periodicity_km +
                ", periodicity_time_days=" + periodicity_time_days +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarToServiceSchedule that = (CarToServiceSchedule) o;
        return id == that.id && periodicity_km == that.periodicity_km && Objects.equals(periodicity_time_days, that.periodicity_time_days) && Objects.equals(notes, that.notes) && Objects.equals(car, that.car) && Objects.equals(serviceSchedule, that.serviceSchedule) && Objects.equals(serviceOperations, that.serviceOperations) && Objects.equals(parts, that.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, periodicity_km, periodicity_time_days, notes, car, serviceSchedule, serviceOperations, parts);
    }
}
