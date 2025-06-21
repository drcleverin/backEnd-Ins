package com.insurance.InsuranceApp.dto;

// src/main/java/com/example/policyapp/dto/VehicleDTO.java

/**
 * DTO for Vehicle details, to be sent to the frontend.
 * Omits sensitive data and includes only necessary fields for display.
 */
public class VehicleDTO {

    private Long vehicleId;
    private String registrationNumber;
    private String make;
    private String model;
    private Integer year;
    private String fuelType;
    private String chassisNumber; // Can be included if needed for display
    private String engineNumber;  // Can be included if needed for display

    // Constructors
    public VehicleDTO() {
    }

    public VehicleDTO(Long vehicleId, String registrationNumber, String model, Integer year, String fuelType, String chassisNumber, String engineNumber) {
        this.vehicleId = vehicleId;
        this.registrationNumber = registrationNumber;
//        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.chassisNumber = chassisNumber;
        this.engineNumber = engineNumber;
    }

    // Getters and Setters
    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

//    public String getMake() {
//        return make;
//    }
//
//    public void setMake(String make) {
//        this.make = make;
//    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "vehicleId=" + vehicleId +
                ", registrationNumber='" + registrationNumber + '\'' +
//                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", fuelType='" + fuelType + '\'' +
                ", chassisNumber='" + chassisNumber + '\'' +
                ", engineNumber='" + engineNumber + '\'' +
                '}';
    }
}
