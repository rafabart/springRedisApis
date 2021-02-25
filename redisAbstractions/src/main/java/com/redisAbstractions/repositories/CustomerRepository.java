package com.redisAbstractions.repositories;

import com.redisAbstractions.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
