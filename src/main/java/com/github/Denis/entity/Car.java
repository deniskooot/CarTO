package com.github.Denis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

//    cars (car_id SERIAL PRIMARY KEY, name VARCHAR(50), user_id INT, mileage INT, notes VARCHAR(1000),
//    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES car_users (user_id);

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;
    @NotBlank(message = "Select name")
    private String name;
    @PositiveOrZero(message = "Mileage must bee >=0")
    private int mileage;
    //    @Nullable — не работает валидация на уровне Hibernate, лучше заменить на @Column(nullable = true).
    @Column(nullable = true)
    private String notes;

//    timestamptz(0) - время с часовой зоной, минимальная точность 0 - секунды без дробных знаков
    @Column(name = "start_date", columnDefinition = "timestamptz(0)")
    private ZonedDateTime startDate;

    @Column(name = "start_mileage")
    private int startMileage;

    @Column(name = "yearly_mileage")
    private int yearlyMileage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    // Указываем колонку связи referencedColumnName - колонка в базе
    private CarUser carUser;

    //    OneToMany CarToServiceSchedule reference, CarToServiceSchedule is owner reference (CarToServiceSchedule side is Many).
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<CarToServiceSchedule> carToServiceSchedules;


    public Car() {
    }

    public Car(int id, String name, int mileage, String notes, CarUser carUser) {
        this.id = id;
        this.name = name;
        //this.userId = userId;
        this.mileage = mileage;
        this.notes = notes;
        this.carUser = carUser;
    }

    @JsonIgnore // чтобы в результат GET не выводился (методы начинающиеся с is считаются полями по конвенции)
    @AssertFalse(message = "BMW millage >1000000 impossible")
    public boolean isBad() {
        return this.mileage > 1_000_000 && name.contains("BMW");
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

    public CarUser getCarUser() {
        return carUser;
    }

    public void setCarUser(CarUser carUser) {
        this.carUser = carUser;
    }

    public List<CarToServiceSchedule> getCarToServiceSchedules() {
        return carToServiceSchedules;
    }

    public void setCarToServiceSchedules(List<CarToServiceSchedule> carToServiceSchedules) {
        this.carToServiceSchedules = carToServiceSchedules;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int milage) {
        this.mileage = milage;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public int getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(int startMileage) {
        this.startMileage = startMileage;
    }

    public int getYearlyMileage() {
        return yearlyMileage;
    }

    public void setYearlyMileage(int yearlyMileage) {
        this.yearlyMileage = yearlyMileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && mileage == car.mileage && Objects.equals(name, car.name) && Objects.equals(notes, car.notes) && Objects.equals(carUser, car.carUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mileage, notes, carUser);
    }

    @Override
    public String toString() {
        return "CarUser{" +
                "id=" + id +
                ", name='" + name + '\'' +

                ", mileage='" + mileage + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }


//    {
//  "brandId": 123,
//  "brandName": "Bike",
//  "mileage": "45831 км",
//  "yearlyMileage": "7000 км",
//  "notes": "car_notes"
//}


}

