//package com.insurance.InsuranceApp.model;
//

// src/main/java/com/insurance/InsuranceApp/model/InsurancePlan.java
package com.insurance.InsuranceApp.model;


 // Import the correct Policy path
import jakarta.persistence.*;
import lombok.Data; // Using Lombok @Data for boilerplate, assuming it's configured
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "insurance_plans")
@Data // Lombok annotation for getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok annotation for no-args constructor
@AllArgsConstructor // Lombok annotation for all-args constructor
public class InsurancePlan {

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public String getDescriptionAboutPolicy() {
        return descriptionAboutPolicy;
    }

    public void setDescriptionAboutPolicy(String descriptionAboutPolicy) {
        this.descriptionAboutPolicy = descriptionAboutPolicy;
    }

    public BigDecimal getCoverage() {
        return coverage;
    }

    public void setCoverage(BigDecimal coverage) {
        this.coverage = coverage;
    }

    public BigDecimal getBasePremium() {
        return basePremium;
    }

    public void setBasePremium(BigDecimal basePremium) {
        this.basePremium = basePremium;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    public enum PlanType {
        Motor,
        Health,
        Product // Assuming 'Product' is also a valid plan type for consistency
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "plan_name", nullable = false, length = 100)
    private String planName; // e.g., "Motor Budget Plan", "Health Premium Plan"

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_type", nullable = false)
    private PlanType planType;

    @Column(name = "description_about_policy", columnDefinition = "TEXT")
    private String descriptionAboutPolicy;

    @Column(name = "base_premium", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePremium;

    @Column(name = "coverage", nullable = false, precision = 10, scale = 2) // Changed column name from base_premium
    private BigDecimal coverage; // Represents the sum insured for the plan

    // One-to-many relationship with Policy (many policies can be based on this plan)
    // 'mappedBy' refers to the field name in the Policy entity that owns the relationship.
    // Ensure 'Policy' entity has a field named 'insurancePlan' with @ManyToOne.
    @OneToMany(mappedBy = "insurancePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Policy> policies; // Ensure this matches the field in Policy.java
}

//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Entity
//@Table(name = "insurance_plans")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class InsurancePlan {
//
//    public Long getPlanId() {
//        return planId;
//    }
//
//    public void setPlanId(Long planId) {
//        this.planId = planId;
//    }
//
//    public String getPlanName() {
//        return planName;
//    }
//
//    public void setPlanName(String planName) {
//        this.planName = planName;
//    }
//
//    public PlanType getPlanType() {
//        return planType;
//    }
//
//    public void setPlanType(PlanType planType) {
//        this.planType = planType;
//    }
//
//    public String getDescriptionAboutPolicy() {
//        return descriptionAboutPolicy;
//    }
//
//    public void setDescriptionAboutPolicy(String descriptionAboutPolicy) {
//        this.descriptionAboutPolicy = descriptionAboutPolicy;
//    }
//
//    public BigDecimal getBasePremium() {
//        return basePremium;
//    }
//
//    public void setBasePremium(BigDecimal basePremium) {
//        this.basePremium = basePremium;
//    }
//
//    public BigDecimal getCoverage() {
//        return coverage;
//    }
//
//    public void setCoverage(BigDecimal coverage) {
//        this.coverage = coverage;
//    }
//
//    public List<Policy> getPolicies() {
//        return policies;
//    }
//
//    public void setPolicies(List<Policy> policies) {
//        this.policies = policies;
//    }
//
//    public enum PlanType {
//        Motor,
//        Health
//    }
//
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long planId;
//
//    @Column(name = "plan_name", nullable = false, length = 100)
//    private String planName; // e.g., "Motor Budget Plan", "Health Premium Plan"
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "plan_type", nullable = false)
//    private PlanType planType;
//
//    @Column(name = "description_about_policy", columnDefinition = "TEXT")
//    private String descriptionAboutPolicy;
//
//    @Column(name = "base_premium", nullable = false, precision = 10, scale = 2)
//    private BigDecimal basePremium;
//
//    @Column(name = "coverage", nullable = false, precision = 10, scale = 2)
//    private BigDecimal coverage;
//
//
//    //    // One-to-many relationship with Policy (many policies can be based on this plan)
//    @OneToMany(mappedBy = "insurancePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<Policy> policies;
//}