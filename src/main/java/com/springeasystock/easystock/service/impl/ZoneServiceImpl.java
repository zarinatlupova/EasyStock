package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.exception.CustomNotFoundException;
import com.springeasystock.easystock.mapper.ItemMapper;
import com.springeasystock.easystock.model.Item;
import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.record.ZoneDTO;
import com.springeasystock.easystock.mapper.ZoneMapper;
import com.springeasystock.easystock.model.Zone;
import com.springeasystock.easystock.repo.ZoneRepository;
import com.springeasystock.easystock.service.ZoneService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ZoneServiceImpl implements ZoneService {
    private ZoneRepository zoneRepository;

    @Override
    public ZoneDTO createZones(ZoneDTO zoneDTO) {
        Zone zone = ZoneMapper.toEntity(zoneDTO);
        Zone savedWave = zoneRepository.save(zone);
        return ZoneMapper.toDTO(savedWave);
    }

    @Override
    public Page<ZoneDTO> getAllZones(Pageable pageable) {
        Page<Zone> zones = zoneRepository.findAll(pageable);
        return zones.map(ZoneMapper::toDTO);
    }

    @Override
    public ZoneDTO getZoneById(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new CustomNotFoundException(zoneId));
        return ZoneMapper.toDTO(zone);

    }

    @Override
    public ZoneDTO updateZone(Long zoneId, ZoneDTO updatedZone) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new CustomNotFoundException(zoneId));
        zone.setWaves(updatedZone.waves());
        zone.setName(updatedZone.name());
        zone.setType(updatedZone.type());
        zone.setItems(updatedZone.items());
        zone.setItemCount(updatedZone.itemCount());
        Zone updatedObj =  zoneRepository.save(zone);
        return ZoneMapper.toDTO(updatedObj);
    }

    @Override
    public void deleteZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new CustomNotFoundException(zoneId));
        zoneRepository.deleteById(zone.getId());
    }

    @Override
    public boolean existsById(Long id) {
        return zoneRepository.existsById(id);
    }

}
