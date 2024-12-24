package com.springeasystock.easystock.record;

import com.springeasystock.easystock.controller.validator.ValidItem;
import com.springeasystock.easystock.controller.validator.ValidWave;
import com.springeasystock.easystock.model.Item;
import com.springeasystock.easystock.model.Wave;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


public record ZoneDTO (
        Long id,
        @ValidWave
        Set<Wave> waves,
        @NotBlank(message = "Name of zone is mandatory")
        @Size(min = 2, max = 50, message = "Name of zone must be between 2 and 50 characters")
        String name,
        @NotBlank(message = "Name of zone type is mandatory")
        @Size(min = 2, max = 50, message = "Name of zone type must be between 2 and 50 characters")
        String type,
        @ValidItem
        Set<Item> items,
        @Positive(message = "Item count must be greater than 0")
        @Min(value = 1, message = "Item count must be at least 1")
        @Max(value = 10000, message = "Item count must not exceed 10,000")
        Integer itemCount

) {

}

