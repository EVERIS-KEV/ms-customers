package com.everis.clientservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.everis.clientservice.model.client;

@Repository
public interface clientRepository extends MongoRepository<client, String> {
	boolean existsById(String id);
	boolean existsByFirstname(String firstname);
	boolean existsByLastname(String lastname);
}
