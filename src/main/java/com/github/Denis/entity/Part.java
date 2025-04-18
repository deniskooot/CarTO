package com.github.Denis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("part_number_original")
    private String partNumberOriginal;
    @JsonProperty("part_number_analogs")
    private String partNumberAnalogs;
    private String notes;

    //    OneToMany CarToServiceSchedule reference, Part is owner reference (Part side is Many).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_to_service_schedule_id", referencedColumnName = "car_to_service_schedule_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private CarToServiceSchedule carToServiceSchedule;

    Part() {

    }

    Part(int id, String name, String partNumberOriginal, String partNumberAnalogs, String notes) {
        this.id = id;
        this.name = name;
        this.partNumberOriginal = partNumberOriginal;
        this.partNumberAnalogs = partNumberAnalogs;
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

    public String getPartNumberOriginal() {
        return partNumberOriginal;
    }

    public void setPartNumberOriginal(String partNumberOriginal) {
        this.partNumberOriginal = partNumberOriginal;
    }

    public String getPartNumberAnalogs() {
        return partNumberAnalogs;
    }

    public void setPartNumberAnalogs(String partNumberAnalogs) {
        this.partNumberAnalogs = partNumberAnalogs;
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
                ", part_number_original='" + partNumberOriginal + '\'' +
                ", part_number_analogs='" + partNumberAnalogs + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return id == part.id && Objects.equals(name, part.name) && Objects.equals(partNumberOriginal, part.partNumberOriginal) && Objects.equals(partNumberAnalogs, part.partNumberAnalogs) && Objects.equals(notes, part.notes) && Objects.equals(carToServiceSchedule, part.carToServiceSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, partNumberOriginal, partNumberAnalogs, notes, carToServiceSchedule);
    }
}
