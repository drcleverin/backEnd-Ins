package com.insurance.InsuranceApp.repository;

/// src/main/java/com/example/policyapp/repository/VehicleRepository.java

import com.insurance.InsuranceApp.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for Vehicle entities.
 * Provides methods for CRUD operations and custom queries.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    /**
     * Finds a Vehicle by its registration number.
     *
     * @param registrationNumber The registration number of the vehicle.
     * @return An Optional containing the Vehicle if found, or empty otherwise.
     */
    Optional<com.insurance.InsuranceApp.model.Vehicle> findByRegistrationNumber(String registrationNumber);
}
