//package com.insurance.InsuranceApp.repository;
//
//import com.insurance.InsuranceApp.model.Role;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface RoleRepository extends JpaRepository<Role, Integer> {
//    Optional<Role> findByName(String name);
//}


package com.insurance.InsuranceApp.repository;

import com.insurance.InsuranceApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType(String roleType);
}