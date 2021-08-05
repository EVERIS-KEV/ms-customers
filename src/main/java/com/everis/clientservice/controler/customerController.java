package com.everis.clientservice.controler; 

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.everis.clientservice.service.customerService;
import com.everis.clientservice.service.typeCustomerService;
import com.everis.clientservice.dto.*; 

import reactor.core.publisher.*; 

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/clients")
public class customerController {
	@Autowired 
	private customerService serviceCustomer;
	@Autowired 
	private typeCustomerService serviceCustomerType;
	
	@GetMapping("/")
	public Flux<Object> list(){
		return serviceCustomer.listAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Object> details(@PathVariable("id") String id){
		return serviceCustomer.detailsClient(id);
	}
	
	@PutMapping("/update/{id}")
	public Mono<Object> update(@PathVariable("id") String id, @RequestBody @Valid  customerFrom from, BindingResult bindinResult) {
		String msg = ""; 
		
		if (bindinResult.hasErrors()) {
			for (int i = 0; i < bindinResult.getAllErrors().size(); i++) 
				msg = bindinResult.getAllErrors().get(0).getDefaultMessage();
			return Mono.just(new message(msg));
		} 
		
		if (!serviceCustomerType.findByName( from.getTypecustomer() )) 
			return Mono.just(new message("Tipo incorrecto."));
		
		return serviceCustomer.update(id, from.getFirstname(), from.getLastname());
	}
	
	@PostMapping("/save")
	public Mono<Object> create(@RequestBody @Valid customerFrom from, BindingResult bindinResult) {
		String msg = ""; 
		
		if (bindinResult.hasErrors()) {
			for (int i = 0; i < bindinResult.getAllErrors().size(); i++) 
				msg = bindinResult.getAllErrors().get(0).getDefaultMessage();
			return Mono.just(new message(msg));
		}  
		
		if (!serviceCustomerType.findByName( from.getTypecustomer() )) 
			return Mono.just(new message("Tipo incorrecto."));
		
		return serviceCustomer.register(from.getFirstname(), from.getLastname(), from.getTypecustomer());
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<Object> update(@PathVariable("id") String id){
		return serviceCustomer.delete(id);
	}
	
}
