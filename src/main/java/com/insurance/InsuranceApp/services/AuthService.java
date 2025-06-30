package com.insurance.InsuranceApp.services; // Changed to services package

import com.insurance.InsuranceApp.dto.LoginRequest;
import com.insurance.InsuranceApp.dto.LoginResponse;
import com.insurance.InsuranceApp.dto.RegisterRequest;
import com.insurance.InsuranceApp.dto.UserResponse;
import com.insurance.InsuranceApp.exception.UserAlreadyExistsException;
import com.insurance.InsuranceApp.model.Role;
import com.insurance.InsuranceApp.model.User;
import com.insurance.InsuranceApp.repository.RoleRepository;
import com.insurance.InsuranceApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional; // Added import for Optional

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String DEFAULT_CUSTOMER_ROLE = "CUSTOMER";

    @Autowired
    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse registerUser(RegisterRequest request) {
        validateUniqueUser(request.getUsername(), request.getEmail());
//
//        Role role = roleRepository.findByRoleType(DEFAULT_CUSTOMER_ROLE)
//                .orElseGet(() -> roleRepository.save(new Role(DEFAULT_CUSTOMER_ROLE)));
        // AuthService.java - this part remains the same after the Role constructor change
//        Role role = roleRepository.findByRoleType(DEFAULT_CUSTOMER_ROLE)
//                .orElseGet(() -> roleRepository.save(new Role(DEFAULT_CUSTOMER_ROLE)));
        System.out.println(request.getRoleType());
        String requestedRoleType = Optional.ofNullable(request.getRoleType())
                .orElse("ADMIN")
                .toUpperCase(); 
        Role roleToAssign;
        switch (requestedRoleType) {
        case "ADMIN":
            roleToAssign = roleRepository.findByRoleType("ADMIN") // Assuming "ADMIN" is the exact string in your DB for admin role
                    .orElseThrow(() -> new RuntimeException("Error: Role 'ADMIN' not found in database."));
            break;
//        case "USER": // Assuming you want a "USER" role as well, distinct from CUSTOMER if applicable
//            roleToAssign = roleRepository.findByRoleType("USER")
//                    .orElseThrow(() -> new RuntimeException("Error: Role 'USER' not found in database."));
//            break;
        case "CUSTOMER": // This will be the default if nothing or invalid is sent
        default:
            roleToAssign = roleRepository.findByRoleType("CUSTOMER")
                    .orElseThrow(() -> new RuntimeException("Error: Role 'CUSTOMER' not found in database."));
            break;
    }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(roleToAssign);

        User savedUser = userRepository.save(user);
//        return new UserResponse(savedUser.getUserId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole().getRoleType()); // Changed to getRoleType()
        // In AuthService.java
        return new UserResponse(savedUser.getUserId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole().getRoleType());
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {
        User user = findByUsernameOrEmail(loginRequest.getUsernameOrEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return new LoginResponse(user.getUsername(), user.getRole().getRoleType());
    }

    private void validateUniqueUser(String username, String email) {
        if (userRepository.existsByUsername(username)) { // Using existsByUsername
            throw new UserAlreadyExistsException("Username '" + username + "' is already taken.");
        }
        if (userRepository.existsByEmail(email)) { // Using existsByEmail
            throw new UserAlreadyExistsException("Email '" + email + "' is already registered.");
        }
    }

    private Optional<User> findByUsernameOrEmail(String input) {
        return userRepository.findByUsername(input)
                .or(() -> userRepository.findByEmail(input));
    }
}