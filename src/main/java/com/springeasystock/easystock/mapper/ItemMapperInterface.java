package com.springeasystock.easystock.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.model.Item;
@Mapper
public interface ItemMapperInterface {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemDTO toDTO(Item item);
    Item toEntity(ItemDTO itemDTO);
}
