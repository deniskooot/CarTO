package com.github.Denis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

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
    @PositiveOrZero(message = "Millage must bee >=0")
    private int mileage;
    @Nullable
    private String notes;

//    fetch = FetchType.LAZY говорит, что это ленивая инициализация. То есть данные из таблицы address будут загружаться по этому ключу только в том случае, когда к ним обратятся.


    //    @JoinTable(name = "CarUser", joinColumns = @JoinColumn(name = "user_id"))
//    @JoinColumn(name = "user_id", referencedColumnName = "id") // Указываем колонку связи
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id") // Указываем колонку связи referencedColumnName - колонка в базе
    private CarUser carUser;


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
    public boolean isBad(){
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

//    public int getUserId() {
//        return userId;
//    }

    public int getMileage() {
        return mileage;
    }
    public void setMileage(int milage){
        this.mileage = milage;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
 public CarUser getCarUser(){
        return carUser;
 }
// public void setCarUser(CarUser carUser){
//        this.carUser = carUser;
// }

//    public void setUserId(int userId) {
//        this.userId = userId;
//    }


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

