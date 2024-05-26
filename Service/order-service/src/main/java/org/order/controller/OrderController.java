package org.order.controller;

import lombok.RequiredArgsConstructor;
import org.order.config.RestTemplateConfig;
import org.order.entity.Order;
import org.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    RestTemplateConfig restTemplateConfig;
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getAllOrder(){
        return orderService.getAllOrder();
    }

    @PostMapping("/add")
    public String createOrder(@RequestBody Order order){
        orderService.addOrder(order);
        return "them order thanh cong";
    }

    @GetMapping("/{orderId}")
    @Cacheable(value= "order", key="#orderId")
    public ResponseEntity<Order> getOrderById(@PathVariable long orderId){
        Optional<Order> order = orderService.getOrderById(orderId);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{orderId}")
    @CachePut(value = "order",key = "#orderId")
    public ResponseEntity<Order> updateOrder(@PathVariable long orderId, @RequestBody Order orderUpdate) {
        try {
            Order updateOrder = orderService.updateOrder(orderId, orderUpdate);
            return ResponseEntity.ok(updateOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{orderId}")
    @CacheEvict(value = "order", allEntries = true)
    public ResponseEntity<Void> deleteOrder(@PathVariable long orderId) {
        try {
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //resttemplate
    @Autowired
    private RestTemplateConfig restTemplate;

    @GetMapping("/customer")
    public ResponseEntity<Object> getOrderAndCustomer() {
        Optional<Order> orderOptional = orderService.getOrderById(1L);

        if (orderOptional.isPresent()) {
            String customerUrl = "http://localhost:8081/customer";
            Object customer = restTemplate.getForObject(customerUrl, Object.class);

            Map<String, Object> result = new HashMap<>();
            result.put("order", orderOptional.get());
            result.put("customer", customer);

            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bike")
    public ResponseEntity<Object> getOrderAndBike() {
        Optional<Order> orderOptional = orderService.getOrderById(1L);

        if (orderOptional.isPresent()) {
            String bikeUrl = "http://localhost:8083/bike";
            Object bike = restTemplate.getForObject(bikeUrl, Object.class);

            Map<String, Object> result = new HashMap<>();
            result.put("order", orderOptional.get());
            result.put("bike", bike);

            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
