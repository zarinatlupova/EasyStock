package com.springeasystock.easystock.record;

import com.springeasystock.easystock.controller.validator.ValidOrderList;
import com.springeasystock.easystock.model.OrderList;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


public record WaveDTO (
        Long id,
        @ValidOrderList
        Set<OrderList> orderList,
        @Size(min = 2, max = 50, message = "Name of wave priority must be between 2 and 50 characters")
        String wavePriority,
        @Size(min = 2, max = 50, message = "Name of wave status must be between 2 and 50 characters")
        String waveStatus
)
{

}

