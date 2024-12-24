package com.springeasystock.easystock.controller.validator;

import com.springeasystock.easystock.model.Zone;
import com.springeasystock.easystock.service.ZoneService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ZoneValidator implements ConstraintValidator<ValidZone, Zone> {

    private final ZoneService zoneService;

    @Override
    public boolean isValid(Zone zone, ConstraintValidatorContext context) {
        if (zone == null) {
            return false;
        }
        return zoneService.existsById(zone.getId());
    }
}
