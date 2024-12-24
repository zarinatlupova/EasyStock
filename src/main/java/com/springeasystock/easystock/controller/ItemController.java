package com.springeasystock.easystock.controller;

import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private ItemService itemService;


    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO){
        ItemDTO savedItem = itemService.createItem(itemDTO);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);

    }
//    @GetMapping
//    public ResponseEntity<List<ItemDTO>> getAllItems(){
//        List<ItemDTO> items = itemService.getAllItems();
//        return ResponseEntity.ok(items);
//    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        // Получаем пагинированный список из сервиса
        Page<ItemDTO> itemPage = itemService.getAllItems(pageable);

        // Получаем общее количество записей
        long totalElements = itemPage.getTotalElements();

        // Создаем HTTP-заголовок с общим количеством записей
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(totalElements));

        // Возвращаем пагинированный список и заголовки
        return ResponseEntity.ok()
                .headers(headers)
                .body(itemPage.getContent());
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable("id") Long itemId){
        ItemDTO savedItem = itemService.getItemById(itemId);
        return ResponseEntity.ok(savedItem);

    }

    @PutMapping("{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable("id")Long itemId,
                                                      @RequestBody ItemDTO updatedItem){
        ItemDTO itemDTO = itemService.updateItem(itemId, updatedItem);
        return ResponseEntity.ok(itemDTO);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id")Long itemId){
        itemService.deleteItem(itemId);
        return ResponseEntity.ok("Deleted Successfully!");

    }
}
