package com.insurance.InsuranceApp.mapper;
import org.mapstruct.Mapper;

import com.insurance.InsuranceApp.dto.PolicyDTO;
import com.insurance.InsuranceApp.model.Policy;

//interface
@Mapper(componentModel = "spring")
public interface PolicyMapper {
 PolicyDTO toDTO(Policy policy);
 Policy toEntity(PolicyDTO dto);
}