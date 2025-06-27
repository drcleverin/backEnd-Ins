
package com.insurance.InsuranceApp.controller;
import com.insurance.InsuranceApp.dto.LoginRequest;
import com.insurance.InsuranceApp.dto.LoginResponse;
import com.insurance.InsuranceApp.dto.RegisterRequest;
import com.insurance.InsuranceApp.dto.UserResponse;
import com.insurance.InsuranceApp.exception.UserAlreadyExistsException;
import com.insurance.InsuranceApp.services.AuthService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException; // Still used for consistent error handling
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            authService.registerUser(registerRequest);
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("status", "successful");
            successResponse.put("message", "User registered successfully!");
            return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Registration Failed");
            errorResponse.put("message", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            System.out.println(e);
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "An unexpected error occurred during registration.");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = authService.loginUser(loginRequest);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) { // Catch IllegalArgumentException
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Login Failed");
            errorResponse.put("message", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "An unexpected error occurred during login.");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}