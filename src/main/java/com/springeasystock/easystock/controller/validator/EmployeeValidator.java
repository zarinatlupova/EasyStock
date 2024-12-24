package com.springeasystock.easystock.controller.validator;

import com.springeasystock.easystock.model.Employee;
import com.springeasystock.easystock.repo.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeValidator implements ConstraintValidator<ValidEmployee, Employee> {

    private final EmployeeRepository employeeRepository;

    @Override
    public boolean isValid(Employee employee, ConstraintValidatorContext context) {
        if (employeeRepository == null) {
            return false;
        }
        return employeeRepository.existsById(employee.getId());
    }
}