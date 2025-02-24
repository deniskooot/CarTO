package com.github.Denis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "car_users")
public class CarUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @NotBlank(message = "Select name")
    @Column(name = "name")
    private String name;
    //можно не указывать Column name, если оно совпадает с названием столбца в таблице

    //SERIAL PRIMARY KEY, name VARCHAR(50)

    // mapped by указывает на поле в данном классе для связи
    // orphanRemoval - с английского — "удалять сирот". Если мы удалим юзера из БД — все связанные с ним автомобили также будут удалены.
//    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)

//    private List<Car> cars;

//
@OneToMany(mappedBy = "carUser", cascade = CascadeType.ALL, orphanRemoval = false)
@JsonIgnore
private List<Car> cars = new ArrayList<>();

    public CarUser() {
    }

    public CarUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarUser carUser = (CarUser) o;
        return id == carUser.id && Objects.equals(name, carUser.name) && Objects.equals(cars, carUser.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cars);
    }
}

