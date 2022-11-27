package com.example.springboottutorial;

import org.springframework.data.repository.CrudRepository;

/**
 * Create a repository interface
 * This repository works with `Customer` entities and `Integer` IDs.
 * It also declares the `findCustomerBydId()` method.(它还声明了 `findCustomerById` 这个方法)
 * Spring Data JPA will derive q query based on this method's signature
 * which will select the `Customer` object for the specified ID.
 */

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findCustomersById(Integer id);
}