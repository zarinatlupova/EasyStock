package com.springeasystock.easystock.service;

import com.springeasystock.easystock.record.ItemDTO;
import com.springeasystock.easystock.record.OrderListDTO;
import com.springeasystock.easystock.model.OrderList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderListService {
    OrderListDTO createOrderList(OrderListDTO orderListDTO);
    Page<OrderListDTO> getAllOrderLists(Pageable pageable);


    OrderList assignItemToOrderList(Long orderListId, Long itemId);

    OrderList unassignItemFromOrderList(Long orderListId, Long itemId);

    OrderListDTO getOrderListById(Long orderListId);

    OrderListDTO updateOrderList(Long orderListId, OrderListDTO updatedItem);

    void deleteOrderList(Long orderListId);
    public boolean existsById(Long id);

}
