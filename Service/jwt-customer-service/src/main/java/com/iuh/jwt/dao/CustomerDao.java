package com.iuh.jwt.dao;

import com.iuh.jwt.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends CrudRepository<Customer, String> {
}
