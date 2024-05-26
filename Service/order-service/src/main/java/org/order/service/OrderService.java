package org.order.service;

import org.order.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void addOrder(Order order);
    List<Order> getAllOrder();
    Optional<Order> getOrderById(long orderId);
    Order updateOrder(long orderId, Order orderUpdate);
    void deleteOrder(long orderId);
}
