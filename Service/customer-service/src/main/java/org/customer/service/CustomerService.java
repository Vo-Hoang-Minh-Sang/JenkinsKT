package org.customer.service;

import org.customer.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomer();
    Optional<Customer> getCustomerById(long customerId);
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(long customerId, Customer customerUpdate);
    void deleteCustomer(long customerId);
}
