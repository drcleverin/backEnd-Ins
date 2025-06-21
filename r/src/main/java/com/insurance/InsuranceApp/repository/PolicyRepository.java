// src/main/java/com/example/insuranceapp/repository/PolicyRepository.java
package com.insurance.InsuranceApp.repository;
// src/main/java/com/example/policyapp/repository/PolicyRepository.java

import com.insurance.InsuranceApp.model.Policy;
import com.insurance.InsuranceApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Policy entity.
 * This extends JpaRepository to provide standard CRUD operations
 * and allows for custom query methods if needed.
 */
@Repository
public interface PolicyRepository extends JpaRepository<com.insurance.InsuranceApp.model.Policy, Long> {
    // You can add custom query methods here if required,
    // e.g., Policy findByUserId(Long userId);
    List<Policy> findByUser_UserId(Long userId); // Assuming User entity has a field named 'userId' or 'id'
}
