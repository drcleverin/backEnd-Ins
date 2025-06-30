package com.insurance.InsuranceApp.controller;

//package com.insurance.InsuranceApp.controller;

import com.insurance.InsuranceApp.dto.ClaimDTO;
//import com.insurance.InsuranceApp.service.ClaimService;
import com.insurance.InsuranceApp.services.ClaimService;
import jakarta.validation.Valid; // Ensure this import is present for @Valid
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing insurance claims.
 * Provides endpoints for creating, retrieving single, and retrieving claims by policy.
 */
@RestController // Marks this class as a Spring REST Controller
@RequestMapping("/api/claims") // Base URL for all endpoints in this controller
@CrossOrigin("*")
public class ClaimController {

    private final ClaimService claimService; // Inject the ClaimService

    /**
     * Constructor injection for ClaimService.
     * @param claimService The service responsible for claim business logic.
     */
    @Autowired
    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    /**
     * Handles POST requests to create a new claim.
     * The @Valid annotation triggers validation on the incoming ClaimDTO object.
     *
     * @param claimDTO The Data Transfer Object containing claim details from the request body.
     * @return A ResponseEntity containing the created ClaimDTO and HTTP status CREATED.
     */
    @PostMapping("/addClaim")
    public ResponseEntity<ClaimDTO> createClaim(@Valid @RequestBody ClaimDTO claimDTO) {
        ClaimDTO createdClaim = claimService.createClaim(claimDTO);
        // Return 201 Created status with the newly created claim details
        return new ResponseEntity<>(createdClaim, HttpStatus.CREATED);
    }

    /**
     * Handles GET requests to retrieve a claim by its ID.
     *
     * @param id The ID of the claim to retrieve, extracted from the URL path.
     * @return A ResponseEntity containing the ClaimDTO if found, and HTTP status OK.
     * If the claim is not found, the service layer will throw an exception
     * which can be handled by a global exception handler.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClaimDTO> getClaimById(@PathVariable Long id) {
        ClaimDTO claimDTO = claimService.getClaimById(id);
        // Return 200 OK status with the retrieved claim details
        return ResponseEntity.ok(claimDTO);
    }

    /**
     * Handles GET requests to retrieve all claims associated with a specific policy ID.
     *
     * @param policyId The ID of the policy, extracted from the URL path.
     * @return A ResponseEntity containing a list of ClaimDTOs and HTTP status OK.
     */
    @GetMapping("/policy/{policyId}")
    public ResponseEntity<List<ClaimDTO>> getClaimsByPolicyId(@PathVariable Long policyId) {
        List<ClaimDTO> claims = claimService.getClaimsByPolicyId(policyId);
        // Return 200 OK status with the list of claims
        return ResponseEntity.ok(claims);
    }

    // You can add more endpoints here for updating (PUT/PATCH) and deleting (DELETE) claims.
    // Example for updating:
    // @PutMapping("/{id}")
    // public ResponseEntity<ClaimDTO> updateClaim(@PathVariable Long id, @Valid @RequestBody ClaimDTO claimDTO) {
    //     // You would need an updateClaim method in your service
    //     ClaimDTO updatedClaim = claimService.updateClaim(id, claimDTO);
    //     return ResponseEntity.ok(updatedClaim);
    // }

    // Example for deleting:
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteClaim(@PathVariable Long id) {
    //     // You would need a deleteClaim method in your service
    //     claimService.deleteClaim(id);
    //     return ResponseEntity.noContent().build(); // 204 No Content
    // }
}
