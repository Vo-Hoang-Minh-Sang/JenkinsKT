package com.iuh.service;

import com.iuh.entity.Bike;
import com.iuh.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeServiceImpl implements BikeService {
    private final BikeRepository bikeRepository;

    @Autowired
    public BikeServiceImpl(BikeRepository bikeRepository){
        this.bikeRepository = bikeRepository;
    }

    @Override
    public List<Bike> getAllBike() {
        return bikeRepository.findAll();
    }

    @Override
    public Optional<Bike> getBikeById(Long bikeId) {
        return bikeRepository.findByBikeId(bikeId);
    }

    @Override
    public Bike saveBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    @Override
    public Bike updateBike(Long bikeId, Bike bikeUpdate) {
        Bike bike = bikeRepository.findByBikeId(bikeId)
                .orElseThrow(() -> new RuntimeException("Bike not found with id: " + bikeId));
        bike.setBikeName(bikeUpdate.getBikeName());
        bike.setPrice(bikeUpdate.getPrice());
        return bikeRepository.save(bike);
    }

    @Override
    public void deleteBike(Long bikeId) {
        Bike bike = bikeRepository.findByBikeId(bikeId)
                .orElseThrow(() -> new RuntimeException("Bike not found with id: " + bikeId));
        bikeRepository.delete(bike);
    }
}
