package com.springeasystock.easystock.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.springeasystock.easystock.record.WaveDTO;
import com.springeasystock.easystock.model.Wave;
@Mapper
public interface WaveMapperInterface {
    WaveMapper INSTANCE = Mappers.getMapper(WaveMapper.class);

//    @Mapping(source = "orderList.id", target = "orderListId")
    WaveDTO toDTO(Wave wave);

    Wave toEntity(WaveDTO waveDTO);

}
