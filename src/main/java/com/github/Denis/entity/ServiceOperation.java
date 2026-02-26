package com.github.Denis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "service_operations")
public class ServiceOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_operation_id")
    private int id;
    @PositiveOrZero(message = "Millage must bee >=0")
    @JsonProperty("mileage_service_operation")
    private int mileageServiceOperation;
    @JsonProperty("date_service_operation")
    @Temporal(TemporalType.DATE) // Temporal data can have DATE, TIME, or TIMESTAMP precision. Use the @Temporal annotation to fine tune that.
    private Date dateServiceOperation;
    @Column
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY) // OneToMany CarToServiceSchedule reference, ServiceOperation is owner reference (ServiceOperation side is Many).
    @JoinColumn(name = "car_to_service_schedule_id", referencedColumnName = "car_to_service_schedule_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private CarToServiceSchedule carToServiceSchedule;

    public ServiceOperation() {
    }

    ServiceOperation(int id, int mileageServiceOperation, Date dateServiceOperation, String notes) {
        this.id = id;
        this.mileageServiceOperation = mileageServiceOperation;
        this.dateServiceOperation = dateServiceOperation;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMileageServiceOperation() {
        return mileageServiceOperation;
    }

    public void setMileageServiceOperation(int mileageServiceOperation) {
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

    public CarToServiceSchedule getCarToServiceSchedule() {
        return carToServiceSchedule;
    }

    public void setCarToServiceSchedule(CarToServiceSchedule carToServiceSchedule) {
        this.carToServiceSchedule = carToServiceSchedule;
    }

    @Override
    public String toString() {
        return "ServiceOperation{" +
                "id=" + id +
                ", mileage_service_operation=" + mileageServiceOperation +
                ", date_service_operation=" + dateServiceOperation +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOperation that = (ServiceOperation) o;
        return id == that.id && mileageServiceOperation == that.mileageServiceOperation && Objects.equals(dateServiceOperation, that.dateServiceOperation) && Objects.equals(notes, that.notes) && Objects.equals(carToServiceSchedule, that.carToServiceSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mileageServiceOperation, dateServiceOperation, notes, carToServiceSchedule);
    }
}
