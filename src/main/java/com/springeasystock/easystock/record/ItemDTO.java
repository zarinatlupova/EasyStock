package com.springeasystock.easystock.record;

import com.springeasystock.easystock.controller.validator.ValidOrderList;
import com.springeasystock.easystock.controller.validator.ValidRole;
import com.springeasystock.easystock.controller.validator.ValidZone;
import com.springeasystock.easystock.model.OrderList;
import com.springeasystock.easystock.model.Role;
import com.springeasystock.easystock.model.Zone;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public record ItemDTO (
        Long id,
        @NotBlank(message = "Nname is mandatory")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,
        @Size(min = 2, max = 50, message = "Supplier must be between 2 and 50 characters")
        String supplier,
        @Positive(message = "Size must be greater than 0")
        @Digits(integer = 6, fraction = 2, message = "Price must have at most 6 digits and 2 decimal places")
        float size,
        @Positive(message = "Price must be greater than 0")
        @Digits(integer = 6, fraction = 2, message = "Price must have at most 6 digits and 2 decimal places")
        float price,
        @Positive(message = "Size must be greater than 0")
        @Min(value = 1, message = "Asile must be at least 1")
        @Max(value = 10000, message = "Asile must not exceed 10,000")
        int asile,
        @Positive(message = "Size must be greater than 0")
        @Min(value = 1, message = "Asile must be at least 1")
        @Max(value = 10000, message = "Asile must not exceed 10,000")
        int rack,
        @Positive(message = "Size must be greater than 0")
        @Min(value = 1, message = "Asile must be at least 1")
        @Max(value = 10000, message = "Asile must not exceed 10,000")
        int shelf,
        @ValidOrderList
        Set<OrderList> orderLists // List of item IDs associated with this order
)
{

}

