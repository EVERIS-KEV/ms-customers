package com.everis.clientservice.controler; 

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.everis.clientservice.service.clientService;
import com.everis.clientservice.dto.*; 

import reactor.core.publisher.*; 

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/clients")
public class clientController {
	@Autowired 
	private clientService service;
	
	@GetMapping("/")
	public Flux<Object> list(){
		return service.listAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Object> details(@PathVariable("id") String id){
		return service.detailsClient(id);
	}
	
	@PutMapping("/update/{id}")
	public Mono<Object> update(@PathVariable("id") String id, @RequestBody @Valid  clientFrom from, BindingResult bindinResult) {
		String msg = ""; 
		if (bindinResult.hasErrors()) {
			for (int i = 0; i < bindinResult.getAllErrors().size(); i++) 
				msg = bindinResult.getAllErrors().get(0).getDefaultMessage();
			return Mono.just(new message(msg));
		} 
		return service.update(id, from.getFirstname(), from.getLastname());
	}
	
	@PostMapping("/save")
	public Mono<Object> create(@RequestBody @Valid clientFrom from, BindingResult bindinResult) {
		String msg = ""; 
		if (bindinResult.hasErrors()) {
			for (int i = 0; i < bindinResult.getAllErrors().size(); i++) 
				msg = bindinResult.getAllErrors().get(0).getDefaultMessage();
			return Mono.just(new message(msg));
		} 
		return service.register(from.getFirstname(), from.getLastname());
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<Object> update(@PathVariable("id") String id){
		return service.delete(id);
	}
	
}
