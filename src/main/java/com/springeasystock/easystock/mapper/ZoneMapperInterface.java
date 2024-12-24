package com.springeasystock.easystock.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.springeasystock.easystock.record.ZoneDTO;
import com.springeasystock.easystock.model.Zone;
@Mapper
public interface ZoneMapperInterface {
    ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);

//    @Mapping(source = "wave.id", target = "waveId")
//    @Mapping(source = "item.id", target = "itemId")
    ZoneDTO toDTO(Zone zone);

    Zone toEntity(ZoneDTO zoneDTO);
}
