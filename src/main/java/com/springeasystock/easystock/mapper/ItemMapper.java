package com.springeasystock.easystock.mapper;

import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.model.Item;

public class ItemMapper {

    public static ItemDTO toDTO(Item item) {
        return  new ItemDTO(
                item.getId(),
                item.getName(),
                item.getSupplier(),
                item.getSize(),
                item.getPrice(),
                item.getAsile(),
                item.getRack(),
                item.getShelf(),
                item.getOrderLists()
        );
    }

    public static Item toEntity(ItemDTO dto) {
        return  new Item(
                dto.id(),
                dto.name(),
                dto.supplier(),
                dto.size(),
                dto.price(),
                dto.asile(),
                dto.rack(),
                dto.shelf(),
                dto.orderLists()
        );
    }


}

