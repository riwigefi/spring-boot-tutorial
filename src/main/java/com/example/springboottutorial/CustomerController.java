package com.example.springboottutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * create a web controller
 * A web controller handles HTTP request for your Spring Application
 * The application will use `/add` endpoint to add `Customer` objects to database
 * the `/list` endpoint to fetch all `Customer` objects from database,
 * and the `/find/${id}` endpoint to find the customer with the specified ID
 *
 * dependencies injection major types:
 * 1. constructor-based dependency injection
 *    - constructor-based injection is recommended for required dependencies allowing them to be immutable and
 *      preventing them to be null
 * 2. setter-based dependency injection
 *    - setter-based injection is recommended fot optional dependencies
 */

@RestController
public class CustomerController {
    // baser-bases injection
    private CustomerRepository customerRepository;
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/add")
    public String addCustomer(@RequestParam String first, @RequestParam String last) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        customerRepository.save(customer);
        return "Added a new customer to repo!";
    }

    @GetMapping("/list")
    public Iterable<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Integer id) {
        return customerRepository.findCustomersById(id);
    }

}
