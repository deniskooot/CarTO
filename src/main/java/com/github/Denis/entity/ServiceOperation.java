package com.github.Denis.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "service_operations")
public class ServiceOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_operation_id")
    private int id;
    private int mileage_service_operation;
    //    Temporal data can have DATE, TIME, or TIMESTAMP precision. Use the @Temporal annotation to fine tune that.
    @Temporal(TemporalType.DATE)
    private Date date_service_operation;
    private String notes;

//    car_to_service_schedule_id

//  TABLE service_operations
//  (service_operation_id SERIAL PRIMARY KEY, car_to_service_schedule_id INT, mileage_service_operation INT, date_service_operation DATE, notes VARCHAR(1000),
//  FOREIGN KEY (car_to_service_schedule_id) REFERENCES car_to_service_schedule (car_to_service_schedule_id));

    ServiceOperation() {
    }

    ServiceOperation(int id, int mileage_service_operation, Date date_service_operation, String notes) {
        this.id = id;
        this.mileage_service_operation = mileage_service_operation;
        this.date_service_operation = date_service_operation;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMileage_service_operation() {
        return mileage_service_operation;
    }

    public void setMileage_service_operation(int mileage_service_operation) {
        this.mileage_service_operation = mileage_service_operation;
    }

    public Date getDate_service_operation() {
        return date_service_operation;
    }

    public void setDate_service_operation(Date date_service_operation) {
        this.date_service_operation = date_service_operation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "ServiceOperation{" +
                "id=" + id +
                ", mileage_service_operation=" + mileage_service_operation +
                ", date_service_operation=" + date_service_operation +
                ", notes='" + notes + '\'' +
                '}';
    }
}
