package com.springeasystock.easystock.mapper;

import com.springeasystock.easystock.record.EmployeeDTO;
import com.springeasystock.easystock.model.Employee;

//@Component

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee) {
        return  new EmployeeDTO(
                employee.getId(),
                employee.getUsername(),
                employee.getRole(),
                employee.getCreatedAt(),
                employee.getZone(),
                employee.getRoles()
        );
    }

    public static Employee toEntity(EmployeeDTO dto) {
        return  new Employee(
                dto.id(),
                dto.username(),
                dto.role(),
                dto.createdAt(),
                dto.zoneId(),
                dto.roleIds()
        );
    }

}

