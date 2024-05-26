package com.iuh.jwt.service;

import com.iuh.jwt.dao.CustomerDao;
import com.iuh.jwt.dao.RoleDao;
import com.iuh.jwt.entity.Customer;
import com.iuh.jwt.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndCustomer() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDesciption("Admin role");
        roleDao.save(adminRole);

        Role customerRole = new Role();
        customerRole.setRoleName("Customer");
        customerRole.setRoleDesciption("Default role for newly created record");
        roleDao.save(customerRole);

        Customer adminCustomer = new Customer();
        adminCustomer.setCustomerPhone("0123456789");
        adminCustomer.setCustomerPassword(getEncodedPassword("admin@pass"));
        adminCustomer.setCustomerFirstName("admin");
        adminCustomer.setCustomerLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminCustomer.setRole(adminRoles);
        customerDao.save(adminCustomer);
    }

    public Customer registerNewCustomer(Customer customer) {
        Role role = roleDao.findById("Customer").get();
        Set<Role> customerRoles = new HashSet<>();
        customerRoles.add(role);
        customer.setRole(customerRoles);
        customer.setCustomerPassword(getEncodedPassword(customer.getCustomerPassword()));

        return customerDao.save(customer);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}

