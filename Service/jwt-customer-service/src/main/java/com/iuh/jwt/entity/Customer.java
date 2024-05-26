package com.iuh.jwt.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {
    @Id
    private String customerPhone;
    private String customerFirstName;
    private String customerLastName;
    private String customerPassword;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "CUSTOMER_ROLE",
        joinColumns = {
            @JoinColumn(name = "CUSTOMER_ID")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")
        }
    )
    private Set<Role> role;

    public String getCustomerPhone() {return customerPhone;}

    public void setCustomerPhone(String customerPhone) {this.customerPhone = customerPhone;}

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
