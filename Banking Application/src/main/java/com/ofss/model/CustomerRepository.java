package com.ofss.model;

import org.springframework.data.repository.CrudRepository;

// Customer is the entity class, Integer is the primary key type
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
