package com.jospehsun.springbootmall.service;

import com.jospehsun.springbootmall.dto.CreateOrderRequest;
import com.jospehsun.springbootmall.dto.OrderQueryParams;
import com.jospehsun.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);
}
