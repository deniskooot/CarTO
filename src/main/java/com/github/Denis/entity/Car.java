package com.github.Denis.entity;

import jakarta.persistence.*;

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
//    @Column(name = "user_id")
//    private int userId;
//    Вы используете связь @ManyToOne между Car и CarUser. Однако в классе Car вы также сохраняете поле userId, которое дублирует информацию, содержащуюся в carUser. Это может привести к конфликтам и путанице.

    private int mileage;
    private String notes;

    //SERIAL PRIMARY KEY, name VARCHAR(50)

//    @ManyToOne() //(fetch = FetchType.LAZY)
//    @JoinColumn (name="user_id")
//    private CarUser carUser;

//    fetch = FetchType.LAZY говорит, что это ленивая инициализация. То есть данные из таблицы address будут загружаться по этому ключу только в том случае, когда к ним обратятся.

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn (name="user_id")
//    @JoinTable(name = "CarUser", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    @JoinTable(name = "CarUser", joinColumns = @JoinColumn(name = "user_id"))
//    @JoinColumn(name = "user_id", referencedColumnName = "id") // Указываем колонку связи
    @JoinColumn(name = "user_id", referencedColumnName = "user_id") // Указываем колонку связи


    private CarUser carUser;

    public Car() {
    }

    public Car(int id, String name, int mileage, String notes, CarUser carUser) {
//        this.id = id;
        this.name = name;
        //this.userId = userId;
        this.mileage = mileage;
        this.notes = notes;
        this.carUser = carUser;
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
    public String toString() {
        return "CarUser{" +
                "id=" + id +
                ", name='" + name + '\'' +

                ", mileage='" + mileage + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

}

