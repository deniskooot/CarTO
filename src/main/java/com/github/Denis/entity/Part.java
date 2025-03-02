package com.github.Denis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

// parts (part_id SERIAL PRIMARY KEY, name VARCHAR(200), car_to_service_schedule_id INT, part_number_original VARCHAR(50), part_number_analogs VARCHAR(400), notes VARCHAR(1000),
//CONSTRAINT car_to_service_schedule_id_fk FOREIGN KEY (car_to_service_schedule_id) REFERENCES car_to_service_schedules (car_to_service_schedule_id);

@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_id")
    private int id;
    @NotBlank(message = "Select name")
    private String name;
    private String part_number_original;
    private String part_number_analogs;
    private String notes;

    //    OneToMany CarToServiceSchedule reference, Part is owner reference (Part side is Many).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_to_service_schedule_id", referencedColumnName = "car_to_service_schedule_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private CarToServiceSchedule carToServiceSchedule;

    Part() {

    }

    Part(int id, String name, String part_number_original, String part_number_analogs, String notes) {
        this.id = id;
        this.name = name;
        this.part_number_original = part_number_original;
        this.part_number_analogs = part_number_analogs;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Select name") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Select name") String name) {
        this.name = name;
    }

    public String getPart_number_original() {
        return part_number_original;
    }

    public void setPart_number_original(String part_number_original) {
        this.part_number_original = part_number_original;
    }

    public String getPart_number_analogs() {
        return part_number_analogs;
    }

    public void setPart_number_analogs(String part_number_analogs) {
        this.part_number_analogs = part_number_analogs;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public CarToServiceSchedule getCarToServiceSchedule() {
        return carToServiceSchedule;
    }

    public void setCarToServiceSchedule(CarToServiceSchedule carToServiceSchedule) {
        this.carToServiceSchedule = carToServiceSchedule;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", part_number_original='" + part_number_original + '\'' +
                ", part_number_analogs='" + part_number_analogs + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return id == part.id && Objects.equals(name, part.name) && Objects.equals(part_number_original, part.part_number_original) && Objects.equals(part_number_analogs, part.part_number_analogs) && Objects.equals(notes, part.notes) && Objects.equals(carToServiceSchedule, part.carToServiceSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, part_number_original, part_number_analogs, notes, carToServiceSchedule);
    }
}
