package com.insurance.InsuranceApp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate; // For date_of_claim and date_of_incident
import java.time.LocalTime; // For time_of_incident

@Entity
@Table(name = "claims") // Ensure table name is 'claims'
//@Data // Lombok for getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok for no-arg constructor
@AllArgsConstructor // Lombok for all-args constructor
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id") // Explicitly map to claim_id
    private Long claimId;

    @Column(name = "claim_reason", columnDefinition = "TEXT") // Map to claim_reason, use TEXT for columnDefinition
    private String claimReason;

    // For ENUM type, you can use String or an actual Enum in Java
    // Using String for simplicity, ensures it matches 'approved','pending','rejected'
    @Column(name = "claim_status", nullable = false, length = 50)
    private String claimStatus;

    @Column(name = "date_of_claim", nullable = false)
    private LocalDate dateOfClaim;

    @Column(name = "date_of_incident", nullable = false)
    private LocalDate dateOfIncident;

    @Column(name = "description", columnDefinition = "TEXT") // Map to description, use TEXT
    private String description;

    @Column(name = "location", length = 255)
    private String location;

    @Column(name = "sum_insured", nullable = false, precision = 12, scale = 2) // Map to sum_insured
    private BigDecimal sumInsured;

    @Column(name = "time_of_incident", nullable = false)
    private LocalTime timeOfIncident; // Map to time_of_incident
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;

    
    

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public String getClaimReason() {
        return claimReason;
    }

    public void setClaimReason(String claimReason) {
        this.claimReason = claimReason;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public LocalDate getDateOfClaim() {
        return dateOfClaim;
    }

    public void setDateOfClaim(LocalDate dateOfClaim) {
        this.dateOfClaim = dateOfClaim;
    }

    public LocalDate getDateOfIncident() {
        return dateOfIncident;
    }

    public void setDateOfIncident(LocalDate dateOfIncident) {
        this.dateOfIncident = dateOfIncident;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    public LocalTime getTimeOfIncident() {
        return timeOfIncident;
    }

    public void setTimeOfIncident(LocalTime timeOfIncident) {
        this.timeOfIncident = timeOfIncident;
    }

    
    // No need for a vehicle direct link based on the provided table schema,
    // as it's linked via policy. If you need it later, you can add it.
}