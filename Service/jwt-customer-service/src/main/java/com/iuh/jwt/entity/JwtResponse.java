package com.iuh.jwt.entity;

public class JwtResponse {
    private Customer customer;
    private String jwtToken;

    public JwtResponse(Customer customer, String jwtToken) {
        this.customer = customer;
        this.jwtToken = jwtToken;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
