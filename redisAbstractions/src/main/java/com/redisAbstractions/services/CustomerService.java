package com.redisAbstractions.services;

import com.redisAbstractions.entities.Customer;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

public interface CustomerService {


    Customer create(Customer customer);

    Customer update(Long id, Customer customer);

    void delete(Long id);

    List<Customer> fetchAll();

    Customer fetchById(Long id);

    @CacheEvict(value = "customer", allEntries = true)
    default void cleanCache() {
    }
}
