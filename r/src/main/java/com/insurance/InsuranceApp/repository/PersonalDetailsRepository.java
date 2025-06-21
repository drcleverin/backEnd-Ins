package com.insurance.InsuranceApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insurance.InsuranceApp.model.PersonalDetails;

@Repository
public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Long> {
	@Query("SELECT pd FROM PersonalDetails pd WHERE pd.user.id = :userId")
    PersonalDetails findByUserId(@Param("userId") Long userId);
}
