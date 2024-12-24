package com.springeasystock.easystock.service;

import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.record.ZoneDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ZoneService {
    ZoneDTO createZones(ZoneDTO zoneDTO);
    Page<ZoneDTO> getAllZones(Pageable pageable);

    ZoneDTO getZoneById(Long zoneId);

    ZoneDTO updateZone(Long zoneId, ZoneDTO updatedZone);

    void deleteZone(Long zoneId);
    public boolean existsById(Long id);
}
