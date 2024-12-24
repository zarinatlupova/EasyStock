package com.springeasystock.easystock.controller.validator;

import com.springeasystock.easystock.model.Role;
import com.springeasystock.easystock.service.RoleService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleValidator implements ConstraintValidator<ValidRole, Role> {

    private final RoleService roleService;

    @Override
    public boolean isValid(Role role, ConstraintValidatorContext context) {
        if (role == null) {
            return false;
        }
        return roleService.existsById(role.getId());
    }
}