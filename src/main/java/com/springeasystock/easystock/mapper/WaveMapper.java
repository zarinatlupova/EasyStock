package com.springeasystock.easystock.mapper;

import com.springeasystock.easystock.record.WaveDTO;
import com.springeasystock.easystock.model.Wave;

public class WaveMapper {

    public static WaveDTO toDTO(Wave wave) {
        return  new WaveDTO(
                wave.getId(),
                wave.getOrderList(),
                wave.getWavePriority(),
                wave.getWaveStatus()
        );
    }


    public static Wave toEntity(WaveDTO dto) {
        return  new Wave(
                dto.id(),
                dto.orderList(),
                dto.wavePriority(),
                dto.waveStatus()
        );
    }
}

