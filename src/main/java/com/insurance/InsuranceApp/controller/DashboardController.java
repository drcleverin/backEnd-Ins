//// src/main/java/com/example/policyapp/controller/DashboardController.java
//package com.insurance.InsuranceApp.controller;
//
//import com.insurance.InsuranceApp.dto.PolicyResponseDTO;
//import com.insurance.InsuranceApp.services.DashboardService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * REST Controller for Dashboard-related policy data.
// * Provides endpoints to list all policies and get details for a specific policy.
// */
//@RestController
//@RequestMapping("/api/dashboard") // Base path for dashboard related APIs
//@CrossOrigin("*")
//public class DashboardController {
//
//    private final DashboardService dashboardService;
//
//    @Autowired
//    public DashboardController(DashboardService dashboardService) {
//        this.dashboardService = dashboardService;
//    }
//
//    /**
//     * Endpoint to retrieve a list of all policies with their associated plan details.
//     * Accessible via GET /api/dashboard/policies/list
//     * Note: The original request specified /admin/policies/list, changing to /api/dashboard/policies/list
//     * for consistency with the /api/dashboard/viewpolicy path.
//     *
//     * @return ResponseEntity containing a list of PolicyResponseDTOs and HTTP status.
//     */
//    @GetMapping("/policies/list")
//    public ResponseEntity<List<PolicyResponseDTO>> getAllPoliciesForDashboard() {
//        List<PolicyResponseDTO> policies = dashboardService.getAllPolicies();
//        return ResponseEntity.ok(policies);
//    }
//
//    @GetMapping("/policies/list/{userId}")
//
//    /**
//     * Endpoint to retrieve detailed information for a single policy by its ID.
//     * Accessible via GET /api/dashboard/viewpolicy/{id}
//     *
//     * @param id The policyId to retrieve.
//     * @return ResponseEntity containing the PolicyResponseDTO if found, or 404 Not Found.
//     */
//    @GetMapping("/viewpolicy/{id}")
//    public ResponseEntity<PolicyResponseDTO> getPolicyDetails(@PathVariable Long id) {
//        return dashboardService.getPolicyById(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }
//
//
//
//}


// src/main/java/com/example/policyapp/controller/DashboardController.java
package com.insurance.InsuranceApp.controller;

import com.insurance.InsuranceApp.dto.PolicyResponseDTO;
import com.insurance.InsuranceApp.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Dashboard-related policy data.
 * Provides endpoints to list all policies and get details for a specific policy.
 */
@RestController
@RequestMapping("/api/dashboard") // Base path for dashboard related APIs
@CrossOrigin("*")
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    /**
     * Endpoint to retrieve a list of all policies with their associated plan details.
     * Accessible via GET /api/dashboard/policies/list
     * Note: The original request specified /admin/policies/list, changing to /api/dashboard/policies/list
     * for consistency with the /api/dashboard/viewpolicy path.
     *
     * @return ResponseEntity containing a list of PolicyResponseDTOs and HTTP status.
     */
    @GetMapping("/policies/list")
    public ResponseEntity<List<PolicyResponseDTO>> getAllPoliciesForDashboard() {
        List<PolicyResponseDTO> policies = dashboardService.getAllPolicies();
        return ResponseEntity.ok(policies);
    }

    /**
     * Endpoint to retrieve a list of policies associated with a specific user ID.
     * Accessible via GET /api/dashboard/policies/user/{userId}
     *
     * @param userId The ID of the user whose policies are to be retrieved.
     * @return ResponseEntity containing a list of PolicyResponseDTOs for the user and HTTP status.
     */
    @GetMapping("/policies/user/{userId}")
    public ResponseEntity<List<PolicyResponseDTO>> getPoliciesByUserIdForDashboard(@PathVariable Long userId) {
        List<PolicyResponseDTO> userPolicies = dashboardService.getPoliciesByUserId(userId);
        if (userPolicies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        }
        return ResponseEntity.ok(userPolicies);
    }

    /**
     * Endpoint to retrieve detailed information for a single policy by its ID.
     * Accessible via GET /api/dashboard/viewpolicy/{id}
     *
     * @param id The policyId to retrieve.
     * @return ResponseEntity containing the PolicyResponseDTO if found, or 404 Not Found.
     */
    @GetMapping("/viewpolicy/{id}")
    public ResponseEntity<PolicyResponseDTO> getPolicyDetails(@PathVariable Long id) {
        return dashboardService.getPolicyById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}