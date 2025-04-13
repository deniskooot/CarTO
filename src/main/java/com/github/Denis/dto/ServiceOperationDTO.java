package com.github.Denis.dto;

import java.util.Date;

public class ServiceOperationDTO {

//        @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "service_operation_id")
    private Integer id;
//    @PositiveOrZero(message = "Millage must bee >=0")
    private Integer mileageServiceOperation;
//    //    Temporal data can have DATE, TIME, or TIMESTAMP precision. Use the @Temporal annotation to fine tune that.
//    @Temporal(TemporalType.DATE)
    private Date dateServiceOperation;
    private String notes;
//
//    //    OneToMany CarToServiceSchedule reference, ServiceOperation is owner reference (ServiceOperation side is Many).
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "car_to_service_schedule_id", referencedColumnName = "car_to_service_schedule_id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Integer carToServiceScheduleId;
//    private CarToServiceSchedule carToServiceSchedule;

    public ServiceOperationDTO(){

    }

    public ServiceOperationDTO(Integer id, Integer mileageServiceOperation, Date dateServiceOperation, String notes, Integer carToServiceScheduleId) {
        this.id = id;
        this.mileageServiceOperation = mileageServiceOperation;
        this.dateServiceOperation = dateServiceOperation;
        this.notes = notes;
        this.carToServiceScheduleId = carToServiceScheduleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMileageServiceOperation() {
        return mileageServiceOperation;
    }

    public void setMileageServiceOperation(Integer mileageServiceOperation) {
        this.mileageServiceOperation = mileageServiceOperation;
    }

    public Date getDateServiceOperation() {
        return dateServiceOperation;
    }

    public void setDateServiceOperation(Date dateServiceOperation) {
        this.dateServiceOperation = dateServiceOperation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getCarToServiceScheduleId() {
        return carToServiceScheduleId;
    }

    public void setCarToServiceScheduleId(Integer carToServiceScheduleId) {
        this.carToServiceScheduleId = carToServiceScheduleId;
    }
}
