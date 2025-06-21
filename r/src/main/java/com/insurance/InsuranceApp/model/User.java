package com.insurance.InsuranceApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId; // Matches BIGINT in database

	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String username;

	@Column(name = "email", unique = true, nullable = false, length = 100)
	private String email;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@ManyToOne(fetch = FetchType.EAGER) // Assuming Role is eager loaded for common use cases
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;



//	@Column(name = "status", nullable = true) // Make sure this matches your DB column definition
//	private String status; // Or Boolean, Integer, etc., depending on your status type


	// Constructors
	public User() {
	}

	public User(String username, String email, String password, Role role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", role=" + (role != null ? role.getRoleType() : "null") +
				'}';
	}
}