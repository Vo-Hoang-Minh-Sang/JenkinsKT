package org.customer.service;

import org.customer.entity.Customer;
import org.customer.repositoy.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(long customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(long customerId, Customer customerDetail) {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        customer.setCustomerName(customerDetail.getCustomerName());
        customer.setCustomerAddress(customerDetail.getCustomerAddress());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(long customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        customerRepository.delete(customer);
    }


}
