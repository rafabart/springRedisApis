package com.redisTemplate.repository;

import com.redisTemplate.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerCacheRepository {

    private static final String KEY = "CUSTOMERS";

    private final CacheManager cacheManager;


    public void create(final Customer customer) {
        try {
            cacheManager.getCache(KEY).put(customer.getId(), customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Customer> fetchAll() {
        return null;
    }


    public Optional<Customer> fetchById(final Long id) {
        return Optional.of((Customer) cacheManager.getCache(KEY).get(id));
    }


    public void deleteById(final Long id) {
        try {
            cacheManager.getCache(KEY).evictIfPresent(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(final Long id, Customer customer) {
        try {
            cacheManager.getCache(KEY).putIfAbsent(customer.getId(), customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
