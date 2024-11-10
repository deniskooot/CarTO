package com.github.Denis;

import javax.persistence.*;

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

//TODO:

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Auto> autos;


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

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }


    @Override
    public String toString() {
        return "CarUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

//    public CarUser(String name, int age) {
//        this.name = name;
//        this.age = age;
//        autos = new ArrayList<>();
//    }
//    public void addAuto(Auto auto) {
//        auto.setUser(this);
//        autos.add(auto);
//    }
//    public void removeAuto(Auto auto) {
//        autos.remove(auto);
//    }

//    public void setName(String name) {
//        this.name = name;
//    }
//    public int getAge() {
//        return age;
//    }
//    public void setAge(int age) {
//        this.age = age;
//    }
//    public List<Auto> getAutos() {
//        return autos;
//    }

//    public void setAutos(List<Auto> autos) {
//        this.autos = autos;
//    }
}

