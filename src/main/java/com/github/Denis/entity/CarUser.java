package com.github.Denis.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_users")
public class CarUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "name")
    private String name;
    //можно не указывать Column name, если оно совпадает с названием столбца в таблице

    //SERIAL PRIMARY KEY, name VARCHAR(50)

    // mapped by указывает на поле в данном классе для связи
    // orphanRemoval - с английского — "удалять сирот". Если мы удалим юзера из БД — все связанные с ним автомобили также будут удалены.
//    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)

//    private List<Car> cars;


@OneToMany(mappedBy = "carUser", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Car> cars;

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
}

