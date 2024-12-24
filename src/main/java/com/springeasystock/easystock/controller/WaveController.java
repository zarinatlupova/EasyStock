package com.springeasystock.easystock.controller;

import com.springeasystock.easystock.record.WaveDTO;
import com.springeasystock.easystock.service.WaveService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/waves")
public class WaveController {

    private WaveService waveService;


    @PostMapping
    public ResponseEntity<WaveDTO> createWaves(@RequestBody WaveDTO waveDTO){
        WaveDTO savedOrderList = waveService.createWave(waveDTO);
        return new ResponseEntity<>(savedOrderList, HttpStatus.CREATED);

    }
//    @GetMapping
//    public ResponseEntity<List<WaveDTO>> getAllWaves(){
//        List<WaveDTO> waves = waveService.getAllWaves();
//        return ResponseEntity.ok(waves);
//    }

    @GetMapping
    public ResponseEntity<List<WaveDTO>> getAllWaves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        // Получаем пагинированный список из сервиса
        Page<WaveDTO> wavePage = waveService.getAllWaves(pageable);

        // Получаем общее количество записей
        long totalElements = wavePage.getTotalElements();

        // Создаем HTTP-заголовок с общим количеством записей
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(totalElements));

        // Возвращаем пагинированный список и заголовки
        return ResponseEntity.ok()
                .headers(headers)
                .body(wavePage.getContent());
    }
    @GetMapping("{id}")
    public ResponseEntity<WaveDTO> getWaveById(@PathVariable("id") Long waveId){
        WaveDTO waveDTO = waveService.getWaveById(waveId);
        return ResponseEntity.ok(waveDTO);

    }

    @PutMapping("{id}")
    public ResponseEntity<WaveDTO> updateWave(@PathVariable("id")Long waveId,
                                              @RequestBody WaveDTO updatedWave){
        WaveDTO waveDTO = waveService.updateWave(waveId, updatedWave);
        return ResponseEntity.ok(waveDTO);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWave(@PathVariable("id")Long waveId){
        waveService.deleteWave(waveId);
        return ResponseEntity.ok("Deleted Successfully!");

    }
}
