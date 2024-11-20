package com.github.Denis;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    // car_id SERIAL PRIMARY KEY, name VARCHAR(50), user_id INT, mileage INT, notes VARCHAR(1000),
    // FOREIGN KEY (user_id) REFERENCES  car_user(user_id)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;
    private String name;
    @Column(name = "user_id")
    private int userId;
    private int mileage;
    private String notes;

    //SERIAL PRIMARY KEY, name VARCHAR(50)

    @ManyToOne() //(fetch = FetchType.LAZY)
    @JoinColumn (name="user_id")
    private CarUser carUser;


    public Car() {
    }

    public Car(int id, String name, int userId, int mileage, String notes) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.mileage = mileage;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public int getMileage() {
        return mileage;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMileage(int milage){
        this.mileage = milage;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "CarUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "userId='" + userId + '\'' +
                "mileage='" + mileage + '\'' +
                "notes='" + notes + '\'' +
                '}';
    }

}

