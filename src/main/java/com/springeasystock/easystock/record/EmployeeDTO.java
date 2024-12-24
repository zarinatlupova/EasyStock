package com.springeasystock.easystock.record;

import com.springeasystock.easystock.controller.validator.ValidRole;
import com.springeasystock.easystock.controller.validator.ValidZone;
import com.springeasystock.easystock.model.Role;
import com.springeasystock.easystock.model.Zone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.Set;

public record EmployeeDTO (
        Long id,
        @NotBlank(message = "Username is mandatory")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String username,
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String role,
        Timestamp createdAt,
        @ValidZone
        Zone zoneId,
        @ValidRole
        Set<Role> roleIds

){
    public EmployeeDTO {
        if (createdAt == null) {
            createdAt = new Timestamp(System.currentTimeMillis());
        }
    }
}
