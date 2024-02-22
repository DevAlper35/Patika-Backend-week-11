package com.rest.RestAPI.business.abstracts;

import com.rest.RestAPI.entities.Customer;

import java.util.List;

public interface ICustomerService {
    Customer getById(int id);
    Customer save(Customer customer);
    Customer update(Customer customer);

    void delete(int id);

    List<Customer> findAll();
}
