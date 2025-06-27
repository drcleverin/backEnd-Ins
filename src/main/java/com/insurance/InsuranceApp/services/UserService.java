// src/main/java/com/example/insuranceapp/services/UserService.java
package com.insurance.InsuranceApp.services;

import com.insurance.InsuranceApp.model.User;
import com.insurance.InsuranceApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public Long getUserIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(User::getUserId) // or getId(), depending on your field name
                .orElse(null);
    }

    public String getUserRoleById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getRole() != null) {
                return user.getRole().getRoleType();
            }
        }
        return null; // User not found or role not assigned
    }

    // Other user-related methods (e.g., getting user details, updating profile)
}