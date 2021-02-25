package com.redisTemplate.resource;

import com.redisTemplate.domain.Customer;
import com.redisTemplate.repository.CustomerCacheRepository;
import lombok.RequiredArgsConstructor;
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
public class CustomerResource {

    private final CustomerCacheRepository cacheRepository;


    @PostMapping
    @ResponseStatus(CREATED)
    public void saveCustomer(@RequestBody Customer customer) {
        cacheRepository.create(customer);
    }


    @GetMapping
    @ResponseStatus(OK)
    public List<Customer> fetchAllCustomer() {
        return cacheRepository.fetchAll();
    }


    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public Customer fetchCustomerById(@PathVariable("id") Long id) {
        return cacheRepository.fetchById(id).get();
    }


    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        cacheRepository.deleteById(id);
    }


    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable("id") Long id,
                               @RequestBody Customer customer) {
        cacheRepository.update(id, customer);
    }
}
