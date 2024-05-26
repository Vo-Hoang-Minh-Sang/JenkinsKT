package com.iuh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iuh.entity.Bike;
import java.util.Optional;

public interface BikeRepository extends JpaRepository<Bike, Long> {
    Optional<Bike> findByBikeId(Long bikeId);
}
