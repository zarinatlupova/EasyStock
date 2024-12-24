package com.springeasystock.easystock.mapper;

import com.springeasystock.easystock.record.ZoneDTO;
import com.springeasystock.easystock.model.Zone;

public class ZoneMapper {

    public static ZoneDTO toDTO(Zone zone) {
        return  new ZoneDTO(
                zone.getId(),
                zone.getWaves(),
                zone.getName(),
                zone.getType(),
                zone.getItems(),
                zone.getItemCount()
        );
    }

    public static Zone toEntity(ZoneDTO dto) {
        return  new Zone(
                dto.id(),
                dto.waves(),
                dto.name(),
                dto.type(),
                dto.items(),
                dto.itemCount()
        );
    }


}

