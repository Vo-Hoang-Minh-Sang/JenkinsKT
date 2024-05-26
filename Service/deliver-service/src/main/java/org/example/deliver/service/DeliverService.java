package org.example.deliver.service;

import org.example.deliver.entity.Deliver;

import java.util.List;
import java.util.Optional;

public interface DeliverService {
    void addDeliver(Deliver deliver);
    List<Deliver>  getAllDeliver();
    Optional<Deliver> getDeliverById(long deliverId);

}
