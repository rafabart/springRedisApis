package com.redisAbstractions.configs;

import com.redisAbstractions.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class RedisConfig {


    private final CacheManager cacheManager;
    private final CustomerRepository customerRepository;

    /*
    Após subir aplicação, busca todos os dados do cliente no banco e adiciona no cache.
    */
    @PostConstruct
    public void cachingAllValuesFromDb() {
        customerRepository.findAll().stream()
                .forEach(customer -> cacheManager.getCache("customer").put(customer.getId(), customer));
    }


    /*
    Antes de cair a aplicação, limpa o cache
    */
    @PreDestroy
    public void evictAllCacheValues() {
        cacheManager.getCacheNames()
                .parallelStream()
                .forEach(n -> cacheManager.getCache(n).clear());

    }
}
