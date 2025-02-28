package com.github.Denis.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.Denis.converter.DurationJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.Duration;

// car_to_service_schedules (car_to_service_schedule_id SERIAL PRIMARY KEY, service_schedule_id INT, car_id INT, periodicity_km INT, periodicity_time_days INTERVAL DAY, notes VARCHAR(200),
//        CONSTRAINT service_schedule_id_fk FOREIGN KEY (service_schedule_id) REFERENCES service_schedules (service_schedule_id),
//        CONSTRAINT car_id_fk FOREIGN KEY (car_id) REFERENCES cars (car_id);

@Entity
@Table(name = "car_to_service_schedules")

public class CarToServiceSchedule {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "car_to_service_schedule_id")
        private int id;
        private int periodicity_km;
        /*@JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)*/
        @Schema(type = "integer", format = "int64", example = "10")
        @JsonSerialize(using = DurationJsonConverter.DurationSerializer.class)
        @JsonDeserialize(using = DurationJsonConverter.DurationDeserializer.class)
        private Duration periodicity_time_days;
        private String notes;

//    service_schedule_id
//    car_id

//        @ManyToOne() //(fetch = FetchType.LAZY)
//        @JoinColumn (name="user_id")
//        private CarUser carUser;

        public CarToServiceSchedule() {
        }

        public CarToServiceSchedule(int id, int periodicity_km, int periodicity_time_days_int, String notes) {
            this.id = id;
            this.periodicity_km = periodicity_km;
            this.periodicity_time_days = Duration.ofDays(periodicity_time_days_int);
            this.notes = notes;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeriodicity_km() {
        return periodicity_km;
    }

    public void setPeriodicity_km(int periodicity_km) {
        this.periodicity_km = periodicity_km;
    }

    public Duration getPeriodicity_time_days() {
        return periodicity_time_days;
    }

    public void setPeriodicity_time_days(Duration periodicity_time_days) {
        this.periodicity_time_days = periodicity_time_days;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "CarToServiceSchedule{" +
                "id=" + id +
                ", periodicity_km=" + periodicity_km +
                ", periodicity_time_days=" + periodicity_time_days +
                ", notes='" + notes + '\'' +
                '}';
    }
    /*public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
            arg1.writeString(arg0.toString());
        }
    }

    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException {
            return LocalDateTime.parse(arg0.getText());
        }
    }*/
}
