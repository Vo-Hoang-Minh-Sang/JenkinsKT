package org.example.deliver.repository;

import org.example.deliver.entity.Deliver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliverRepository extends JpaRepository<Deliver,Long> {
    Optional<Deliver> findByDeliverId(Long deliverId);
}
