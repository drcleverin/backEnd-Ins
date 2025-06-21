package com.insurance.InsuranceApp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	public Role(Long roleId, String roleType) {
		this.roleId = roleId;
		this.roleType = roleType;
	}

	public Role() {
	}

	public Role(String roleType) {
		this.roleType = roleType;
	}

	public Long getRoleId() {
		return roleId;
	}


	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}


	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_type", nullable = false, unique = true, length = 50)
    private String roleType; // e.g., 'Customer', 'Admin', 'CSR', 'Underwriter'
    
    
    public String getRoleType() {
    	return roleType;
    }
}