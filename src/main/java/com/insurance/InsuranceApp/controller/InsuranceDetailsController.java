package com.insurance.InsuranceApp.controller;

import com.insurance.InsuranceApp.dto.ClaimList;
import com.insurance.InsuranceApp.services.InsuranceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for exposing aggregated insurance details.
 */
@RestController // Marks this class as a REST controller
@RequestMapping("/api/insurance") // Base path for all endpoints in this controller
@CrossOrigin("*")
public class InsuranceDetailsController {

    private final InsuranceDetailsService insuranceDetailsService;

    // Inject the service using constructor injection (recommended)
    @Autowired
    public InsuranceDetailsController(InsuranceDetailsService insuranceDetailsService) {
        this.insuranceDetailsService = insuranceDetailsService;
    }

    /**
     * Endpoint to fetch all aggregated claim details.
     * URL: GET /api/insurance/claims/aggregated
     * @return A list of AggregatedClaimDetailsDTO, or an empty list if no claims are found.
     */
    @GetMapping("/claims/aggregated")
    public ResponseEntity<List<ClaimList>> getAggregatedClaimDetails() {
        // Call the service layer to get the aggregated data
        List<ClaimList> aggregatedClaims = insuranceDetailsService.getAllAggregatedClaimDetails();

        // Return the list with an OK (200) status
        return ResponseEntity.ok(aggregatedClaims);
    }

    // You can add more endpoints here if needed, e.g., to get details for a specific claim ID
    // @GetMapping("/claims/aggregated/{claimId}")
    // public ResponseEntity<AggregatedClaimDetailsDTO> getAggregatedClaimDetailsById(@PathVariable Long claimId) {
    //     // ... logic to find a single claim and map it ...
    // }
}