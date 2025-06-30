package com.insurance.InsuranceApp.repository;


import com.insurance.InsuranceApp.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    // Custom query to find claims by policy ID (useful for retrieval)
    List<Claim> findByPolicy_PolicyId(Long policyId);
}