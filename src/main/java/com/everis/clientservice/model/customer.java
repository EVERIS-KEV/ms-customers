package com.everis.clientservice.model; 

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*; 

@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor 

@Document(collection  = "customers")
public class customer {
	@Id
	private String idclient;
	private String firstname;
	private String lastname;  
	private typeCustomer type;
}
