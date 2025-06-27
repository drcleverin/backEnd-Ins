package com.insurance.InsuranceApp.repository;


import com.insurance.InsuranceApp.model.InsurancePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePlanRepository extends JpaRepository<InsurancePlan, Long> {
    // You can add custom queries later if needed
}
