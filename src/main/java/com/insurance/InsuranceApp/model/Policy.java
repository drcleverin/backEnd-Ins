package com.insurance.InsuranceApp.model;
//


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate; // Correctly using LocalDate for database DATE type columns

/**
 * Entity class representing the 'policies' table in the database.
 * This maps Java objects to relational database tables using JPA.
 */
@Entity
@Table(name = "policies")
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID
	@Column(name = "policy_id")
	private Long policyId;

	@Column(name = "policy_end_date", nullable = false)
	private LocalDate policyEndDate; // Mapped to DATE column

	@Column(name = "policy_start_date", nullable = false)
	private LocalDate policyStartDate; // Mapped to DATE column

	@Column(name = "policy_status", nullable = false, length = 50)
	private String policyStatus;

	@Column(name = "premium_amount", nullable = false, precision = 10, scale = 2)
	private BigDecimal premiumAmount;

	// Many policies can belong to one insurance plan
	// JPA automatically manages the 'plan_id' foreign key column due to @JoinColumn
	@ManyToOne
	@JoinColumn(name = "plan_id", nullable = false)
	private InsurancePlan insurancePlan; // Reference to the associated InsurancePlan object

	// Many policies can belong to one user
	// JPA automatically manages the 'user_id' foreign key column due to @JoinColumn
	// @JsonBackReference prevents infinite recursion in JSON serialization if User also has @JsonManagedReference to policies
	@JsonBackReference("user-policies")
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	// Many policies can belong to one vehicle (nullable relationship)
	// JPA automatically manages the 'vehicle_id' foreign key column due to @JoinColumn
	// @JsonBackReference prevents infinite recursion in JSON serialization if Vehicle also has @JsonManagedReference to policies
	@JsonBackReference("vehicle-policies")
	@ManyToOne
	@JoinColumn(name = "vehicle_id", nullable = true) // 'nullable = true' matches your schema
	private Vehicle vehicle;


	// Constructors
	public Policy() {
	}

	public Policy(LocalDate policyEndDate, LocalDate policyStartDate, String policyStatus,
				  BigDecimal premiumAmount, InsurancePlan insurancePlan, User user, Vehicle vehicle) {
		this.policyEndDate = policyEndDate;
		this.policyStartDate = policyStartDate;
		this.policyStatus = policyStatus;
		this.premiumAmount = premiumAmount;
		this.insurancePlan = insurancePlan;
		this.user = user;
		this.vehicle = vehicle;
	}

	// Getters and Setters
	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public LocalDate getPolicyEndDate() {
		return policyEndDate;
	}

	public void setPolicyEndDate(LocalDate policyEndDate) {
		this.policyEndDate = policyEndDate;
	}

	public LocalDate getPolicyStartDate() {
		return policyStartDate;
	}

	public void setPolicyStartDate(LocalDate policyStartDate) {
		this.policyStartDate = policyStartDate;
	}

	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	public BigDecimal getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(BigDecimal premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public InsurancePlan getInsurancePlan() {
		return insurancePlan;
	}

	public void setInsurancePlan(InsurancePlan insurancePlan) {
		this.insurancePlan = insurancePlan;
	}

	// Convenience getter to get planId from the associated InsurancePlan object
	public Long getPlanId() {
		return insurancePlan != null ? insurancePlan.getPlanId() : null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Convenience getter to get userId from the associated User object
	public Long getUserId() {
		return user != null ? user.getUserId() : null;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	// Convenience getter to get vehicleId from the associated Vehicle object
	public Long getVehicleId() {
		return vehicle != null ? vehicle.getVehicleId() : null;
	}

	@Override
	public String toString() {
		return "Policy{" +
				"policyId=" + policyId +
				", policyEndDate=" + policyEndDate +
				", policyStartDate=" + policyStartDate +
				", policyStatus='" + policyStatus + '\'' +
				", premiumAmount=" + premiumAmount +
				", planId=" + (insurancePlan != null ? insurancePlan.getPlanId() : "null") +
				", userId=" + (user != null ? user.getUserId() : "null") +
				", vehicleId=" + (vehicle != null ? vehicle.getVehicleId() : "null") +
				'}';
	}
}

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//import java.time.LocalDate;
//
///**
// * Entity class representing the 'policies' table in the database.
// * This maps Java objects to relational database tables using JPA.
// */
//@Entity
//@Table(name = "policies")
//public class Policy {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID
//	@Column(name = "policy_id")
//	private Long policyId;
//
//	@Column(name = "policy_end_date", nullable = false)
//	private LocalDate policyEndDate;
//
//	@Column(name = "policy_start_date", nullable = false)
//	private LocalDate policyStartDate;
//
//	@Column(name = "policy_status", nullable = false, length = 50)
//	private String policyStatus;
//
//	@Column(name = "premium_amount", nullable = false, precision = 10, scale = 2)
//	private BigDecimal premiumAmount;
//
//	@ManyToOne // Many policies can belong to one insurance plan
//	@JoinColumn(name = "plan_id", nullable = false) // Foreign key column
//	private InsurancePlan insurancePlan; // Reference to the associated InsurancePlan
//
////	@ManyToOne // Many policies can belong to one user
////	@JoinColumn(name = "user_id", nullable = false) // Foreign key column for user
////	private User user; // This field MUST be named 'user' to match mappedBy="user" in the User entity
////
////	// ********************************** FIX STARTS HERE **********************************
////	@ManyToOne // Many policies can belong to one vehicle (nullable relationship)
////	@JoinColumn(name = "vehicle_id", nullable = true) // Foreign key column for vehicle, nullable as per your original
////	private Vehicle vehicle; // This field MUST be named 'vehicle' to match mappedBy="vehicle" in the Vehicle entity
////	// *********************************** FIX ENDS HERE ***********************************
//
//	@JsonBackReference("user-policies") // <--- Add this, with same name
//	@ManyToOne
//	@JoinColumn(name = "user_id", nullable = false)
//	private User user;
//
//	// Also for Vehicle-Policy relationship
//	@JsonBackReference("vehicle-policies") // <--- Add this, with specific name
//	@ManyToOne
//	@JoinColumn(name = "vehicle_id", nullable = true)
//	private Vehicle vehicle;
//
//
//	// Constructors
//	public Policy() {
//	}
//
//	public Policy(LocalDate policyEndDate, LocalDate policyStartDate, String policyStatus,
//				  BigDecimal premiumAmount, InsurancePlan insurancePlan, User user, Vehicle vehicle) { // Changed userId to User user, and vehicleId to Vehicle vehicle
//		this.policyEndDate = policyEndDate;
//		this.policyStartDate = policyStartDate;
//		this.policyStatus = policyStatus;
//		this.premiumAmount = premiumAmount;
//		this.insurancePlan = insurancePlan;
//		this.user = user;
//		this.vehicle = vehicle; // Set the Vehicle object
//	}
//
//	// Getters and Setters
//	public Long getPolicyId() {
//		return policyId;
//	}
//
//	public void setPolicyId(Long policyId) {
//		this.policyId = policyId;
//	}
//
//	public LocalDate getPolicyEndDate() {
//		return policyEndDate;
//	}
//
//	public void setPolicyEndDate(LocalDate policyEndDate) {
//		this.policyEndDate = policyEndDate;
//	}
//
//	public LocalDate getPolicyStartDate() {
//		return policyStartDate;
//	}
//
//	public void setPolicyStartDate(LocalDate policyStartDate) {
//		this.policyStartDate = policyStartDate;
//	}
//
//	public String getPolicyStatus() {
//		return policyStatus;
//	}
//
//	public void setPolicyStatus(String policyStatus) {
//		this.policyStatus = policyStatus;
//	}
//
//	public BigDecimal getPremiumAmount() {
//		return premiumAmount;
//	}
//
//	public void setPremiumAmount(BigDecimal premiumAmount) {
//		this.premiumAmount = premiumAmount;
//	}
//
//	public InsurancePlan getInsurancePlan() {
//		return insurancePlan;
//	}
//
//	public void setInsurancePlan(InsurancePlan insurancePlan) {
//		this.insurancePlan = insurancePlan;
//	}
//
//	public Long getPlanId() {
//		return insurancePlan != null ? insurancePlan.getPlanId() : null;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Long getUserId() {
//		return user != null ? user.getUserId() : null;
//	}
//
//	// ********************************** FIX STARTS HERE **********************************
//	public Vehicle getVehicle() { // Getter for the Vehicle object
//		return vehicle;
//	}
//
//	public void setVehicle(Vehicle vehicle) { // Setter for the Vehicle object
//		this.vehicle = vehicle;
//	}
//
//	// If you still need the vehicleId directly for DTOs or other purposes, add a convenience getter:
//	public Long getVehicleId() {
//		return vehicle != null ? vehicle.getVehicleId() : null;
//	}
//	// *********************************** FIX ENDS HERE ***********************************
//
//	@Override
//	public String toString() {
//		return "Policy{" +
//				"policyId=" + policyId +
//				", policyEndDate=" + policyEndDate +
//				", policyStartDate=" + policyStartDate +
//				", policyStatus='" + policyStatus + '\'' +
//				", premiumAmount=" + premiumAmount +
//				", planId=" + (insurancePlan != null ? insurancePlan.getPlanId() : "null") +
//				", userId=" + (user != null ? user.getUserId() : "null") +
//				", vehicleId=" + (vehicle != null ? vehicle.getVehicleId() : "null") + // Changed to vehicle.getVehicleId()
//				'}';
//	}
//}