package com.everis.clientservice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.everis.clientservice.dto.message;
import com.everis.clientservice.model.customer; 
import com.everis.clientservice.repository.customerRepository;
import com.everis.clientservice.repository.typeCustomerRepository;

import reactor.core.publisher.*; 

@Service
@Transactional
public class customerService {
	@Autowired
	customerRepository repository; 
	
	@Autowired
	typeCustomerRepository repositoryType;  
	
	public customer searchClient(String id) {
		return repository.findById(id).get();
	}
	
	public Mono<Object> save(customer obj) {  
		String m = "Datos registrados.";
		
		try {
			
			if ( repository.existsByFirstname( obj.getFirstname() ) && repository.existsByLastname( obj.getLastname() ) ) 
				m = "Datos repetidos."; 
			else repository.save(obj);
			
		} catch (Exception e) {
			m = "Datos inválidos.";
		} 
			
		
		return Mono.just(new message(m));
	}
	
	public Mono<Object> detailsClient(String id){
		if( repository.existsById(id) ) 
			return Mono.just( searchClient(id) );
		
		return Mono.just(new message("Cliente no registrado."));
	}
	
	public Mono<Object> update(String id, String firstname, String lastname){
		if( repository.existsById(id) ) {
			customer obj = new customer(id, firstname, lastname, null); 
			return save(obj);
		}
		
		return Mono.just(new message("Cliente no registrado."));
	}
	
	public Mono<Object> delete(String id){
		String m = "Cliente no registrado.";
		
		if( repository.existsById(id) ) {
			repository.deleteById(id);
			m = "Cliente eliminado.";
		}
		
		return Mono.just(new message(m));
	}
	
	public Mono<Object> register(String firstname, String lastname, String typecustomer){
		customer obj = new customer(null, firstname, lastname, repositoryType.findByDescription(typecustomer));
		return save(obj);
	}
	
	public Flux<Object> listAll(){  
		return Flux.fromIterable( repository.findAll() );
	}
}
