package org.example.deliver.controller;

import lombok.RequiredArgsConstructor;
import org.example.deliver.config.RestTemplateConfig;
import org.example.deliver.entity.Deliver;
import org.example.deliver.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/deliver")
@RequiredArgsConstructor
public class DeliverController {
    @Autowired
    RestTemplateConfig restTemplate;

    @Autowired
    DeliverService deliverService;

    @GetMapping
    public List<Deliver> getDelivers() {
        return deliverService.getAllDeliver();
    }

    @PostMapping("/add")
    public String createDeliver(@RequestBody Deliver deliver){
        deliverService.addDeliver(deliver);
        return "them deliver thanh cong";
    }

    @GetMapping("/{deliverId}")
    @Cacheable(value= "deliver", key="#deliverId")
    public ResponseEntity<Deliver> getDeliverById(@PathVariable long deliverId){
        Optional<Deliver> deliver = deliverService.getDeliverById(deliverId);
        return deliver.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/order")
    public ResponseEntity<Object> getDeliverAndOrder() {
        Optional<Deliver> deliverOptional = deliverService.getDeliverById(1L);

        if (deliverOptional.isPresent()) {
            String orderUrl = "http://localhost:8082/order";
            Object order = restTemplate.getForObject(orderUrl, Object.class);

            Map<String, Object> result = new HashMap<>();
            result.put("deliver", deliverOptional.get());
            result.put("order", order);

            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<Object> getDeliverAndCustomer() {
        Optional<Deliver> deliverOptional = deliverService.getDeliverById(1L);

        if (deliverOptional.isPresent()) {
            String customerUrl = "http://localhost:8081/customer";
            Object customer = restTemplate.getForObject(customerUrl, Object.class);

            Map<String, Object> result = new HashMap<>();
            result.put("deliver", deliverOptional.get());
            result.put("customer", customer);

            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
