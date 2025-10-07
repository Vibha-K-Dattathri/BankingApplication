package com.ofss.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ofss.model.Customer;
import com.ofss.service.CustomerService;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // POST http://localhost:8184/customers
    @PostMapping("/customers")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    // GET http://localhost:8184/customers
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
 // GET http://localhost:8184/customers/id/{customerId}
    @GetMapping("/customers/id/{customerId}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Integer customerId) {
        return customerService.getCustomerById(customerId);
    }
 
    // PUT http://localhost:8184/customers/id/{customerId}
    	@PutMapping("/customers/id/{customerId}")
    	public ResponseEntity<Object> updateCustomer(
    	        @PathVariable Integer customerId,
    	        @RequestBody Customer customer) {
    	    return customerService.updateCustomer(customerId, customer);
    	}
    	// PATCH http://localhost:8184/customers/id/{customerId}
    	@PatchMapping("/customers/id/{customerId}")
    	public ResponseEntity<Object> patchCustomer(
    	        @PathVariable Integer customerId,
    	        @RequestBody Map<String, Object> updates) {
    	    return customerService.patchCustomer(customerId, updates);
    	}
    	

}
