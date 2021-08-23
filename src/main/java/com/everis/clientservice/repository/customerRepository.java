package com.everis.clientservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.everis.clientservice.model.customer;

@Repository
public interface customerRepository extends MongoRepository<customer, String> {
	@Override
	boolean existsById(String id);
	boolean existsByFirstname(String firstname);

	boolean existsByLastname(String lastname);

	boolean existsByDni(String dni);

	customer findByDni(String dni);
}
