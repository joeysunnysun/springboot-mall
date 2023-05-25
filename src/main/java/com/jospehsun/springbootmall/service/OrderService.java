package com.jospehsun.springbootmall.service;

import com.jospehsun.springbootmall.dto.CreateOrderRequest;
import com.jospehsun.springbootmall.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
