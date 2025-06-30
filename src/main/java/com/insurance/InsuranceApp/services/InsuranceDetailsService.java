package com.insurance.InsuranceApp.services;

import com.insurance.InsuranceApp.model.Claim;
import com.insurance.InsuranceApp.model.Policy;
import com.insurance.InsuranceApp.model.PersonalDetails;
import com.insurance.InsuranceApp.model.InsurancePlan;
import com.insurance.InsuranceApp.dto.ClaimList;
import com.insurance.InsuranceApp.repository.ClaimRepository;
import com.insurance.InsuranceApp.repository.PolicyRepository;
import com.insurance.InsuranceApp.repository.PersonalDetailsRepository;
import com.insurance.InsuranceApp.repository.InsurancePlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class to handle business logic related to insurance details,
 * particularly fetching and aggregating claim, policy, and personal data.
 */
@Service
public class InsuranceDetailsService {

    private final ClaimRepository claimRepository;
    private final PolicyRepository policyRepository;
    private final PersonalDetailsRepository personalDetailsRepository;
    private final InsurancePlanRepository insurancePlanRepository;

    @Autowired
    public InsuranceDetailsService(ClaimRepository claimRepository,
                                   PolicyRepository policyRepository,
                                   PersonalDetailsRepository personalDetailsRepository,
                                   InsurancePlanRepository insurancePlanRepository) {
        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
        this.personalDetailsRepository = personalDetailsRepository;
        this.insurancePlanRepository = insurancePlanRepository;
    }

    /**
     * Fetches all claims and aggregates their related policy, personal,
     * and insurance plan details into AggregatedClaimDetailsDTO objects.
     *
     * @return A list of AggregatedClaimDetailsDTO containing the requested information.
     */
    @Transactional(readOnly = true) // Optimize for read operations
    public List<ClaimList> getAllAggregatedClaimDetails() {
        // Fetch all claims. Due to ManyToOne(fetch = FetchType.LAZY) on Policy in Claim entity,
        // we might get N+1 issues if not careful. For simplicity, we'll iterate,
        // but in a high-performance scenario, consider using JOIN FETCH or DTO projections in JPA.
        List<Claim> claims = claimRepository.findAll();

        // Convert each Claim entity to the aggregated DTO
        return claims.stream()
                .map(this::mapToAggregatedClaimDetailsDTO)
                .collect(Collectors.toList());
    }

    /**
     * Maps a Claim entity to an AggregatedClaimDetailsDTO,
     * fetching necessary related data (Policy, PersonalDetails, InsurancePlan).
     *
     * @param claim The Claim entity to map.
     * @return An AggregatedClaimDetailsDTO containing the combined details.
     */
    private ClaimList mapToAggregatedClaimDetailsDTO(Claim claim) {
        ClaimList dto = new ClaimList();
        dto.setClaimId(claim.getClaimId());
        dto.setDateOfClaim(claim.getDateOfClaim());
        dto.setStatus(claim.getClaimStatus());
        dto.setClaimAmount(claim.getSumInsured());
        dto.setDateOfIncident(claim.getDateOfIncident());
        dto.setDescription(claim.getDescription());
        dto.setLocation(claim.getLocation());
        dto.setTimeOfIncident(claim.getTimeOfIncident());

        // Get Policy details (should be eagerly loaded or accessed within transactional context)
        Policy policy = claim.getPolicy();
        if (policy != null) {
            dto.setPolicyNumber(policy.getPolicyId());

            // Get InsurancePlan details
            InsurancePlan insurancePlan = policy.getInsurancePlan();
            if (insurancePlan != null) {
                dto.setClaimType(insurancePlan.getPlanName());
            } else {
                dto.setClaimType("Unknown Plan");
            }

            // Get User and PersonalDetails for customer name
            PersonalDetails personalDetails = null;
            if (policy.getUser() != null) {
                // Fetch PersonalDetails using the user_id from the Policy's User object.
                // Assuming PersonalDetails has a direct relationship back to User.
                // In PersonalDetails entity, @JoinColumn(name = "user_id") maps to User's primary key.
                personalDetails = personalDetailsRepository.findByUserId(policy.getUser().getUserId());
            }

            if (personalDetails != null) {
                dto.setCustomerName(personalDetails.getFirstName() + " " + personalDetails.getLastName());
            } else {
                dto.setCustomerName("Unknown Customer");
            }
        } else {
            dto.setPolicyNumber(null); // Or some default value like 0L
            dto.setClaimType("N/A");
            dto.setCustomerName("N/A");
        }

        return dto;
    }

       public PersonalDetails getPersonalDetailsByUser(Long userId) {
        return personalDetailsRepository.findByUserId(userId);
    }
}