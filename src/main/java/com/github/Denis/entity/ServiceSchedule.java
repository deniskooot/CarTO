package com.github.Denis.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "service_schedules")
public class ServiceSchedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "service_schedule_id")
  private Integer id;

  @NotBlank(message = "Select name")
  private String name;

  @JsonProperty("is_required")
  private boolean isRequired;

  @PositiveOrZero(message = "Default_period must bee >=0")
  @JsonProperty("default_period_km")
  private Integer defaultPeriodKm;

  @Schema(type = "integer", format = "int64")
  @Column(name = "default_period_time_days")
  @JsonProperty("default_period_time_days")
  private Duration defaultPeriodTimeDays; // (INTERVAL '1095' DAY)

  @OneToMany(
      mappedBy = "serviceSchedule",
      cascade = CascadeType.ALL,
      orphanRemoval =
          false) // OneToMany CarToServiceSchedule reference, CarToServiceSchedule is owner
  // reference (CarToServiceSchedule side is Many).
  @JsonIgnore
  private List<CarToServiceSchedule> carToServiceSchedules;

  public ServiceSchedule() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isRequired() {
    return isRequired;
  }

  public void setRequired(boolean required) {
    this.isRequired = required;
  }

  public Integer getDefaultPeriodKm() {
    return defaultPeriodKm;
  }

  public void setDefaultPeriodKm(Integer defaultPeriodKm) {
    this.defaultPeriodKm = defaultPeriodKm;
  }

  @JsonIgnore
  public Duration getDefaultPeriod() {
    return defaultPeriodTimeDays;
  }

  @JsonGetter("default_period_time_days")
  public Long getDefaultPeriodTimeDays() {
    return defaultPeriodTimeDays == null ? null : defaultPeriodTimeDays.toDays();
  }

  @JsonSetter("default_period_time_days")
  public void setDefaultPeriodTimeDays(Long defaultPeriodTimeDays) {
    if (defaultPeriodTimeDays != null) {
      this.defaultPeriodTimeDays = Duration.ofDays(defaultPeriodTimeDays);
    } else {
      this.defaultPeriodTimeDays = null;
    }
  }

  public List<CarToServiceSchedule> getCarToServiceSchedules() {
    return carToServiceSchedules;
  }

  public void setCarToServiceSchedules(List<CarToServiceSchedule> carToServiceSchedules) {
    this.carToServiceSchedules = carToServiceSchedules;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ServiceSchedule that = (ServiceSchedule) o;
    return Objects.equals(id, that.id)
        && isRequired == that.isRequired
        && Objects.equals(defaultPeriodKm, that.defaultPeriodKm)
        && Objects.equals(name, that.name)
        && Objects.equals(defaultPeriodTimeDays, that.defaultPeriodTimeDays)
        && Objects.equals(carToServiceSchedules, that.carToServiceSchedules);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, name, isRequired, defaultPeriodKm, defaultPeriodTimeDays, carToServiceSchedules);
  }
}
