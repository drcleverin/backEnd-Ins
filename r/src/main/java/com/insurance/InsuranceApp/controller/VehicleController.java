package com.insurance.InsuranceApp.controller;

// src/main/java/com/example/policyapp/controller/VehicleController.java

import com.insurance.InsuranceApp.dto.VehicleDTO;
import com.insurance.InsuranceApp.model.Vehicle;
import com.insurance.InsuranceApp.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Vehicle-related operations.
 * Provides endpoints to retrieve vehicle details.
 */
@RestController
@RequestMapping("/api/vehicles") // Base path for vehicle APIs
@CrossOrigin("*")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Endpoint to retrieve vehicle details by its registration number.
     * Accessible via GET /api/vehicles/{registrationNumber}
     *
     * @param registrationNumber The registration number of the vehicle.
     * @return ResponseEntity containing the VehicleDTO if found, or 404 Not Found.
     */
    @GetMapping("/{registrationNumber}")
    public ResponseEntity<VehicleDTO> getVehicleByRegistrationNumber(@PathVariable String registrationNumber) {
        return vehicleService.getVehicleByRegistrationNumber(registrationNumber)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Endpoint to create a new vehicle.
     * This might be used by an admin or an initial data seeding process.
     * Accessible via POST /api/vehicles
     *
     * @param vehicle The Vehicle object to be saved.
     * @return ResponseEntity containing the saved Vehicle and HTTP status.
     */
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }
}
