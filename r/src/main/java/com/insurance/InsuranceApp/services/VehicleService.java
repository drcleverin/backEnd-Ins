package com.insurance.InsuranceApp.services;

// src/main/java/com/example/policyapp/service/VehicleService.java


import com.insurance.InsuranceApp.dto.VehicleDTO;
import com.insurance.InsuranceApp.model.Vehicle;
import com.insurance.InsuranceApp.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service layer for managing vehicle-related business logic.
 */
@Service
public class VehicleService {

    private final com.insurance.InsuranceApp.repository.VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Retrieves vehicle details by registration number.
     *
     * @param registrationNumber The registration number of the vehicle.
     * @return An Optional containing the VehicleDTO if found, empty otherwise.
     */
    public Optional<com.insurance.InsuranceApp.dto.VehicleDTO> getVehicleByRegistrationNumber(String registrationNumber) {
        return vehicleRepository.findByRegistrationNumber(registrationNumber)
                .map(this::convertToDto);
    }

    /**
     * Saves a new vehicle or updates an existing one.
     *
     * @param vehicle The Vehicle entity to save.
     * @return The saved or updated Vehicle entity.
     */
    public com.insurance.InsuranceApp.model.Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    /**
     * Helper method to convert a Vehicle entity to a VehicleDTO.
     *
     * @param vehicle The Vehicle entity to convert.
     * @return A populated VehicleDTO.
     */
    private VehicleDTO convertToDto(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleId(vehicle.getVehicleId());
        dto.setRegistrationNumber(vehicle.getRegistrationNumber());
//        dto.setMake(vehicle.getMake());
        dto.setModel(vehicle.getModel());
        dto.setYear(vehicle.getYear());
        dto.setFuelType(vehicle.getFuelType());
        dto.setChassisNumber(vehicle.getChassisNumber());
        dto.setEngineNumber(vehicle.getEngineNumber());
        return dto;
    }
}
