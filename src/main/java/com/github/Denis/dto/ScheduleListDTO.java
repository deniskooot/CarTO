package com.github.Denis.dto;

import com.github.Denis.entity.Part;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * DTO class for list of schedules for frontend
 *
 */
public class ScheduleListDTO {

    private String scheduleName; // Наименование работы
    private Integer scheduleMileageKm; // Пробег
    private ZonedDateTime scheduleDate; // Дата
    private String scheduleNotes; // Примечание
    private Boolean scheduleIsRequired; // * - обязательна ли работа?
    private List<Part> scheduleParts;

//    @JsonProperty("id")
//    private Integer id;
//    @JsonProperty("car_to_service_schedule_id")
//    private Integer carToServiceScheduleId;

    public ScheduleListDTO() {

    }

    public ScheduleListDTO(String scheduleName, Integer scheduleMileageKm, ZonedDateTime scheduleDate, String scheduleNotes, Boolean scheduleIsRequired, List<Part> scheduleParts) {
        this.scheduleName = scheduleName;
        this.scheduleMileageKm = scheduleMileageKm;
        this.scheduleDate = scheduleDate;
        this.scheduleNotes = scheduleNotes;
        this.scheduleIsRequired = scheduleIsRequired;
        this.scheduleParts = scheduleParts;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public Integer getScheduleMileageKm() {
        return scheduleMileageKm;
    }

    public void setScheduleMileageKm(Integer scheduleMileageKm) {
        this.scheduleMileageKm = scheduleMileageKm;
    }

    public ZonedDateTime getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(ZonedDateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleNotes() {
        return scheduleNotes;
    }

    public void setScheduleNotes(String scheduleNotes) {
        this.scheduleNotes = scheduleNotes;
    }

    public Boolean getScheduleIsRequired() {
        return scheduleIsRequired;
    }

    public void setScheduleIsRequired(Boolean scheduleIsRequired) {
        this.scheduleIsRequired = scheduleIsRequired;
    }

    public List<Part> getScheduleParts() {
        return scheduleParts;
    }

    public void setScheduleParts(List<Part> scheduleParts) {
        this.scheduleParts = scheduleParts;
    }
}
