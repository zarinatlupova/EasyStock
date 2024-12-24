package com.springeasystock.easystock.service;

import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.record.WaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WaveService {

    WaveDTO createWave(WaveDTO orderListDTO);
    Page<WaveDTO> getAllWaves(Pageable pageable);


    WaveDTO getWaveById(Long waveId);

    WaveDTO updateWave(Long waveId, WaveDTO updatedWave);

    void deleteWave(Long waveId);

    public boolean existsById(Long id);
}
