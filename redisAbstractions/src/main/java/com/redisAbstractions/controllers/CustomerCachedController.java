package com.redisAbstractions.controllers;

import com.redisAbstractions.entities.Customer;
import com.redisAbstractions.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/customers/cached")
@Api(tags = "/customers/cached -> Operações do cliente COM cache")
public class CustomerCachedController {

    @Qualifier("customerServiceCachedImpl")
    private final CustomerService customerServiceCachedImpl;


    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Salva um novo cliente no banco e no cache")
    public Customer create(@RequestBody final Customer customer) {
        return customerServiceCachedImpl.create(customer);
    }


    @GetMapping
    @ResponseStatus(OK)
    @ApiOperation(value = "Lista todos clientes (somente do banco - sem cache)")
    public List<Customer> findAll() {
        return customerServiceCachedImpl.fetchAll();
    }


    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove um cliente do banco e do cache")
    public void delete(@PathVariable final Long id) {
        customerServiceCachedImpl.delete(id);
    }


    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @ApiOperation(value = "Busca cliente primeiramente no cache e caso nao acha-lo busca no banco e se achar adiciona no cache")
    public Customer fetchById(@PathVariable final Long id) {
        return customerServiceCachedImpl.fetchById(id);
    }


    @PutMapping("/{id}")
    @ResponseStatus(OK)
    @ApiOperation(value = "Atualiza dados do cliente no cache e no banco")
    public Customer update(@PathVariable final Long id,
                           @RequestBody final Customer customer) {
        return customerServiceCachedImpl.update(id, customer);
    }


    @GetMapping("/clean")
    @ResponseStatus(OK)
    @ApiOperation(value = "Limpa o cache")
    public void cleanCache() {
        customerServiceCachedImpl.cleanCache();
    }
}

