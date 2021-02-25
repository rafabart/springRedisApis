package com.redisAbstractions.controllers;

import com.redisAbstractions.entities.Customer;
import com.redisAbstractions.services.CustomerService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@Api(tags = "/customers -> Operações do cliente SEM cache")
public class CustomerController {

    @Qualifier("customerServiceImpl")
    private final CustomerService customerServiceImpl;


    @PostMapping
    @ResponseStatus(CREATED)
    public Customer create(@RequestBody final Customer customer) {
        return customerServiceImpl.create(customer);
    }


    @GetMapping
    @ResponseStatus(OK)
    public List<Customer> findAll() {
        return customerServiceImpl.fetchAll();
    }


    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        customerServiceImpl.delete(id);
    }


    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Customer fetchById(@PathVariable final Long id) {
        return customerServiceImpl.fetchById(id);
    }


    @PutMapping
    @ResponseStatus(OK)
    public Customer update(@PathVariable final Long id,
                           @RequestBody final Customer customer) {
        return customerServiceImpl.update(id, customer);
    }
}
