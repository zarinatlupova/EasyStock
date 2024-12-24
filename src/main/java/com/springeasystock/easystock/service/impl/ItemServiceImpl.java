package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.exception.CustomNotFoundException;
import com.springeasystock.easystock.mapper.EmployeeMapper;
import com.springeasystock.easystock.model.Employee;
import com.springeasystock.easystock.record.EmployeeDTO;
import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.mapper.ItemMapper;
import com.springeasystock.easystock.model.Item;
import com.springeasystock.easystock.repo.ItemRepository;
import com.springeasystock.easystock.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = ItemMapper.toEntity(itemDTO);
        Item savedItem = itemRepository.save(item);
        return ItemMapper.toDTO(savedItem);
    }


    @Override
    public Page<ItemDTO> getAllItems(Pageable pageable) {
        Page<Item> items = itemRepository.findAll(pageable);
        return items.map(ItemMapper::toDTO);
    }

    @Override
    public ItemDTO getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new CustomNotFoundException(itemId));
        return ItemMapper.toDTO(item);

    }

    @Override
    public ItemDTO updateItem(Long itemId, ItemDTO updatedItem) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new CustomNotFoundException(itemId));
        item.setName(updatedItem.name());
        item.setSupplier(updatedItem.supplier());
        item.setSize(updatedItem.size());
        item.setPrice(updatedItem.price());
        item.setAsile(updatedItem.asile());
        item.setRack(updatedItem.rack());
        item.setShelf(updatedItem.shelf());
        Item updatedItemObj =  itemRepository.save(item);
        return ItemMapper.toDTO(updatedItemObj);
    }


    @Override
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new CustomNotFoundException(itemId));
        itemRepository.deleteById(item.getId());
    }

    @Override
    public boolean existsById(Long id) {
        return itemRepository.existsById(id);
    }

}
