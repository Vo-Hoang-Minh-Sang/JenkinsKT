package org.order.service;

import org.order.entity.Order;
import org.order.repositoy.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(long orderId){
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public Order updateOrder(long orderId, Order orderUpdate){
        Order order = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        order.setOrderName(orderUpdate.getOrderName());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(long orderId){
        Order order = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        orderRepository.delete(order);
    }
}
