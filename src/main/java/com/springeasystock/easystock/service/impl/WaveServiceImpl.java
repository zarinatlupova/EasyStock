package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.exception.CustomNotFoundException;
import com.springeasystock.easystock.mapper.ItemMapper;
import com.springeasystock.easystock.model.Item;
import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.record.WaveDTO;
import com.springeasystock.easystock.mapper.WaveMapper;
import com.springeasystock.easystock.model.Wave;
import com.springeasystock.easystock.repo.WaveRepository;
import com.springeasystock.easystock.service.WaveService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class WaveServiceImpl implements WaveService {
    private WaveRepository waveRepository;

    @Override
    public WaveDTO createWave(WaveDTO waveDTO) {
        Wave wave = WaveMapper.toEntity(waveDTO);
        Wave savedWave = waveRepository.save(wave);
        return WaveMapper.toDTO(savedWave);
    }


    @Override
    public Page<WaveDTO> getAllWaves(Pageable pageable) {
        Page<Wave> waves = waveRepository.findAll(pageable);
        return waves.map(WaveMapper::toDTO);
    }
    @Override
    public WaveDTO getWaveById(Long waveId) {
        Wave wave = waveRepository.findById(waveId)
                .orElseThrow(() -> new CustomNotFoundException(waveId));
        return WaveMapper.toDTO(wave);

    }

    @Override
    public WaveDTO updateWave(Long waveId, WaveDTO updatedWave) {
        Wave wave = waveRepository.findById(waveId)
                .orElseThrow(() -> new CustomNotFoundException(waveId));
        wave.setOrderList(updatedWave.orderList());
        wave.setWavePriority(updatedWave.wavePriority());
        wave.setWaveStatus(updatedWave.waveStatus());
        Wave updatedObj =  waveRepository.save(wave);
        return WaveMapper.toDTO(updatedObj);
    }

    @Override
    public void deleteWave(Long waveId) {
        Wave wave = waveRepository.findById(waveId)
                .orElseThrow(() -> new CustomNotFoundException(waveId));
        waveRepository.deleteById(wave.getId());
    }

    @Override
    public boolean existsById(Long id) {
        return waveRepository.existsById(id);
    }
}
