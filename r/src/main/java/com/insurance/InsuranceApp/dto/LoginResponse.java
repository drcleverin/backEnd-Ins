package com.insurance.InsuranceApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // This handles getUsername(), setUsername(), getRole(), setRole(), toString(), equals(), hashCode()
@NoArgsConstructor // This handles public LoginResponse()
@AllArgsConstructor // This handles public LoginResponse(String username, String role)
public class LoginResponse {

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private String username;
	private String role;

	public LoginResponse(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}
	// You can add more user-specific data here if needed (e.g., userId)
	// No accessToken or tokenType needed as per your request
}