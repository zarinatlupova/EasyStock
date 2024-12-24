package com.springeasystock.easystock.controller.validator;

import com.springeasystock.easystock.model.Item;
import com.springeasystock.easystock.repo.ItemRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemValidator implements ConstraintValidator<ValidItem, Item> {

    private final ItemRepository itemRepository;

    @Override
    public boolean isValid(Item item, ConstraintValidatorContext context) {
        if (itemRepository == null) {
            return false;
        }
        return itemRepository.existsById(item.getId());
    }
}