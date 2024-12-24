package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.exception.CustomNotFoundException;
import com.springeasystock.easystock.mapper.ItemMapper;
import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.record.OrderListDTO;
import com.springeasystock.easystock.mapper.OrderListMapper;
import com.springeasystock.easystock.model.Item;
import com.springeasystock.easystock.model.OrderList;
import com.springeasystock.easystock.repo.ItemRepository;
import com.springeasystock.easystock.repo.OrderListRepository;
import com.springeasystock.easystock.service.OrderListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderListServiceImpl implements OrderListService {
    private OrderListRepository orderListRepository;

    @Autowired
    private ItemRepository itemRepository;
    @Transactional
    @Override
    public OrderListDTO createOrderList(OrderListDTO orderListDTO) {
        OrderList orderList = OrderListMapper.toEntity(orderListDTO);
        OrderList savedOrderList = orderListRepository.save(orderList);
        return OrderListMapper.toDTO(savedOrderList);
    }


    @Override
    public Page<OrderListDTO> getAllOrderLists(Pageable pageable) {
        Page<OrderList> orderLists = orderListRepository.findAll(pageable);
        return orderLists.map(OrderListMapper::toDTO);
    }

    @Override
    public OrderList assignItemToOrderList(Long orderListId, Long itemId) {
        Set<Item> itemSet = null;
        OrderList orderList = orderListRepository.findById(orderListId).get();
        Item item = itemRepository.findById(itemId).get();
        itemSet = orderList.getItemIds();
        itemSet.add(item);
        orderList.setItemIds(itemSet);
        return orderListRepository.save(orderList);
    }
    @Override
    public OrderList unassignItemFromOrderList(Long orderListId, Long itemId) {
        OrderList orderList = orderListRepository.findById(orderListId)
                .orElseThrow(() -> new IllegalArgumentException("OrderList not found with id: " + orderListId));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + itemId));

        Set<Item> itemSet = orderList.getItemIds();
        if (itemSet.contains(item)) {
            itemSet.remove(item);
            orderList.setItemIds(itemSet);
            return orderListRepository.save(orderList);
        } else {
            throw new IllegalArgumentException("Item with id " + itemId + " is not assigned to OrderList with id " + orderListId);
        }
    }

    @Transactional
    @Override
    public OrderListDTO getOrderListById(Long orderListId) {
        OrderList orderList = orderListRepository.findById(orderListId)
                .orElseThrow(() -> new CustomNotFoundException(orderListId));
        return OrderListMapper.toDTO(orderList);

    }
    @Transactional
    @Override
    public OrderListDTO updateOrderList(Long orderListId, OrderListDTO updatedItem) {
        OrderList orderList = orderListRepository.findById(orderListId)
                .orElseThrow(() -> new CustomNotFoundException(orderListId));
        orderList.setCustomerId(updatedItem.customerId());
        orderList.setOrderStatus(updatedItem.orderStatus());
        orderList.setTotalPrice(updatedItem.totalPrice());
        orderList.setOrderDate(updatedItem.orderDate());
        orderList.setDeliveryDate(updatedItem.deliveryDate());
        OrderList updatedObj =  orderListRepository.save(orderList);
        return OrderListMapper.toDTO(updatedObj);
    }
    @Transactional
    @Override
    public void deleteOrderList(Long orderListId) {
        OrderList orderList = orderListRepository.findById(orderListId)
                .orElseThrow(() -> new CustomNotFoundException(orderListId));
        orderListRepository.deleteById(orderList.getId());
    }

    @Override
    public boolean existsById(Long id) {
        return orderListRepository.existsById(id);
    }

}
