package com.insurance.InsuranceApp.controller;

import com.insurance.InsuranceApp.model.User; // Use the new User entity
import com.insurance.InsuranceApp.services.UserService; // This service should handle User-related logic
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-role")
@CrossOrigin(origins = "*")
public class UserController {


    private final UserService userService; // Now depends on UserService

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id-by-username/{username}")
    public ResponseEntity<?> getUserId(@PathVariable String username) {
        Long userId = userService.getUserIdByUsername(username);
        System.out.println(userId+" ..........................From USerCOn");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"message\": \"Username not found\" }");
        }

        return ResponseEntity.ok("{ \"userId\": " + userId + " }");
    }

    
    /**
     * 
     * Retrieves the role of a user by their ID.
     * @param userId The ID of the user.
     * @return A ResponseEntity containing the user's role or an error message.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserRole(@PathVariable String userId) {
        Long id; // Use Long for userId as per User entity
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"message\": \"Invalid User ID format\" }");
        }

        // Assuming UserService has a method to get role by user ID
        String roleName = userService.getUserRoleById(id);

        if (roleName == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"message\": \"User not found or role not assigned\" }");
        }

        return ResponseEntity.ok("{ \"role\": \"" + roleName + "\" }");
    }
}