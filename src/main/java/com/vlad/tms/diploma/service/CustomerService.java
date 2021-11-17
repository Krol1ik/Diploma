package com.vlad.tms.diploma.service;

import com.vlad.tms.diploma.model.entity.Customer;
import com.vlad.tms.diploma.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void addCustomer (Customer customer){
        customerRepository.save(customer);
    }

}
