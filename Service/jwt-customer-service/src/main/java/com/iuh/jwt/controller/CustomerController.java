package com.iuh.jwt.controller;

import com.iuh.jwt.entity.Customer;
import com.iuh.jwt.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostConstruct
    public void initRoleandCustomer(){
        customerService.initRoleAndCustomer();
    }

    @PostMapping({"/register"})
    public Customer registerNewCustomer(@RequestBody Customer customer){
        return customerService.registerNewCustomer(customer);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only access to the admin";
    }

    @GetMapping({"/forCustomer"})
    @PreAuthorize("hasRole('Customer')")
    public String forCustomer(){
        return "This URL is only access to the customer";
    }
}
