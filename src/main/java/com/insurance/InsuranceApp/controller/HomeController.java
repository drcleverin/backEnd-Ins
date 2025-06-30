package com.insurance.InsuranceApp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Keep CrossOrigin if you need it for frontend interaction
public class HomeController {

    /**
     * Simple public endpoint for a home message.
     * Removed @PreAuthorize.
     */
    @GetMapping("/home")
    public String userHome() {
        return "Welcome to the Home Page (unsecured)!";
    }
}