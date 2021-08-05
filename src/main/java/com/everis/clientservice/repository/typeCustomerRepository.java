package com.everis.clientservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.everis.clientservice.model.typeCustomer;

@Repository
public interface typeCustomerRepository extends MongoRepository<typeCustomer, String> {
	typeCustomer findByDescription(String description);
	boolean existsByDescription(String description);
}
