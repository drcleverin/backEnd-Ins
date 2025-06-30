// src/main/java/com/example/policyapp/entity/Vehicle.java
package com.insurance.InsuranceApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing the 'vehicles' table in the database.
 */
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "registration_number", unique = true, nullable = false, length = 20)
    private String registrationNumber;

//    @Column(name = "make", nullable = false, length = 100)
//    private String make;

    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "fuel_type", nullable = false, length = 50)
    private String fuelType; // e.g., Petrol, Diesel, Electric

    @Column(name = "chassis_number", unique = true, length = 100)
    private String chassisNumber;

    @Column(name = "engine_number", unique = true, length = 100)
    private String engineNumber;

//    @Column(name = "user_id") // Foreign key to the user who owns this vehicle (can be null if not yet associated)
//    private Long userId;

    // Constructors
    public Vehicle() {
    }

    public Vehicle(String registrationNumber, String model, Integer year, String fuelType, String chassisNumber, String engineNumber) {
        this.registrationNumber = registrationNumber;
//        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.chassisNumber = chassisNumber;
        this.engineNumber = engineNumber;
//        this.userId = userId;
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

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", registrationNumber='" + registrationNumber + '\'' +
//                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", fuelType='" + fuelType + '\'' +
                ", chassisNumber='" + chassisNumber + '\'' +
                ", engineNumber='" + engineNumber + '\'' +
//                ", userId=" + userId +
                '}';
    }
}
