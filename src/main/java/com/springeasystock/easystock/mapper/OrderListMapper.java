package com.springeasystock.easystock.mapper;

import com.springeasystock.easystock.record.OrderListDTO;
import com.springeasystock.easystock.model.OrderList;

public class OrderListMapper {

    public static OrderListDTO toDTO(OrderList orderList) {
        return  new OrderListDTO(
                orderList.getId(),
                orderList.getCustomerId(),
                orderList.getOrderStatus(),
                orderList.getTotalPrice(),
                orderList.getOrderDate(),
                orderList.getDeliveryDate(),
                orderList.getItemIds()
        );
    }

    public static OrderList toEntity(OrderListDTO dto) {
        return  new OrderList(
                dto.id(),
                dto.customerId(),
                dto.orderStatus(),
                dto.totalPrice(),
                dto.orderDate(),
                dto.deliveryDate(),
                dto.itemIds()
        );
    }


}

