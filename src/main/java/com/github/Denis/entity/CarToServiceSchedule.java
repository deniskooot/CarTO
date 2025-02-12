package com.github.Denis.entity;

import jakarta.persistence.*;

import java.time.Duration;

@Entity
@Table(name = "car_to_service_schedules")

public class CarToServiceSchedule {

// TABLE car_to_service_schedules
// (car_to_service_schedule_id SERIAL PRIMARY KEY, service_schedule_id INT, car_id INT, periodicity_km INT, periodicity_time_days INTERVAL DAY, notes VARCHAR(200),
// FOREIGN KEY (service_shedule_id)
// REFERENCES service_shedule (service_shedule_id), FOREIGN KEY (car_id) REFERENCES cars (car_id));

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "car_to_service_schedule_id")
        private int id;
        private int periodicity_km;
        private Duration periodicity_time_days;
        private String notes;

//    service_schedule_id
//    car_id

//        @ManyToOne() //(fetch = FetchType.LAZY)
//        @JoinColumn (name="user_id")
//        private CarUser carUser;

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

    public void setPeriodicity_time_days(int periodicity_time_days_int) {
        this.periodicity_time_days = Duration.ofDays(periodicity_time_days_int);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
}
