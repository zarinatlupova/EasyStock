package com.springeasystock.easystock.controller.validator;

import com.springeasystock.easystock.model.Customer;
import com.springeasystock.easystock.repo.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerValidator implements ConstraintValidator<ValidCustomer, Customer> {

    private final CustomerRepository customerRepository;

    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext context) {
        if (customerRepository == null) {
            return false;
        }
        return customerRepository.existsById(customer.getId());
    }
}