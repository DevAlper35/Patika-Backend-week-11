package com.rest.RestAPI.dao;

import com.rest.RestAPI.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository <Customer,Integer> {
}
