package com.springeasystock.easystock.controller.validator;

import com.springeasystock.easystock.model.OrderList;
import com.springeasystock.easystock.service.OrderListService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderListValidator implements ConstraintValidator<ValidOrderList, OrderList> {

    private final OrderListService orderListService;

    @Override
    public boolean isValid(OrderList orderList, ConstraintValidatorContext context) {
        if (orderList == null) {
            return false;
        }
        return orderListService.existsById(orderList.getId());
    }
}