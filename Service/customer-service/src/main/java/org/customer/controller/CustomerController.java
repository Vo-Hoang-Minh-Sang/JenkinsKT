package org.customer.controller;

import org.customer.entity.Customer;
import org.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long customerId, @RequestBody Customer customerDetails) {
        try {
            Customer updateCustomer = customerService.updateCustomer(customerId, customerDetails);
            return ResponseEntity.ok(updateCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/order")
    public ResponseEntity<Object> getOrderAndCustomer() {
        Optional<Customer> orderOptional = customerService.getCustomerById(1L);

        if (orderOptional.isPresent()) {
            String orderUrl = "http://localhost:8082/order";
            Object order = restTemplate.getForObject(orderUrl, Object.class);

            Map<String, Object> result = new HashMap<>();
            result.put("customer", orderOptional.get());
            result.put("order", order);

            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
