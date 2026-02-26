package com.github.Denis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

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
    @Column(nullable = true) // @Nullable — не работает валидация на уровне Hibernate, лучше заменить на @Column(nullable = true).
    private String notes;
    @Column(name = "start_date", columnDefinition = "timestamptz(0)") // timestamptz(0) - время с часовой зоной, минимальная точность 0 - секунды без дробных знаков
    private ZonedDateTime startDate;
    @Column(name = "start_mileage")
    private int startMileage;
    @Column(name = "yearly_mileage")
    private int yearlyMileage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id") // Указываем колонку связи referencedColumnName - колонка в базе
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private CarUser carUser;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = false) // OneToMany CarToServiceSchedule reference, CarToServiceSchedule is owner reference (CarToServiceSchedule side is Many).
    @JsonIgnore
    private List<CarToServiceSchedule> carToServiceSchedules;

    public Car() {
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

}

