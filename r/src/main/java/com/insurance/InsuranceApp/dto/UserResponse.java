package com.insurance.InsuranceApp.dto;

public class UserResponse {
    private Long userId;
    private String username;
    private String email;
    private String roleType;

    // No-argument constructor
    public UserResponse() {
    }

    // All-argument constructor
    public UserResponse(Long userId, String username, String email, String roleType) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roleType = roleType;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
