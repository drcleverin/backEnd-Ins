package com.insurance.InsuranceApp.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//@Data
public class LoginRequest {
    public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotBlank(message = "Username or email cannot be empty")
    private String usernameOrEmail; // Can be username or email
    
    @NotBlank(message = "Password cannot be empty")
    private String password;
}