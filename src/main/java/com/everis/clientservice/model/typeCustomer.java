package com.everis.clientservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*; 

@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor 

@Document(collection  = "type-customers")
public class typeCustomer {
	@Id
	private String idclient;
	private String description;
}
