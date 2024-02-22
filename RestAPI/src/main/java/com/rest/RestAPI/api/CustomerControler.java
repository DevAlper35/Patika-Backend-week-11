package com.rest.RestAPI.api;

import com.rest.RestAPI.business.abstracts.ICustomerService;
import com.rest.RestAPI.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerControler {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@RequestBody Customer customer){
        return this.customerService.save(customer);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Customer update(@RequestBody Customer customer){
        return this.customerService.update(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable ("id") int id){
        this.customerService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getById(@PathVariable ("id") int id){
        return this.customerService.getById(id);
    }
}