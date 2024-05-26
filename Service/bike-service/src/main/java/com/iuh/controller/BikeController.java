package com.iuh.controller;

import com.iuh.config.RestTemplateConfig;
import com.iuh.entity.Bike;
import com.iuh.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bike")
public class BikeController {
    private final BikeService bikeService;

    private final RestTemplateConfig restTemplateConfig;

    @Autowired
    private RetryTemplate retryTemplate;

    @Autowired
    public BikeController(BikeService bikeService, RestTemplateConfig restTemplateConfig){
        this.bikeService = bikeService;
        this.restTemplateConfig = restTemplateConfig;
    }

    @GetMapping
    public List<Bike> getAllBikes(){
        return bikeService.getAllBike();
    }

    @GetMapping("/{bikeId}")
    @Cacheable(value = "bike", key = "#bikeId")
    public ResponseEntity<Bike> getBikeById(@PathVariable Long bikeId) {
        Optional<Bike> bike = bikeService.getBikeById(bikeId);
        return bike.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bike saveBike(@RequestBody Bike bike) {
        return bikeService.saveBike(bike);
    }

    @PutMapping("/{bikeId}")
    @CachePut(value = "bike", key = "#bikeId")
    public ResponseEntity<Bike> updateBike(@PathVariable Long bikeId, @RequestBody Bike bikeUpdate) {
        try {
            Bike updatedbike = bikeService.updateBike(bikeId, bikeUpdate);
            return ResponseEntity.ok(updatedbike);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bikeId}")
    @CacheEvict(value = "bike", allEntries = true)
    public ResponseEntity<Void> deleteBike(@PathVariable Long bikeId) {
        try {
            bikeService.deleteBike(bikeId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/comment")
    public Object getForObject(){
        String url = "http://localhost:8084/comment";
        return retryTemplate.execute(context -> restTemplateConfig.getForObject(url, Object.class));
    }


}
