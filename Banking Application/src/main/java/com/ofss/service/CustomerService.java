package com.ofss.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ofss.model.Customer;
import com.ofss.model.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Add a customer
    public ResponseEntity<Object> addCustomer(Customer customer) {
        customerRepository.save(customer);
        return ResponseEntity.ok("Customer added successfully!");
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }
 // Get customer by ID
    public ResponseEntity<Object> getCustomerById(Integer customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.status(404).body("Customer not found with ID: " + customerId);
        }
    }
 // Update customer by ID
    public ResponseEntity<Object> updateCustomer(Integer customerId, Customer updatedCustomer) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(customerId);
        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            // Update fields
            existingCustomer.setFirstName(updatedCustomer.getFirstName());
            existingCustomer.setLastName(updatedCustomer.getLastName());
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            existingCustomer.setEmailId(updatedCustomer.getEmailId());

            customerRepository.save(existingCustomer); // save updated entity
            return ResponseEntity.ok("Customer updated successfully!");
        } else {
            return ResponseEntity.status(404).body("Customer not found with ID: " + customerId);
        }
    }
 // Partial update customer by ID
    public ResponseEntity<Object> patchCustomer(Integer customerId, Map<String, Object> updates) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(customerId);
        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();

            // Update only the fields provided
            updates.forEach((key, value) -> {
                switch (key) {
                    case "firstName":
                        existingCustomer.setFirstName((String) value);
                        break;
                    case "lastName":
                        existingCustomer.setLastName((String) value);
                        break;
                    case "phoneNumber":
                        existingCustomer.setPhoneNumber((String) value);
                        break;
                    case "emailId":
                        existingCustomer.setEmailId((String) value);
                        break;
                }
            });

            customerRepository.save(existingCustomer);
            return ResponseEntity.ok("Customer partially updated successfully!");
        } else {
            return ResponseEntity.status(404).body("Customer not found with ID: " + customerId);
        }
    }

}

