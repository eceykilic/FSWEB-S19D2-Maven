package com.workintech.s18d4.service;

import com.workintech.s18d4.dao.CustomerRepository;
import com.workintech.s18d4.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    //doğrusu exception ile 404 dönmeli
    @Override
    public Customer find(long id) {

        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer delete(long id) {
        Customer foundCustomer = find(id);
        customerRepository.delete(foundCustomer);
        return foundCustomer;
    }
}