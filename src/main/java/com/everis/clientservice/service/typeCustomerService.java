package com.everis.clientservice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.everis.clientservice.model.typeCustomer;
import com.everis.clientservice.repository.typeCustomerRepository; 

@Service
@Transactional
public class typeCustomerService {
	@Autowired
	typeCustomerRepository repository;
	
	public void save(String description) {
		typeCustomer obj = new typeCustomer(null, description);
		repository.save(obj); 
	}
	
	public int listSize() {
		return repository.findAll().size();
	}
	
	public boolean findByName(String description) {
		return repository.existsByDescription(description);
	}
	
	public typeCustomer searchByDescription(String description) {
		return repository.findByDescription(description); 
	}

}
