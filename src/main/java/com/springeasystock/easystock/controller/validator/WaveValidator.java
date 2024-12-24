package com.springeasystock.easystock.controller.validator;

import com.springeasystock.easystock.model.Wave;
import com.springeasystock.easystock.service.WaveService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WaveValidator implements ConstraintValidator<ValidWave, Wave> {

    private final WaveService waveService;

    @Override
    public boolean isValid(Wave wave, ConstraintValidatorContext context) {
        if (wave == null) {
            return false;
        }
        return waveService.existsById(wave.getId());
    }
}