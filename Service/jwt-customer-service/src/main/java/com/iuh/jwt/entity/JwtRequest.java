package com.iuh.jwt.entity;

public class JwtRequest {
    private String customerPhone;
    private String customerPassword;

    public String getCustomerPhone() {return customerPhone;}

    public void setCustomerPhone(String customerPhone) {this.customerPhone = customerPhone;}

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
}
