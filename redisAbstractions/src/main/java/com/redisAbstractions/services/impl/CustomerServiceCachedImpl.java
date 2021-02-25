package com.redisAbstractions.services.impl;

import com.redisAbstractions.entities.Customer;
import com.redisAbstractions.exceptions.CustomerNotFoundException;
import com.redisAbstractions.repositories.CustomerRepository;
import com.redisAbstractions.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceCachedImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    @CachePut(value = "customer", key = "#customer.id")
    public Customer create(final Customer customer) {

        log.info("CACHE - CREATE -> access to DB H2");

        return customerRepository.save(customer);
    }


    @Override
    @CachePut(value = "customer", key = "#id")
    public Customer update(final Long id,
                           final Customer customer) {

        log.info("CACHE - UPDATE -> access to DB H2");

        customer.setId(fetchById(id).getId());
        return customerRepository.save(customer);
    }


    @Override
    @CacheEvict(value = "customer", key = "#id")
    public void delete(final Long id) {

        log.info("CACHE - DELETE -> access to DB H2");
        fetchById(id);

        customerRepository.deleteById(id);
    }


    @Override
    public List<Customer> fetchAll() {

        log.info("CACHE - LIST_ALL -> access to DB H2");

        return customerRepository.findAll();
    }


    @Override
    @Cacheable(value = "customer", key = "#id", unless = "#result == null")
    public Customer fetchById(final Long id) {

        log.info("CACHE - FECTH_BY_ID -> access to DB H2");

        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }
}
