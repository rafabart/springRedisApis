package com.redisAbstractions.services.impl;

import com.redisAbstractions.entities.Customer;
import com.redisAbstractions.exceptions.CustomerNotFoundException;
import com.redisAbstractions.repositories.CustomerRepository;
import com.redisAbstractions.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public Customer create(final Customer customer) {
        log.info("CREATE -> access to DB H2");
        return customerRepository.save(customer);
    }


    @Override
    public Customer update(final Long id,
                           final Customer customer) {
        log.info("UPDATE -> access to DB H2");
        customer.setId(fetchById(id).getId());
        return customerRepository.save(customer);
    }


    @Override
    public void delete(final Long id) {
        log.info("DELETE -> access to DB H2");
        fetchById(id);
        customerRepository.deleteById(id);
    }


    @Override
    public List<Customer> fetchAll() {
        log.info("FETCH_ALL -> access to DB H2");
        return customerRepository.findAll();
    }


    @Override
    public Customer fetchById(final Long id) {
        log.info("FETCH_BY_ID -> access to DB H2");
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }
}
