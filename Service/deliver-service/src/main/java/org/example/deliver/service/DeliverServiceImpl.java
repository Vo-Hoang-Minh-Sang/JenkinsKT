package org.example.deliver.service;

import org.example.deliver.entity.Deliver;
import org.example.deliver.repository.DeliverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliverServiceImpl implements DeliverService{

    @Autowired
    private DeliverRepository deliverRepository;

    @Override
    public void addDeliver(Deliver deliver) {
        deliverRepository.save(deliver);
    }

    @Override
    public List<Deliver> getAllDeliver() {
        return deliverRepository.findAll();
    }

    @Override
    public Optional<Deliver> getDeliverById(long deliverId) { return deliverRepository.findByDeliverId(deliverId);}
}
