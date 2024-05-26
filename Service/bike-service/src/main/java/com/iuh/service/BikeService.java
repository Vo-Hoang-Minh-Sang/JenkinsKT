package com.iuh.service;

import com.iuh.entity.Bike;

import java.util.List;
import java.util.Optional;

public interface BikeService  {
    List<Bike> getAllBike();
    Optional<Bike> getBikeById(Long bikeId);
    Bike saveBike (Bike bike);
    Bike updateBike(Long bikeId, Bike bikeUpdate);
    void deleteBike(Long bikeId);
}
