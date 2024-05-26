package com.iuh.jwt.service;

import com.iuh.jwt.dao.CustomerDao;
import com.iuh.jwt.entity.Customer;
import com.iuh.jwt.entity.JwtRequest;
import com.iuh.jwt.entity.JwtResponse;
import com.iuh.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String customerPhone = jwtRequest.getCustomerPhone();
        String customerPassword = jwtRequest.getCustomerPassword();
        authenticate(customerPhone, customerPassword);

        UserDetails userDetails = loadUserByUsername(customerPhone);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        Customer customer = customerDao.findById(customerPhone).get();
        return new JwtResponse(customer, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String customerphone) throws UsernameNotFoundException {
        Customer customer = customerDao.findById(customerphone).get();

        if (customer != null) {
            return new org.springframework.security.core.userdetails.User(
                    customer.getCustomerPhone(),
                    customer.getCustomerPassword(),
                    getAuthority(customer)
            );
        } else {
            throw new UsernameNotFoundException("Customer not found with customerphone: " + customerphone);
        }
    }

    private Set getAuthority(Customer customer) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        customer.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String customerPhone, String customerPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerPhone, customerPassword));
        } catch (DisabledException e) {
            throw new Exception("CUSTOMER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}


