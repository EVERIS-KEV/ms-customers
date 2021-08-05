package com.everis.clientservice.util;

import org.springframework.stereotype.Component;

import com.everis.clientservice.service.typeCustomerService;

import org.springframework.boot.CommandLineRunner; 
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class initialize implements CommandLineRunner {
	
	@Autowired
	typeCustomerService service;  

	@Override 
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		if (service.listSize()<1) {
			service.save("personal");  
			service.save("empresarial"); 
		}
		
		System.out.println("\n----       ----");
		System.out.println("-ENABLED TYPES-");
		System.out.println("----       ----\n");
		
	}
	
}
