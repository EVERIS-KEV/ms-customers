package com.everis.clientservice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.everis.clientservice.dto.message;
import com.everis.clientservice.model.client;
import com.everis.clientservice.repository.clientRepository;
 
import reactor.core.publisher.*; 

@Service
@Transactional
public class clientService {
	@Autowired
	clientRepository repository;  
	
	public client searchClient(String id) {
		return repository.findById(id).get();
	}
	
	public Mono<Object> save(client obj) {  
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
			client obj = new client(id, firstname, lastname); 
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
	
	public Mono<Object> register(String firstname, String lastname){
		client obj = new client(null, firstname, lastname);
		return save(obj);
	}
	
	public Flux<Object> listAll(){  
		return Flux.fromIterable( repository.findAll() );
	}
}
