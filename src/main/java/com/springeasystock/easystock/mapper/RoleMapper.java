package com.springeasystock.easystock.mapper;

import com.springeasystock.easystock.record.RoleDTO;
import com.springeasystock.easystock.model.Role;

public class RoleMapper {

    public static RoleDTO toDTO(Role role) {
        return  new RoleDTO(
                role.getId(),
                role.getName(),
                role.getEmployees()
        );
    }

    public static Role toEntity(RoleDTO dto) {
        return  new Role(
                dto.id(),
                dto.name(),
                dto.employees()
        );
    }
}

