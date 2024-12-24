package com.springeasystock.easystock.service;

import com.springeasystock.easystock.record.CustomerDTO;
import com.springeasystock.easystock.record.ItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {
    ItemDTO createItem(ItemDTO itemDTO);
    Page<ItemDTO> getAllItems(Pageable pageable);

    ItemDTO getItemById(Long itemId);

    ItemDTO updateItem(Long itemId, ItemDTO updatedItem);

    void deleteItem(Long itemId);
    public boolean existsById(Long id);

}
