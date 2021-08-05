package com.everis.clientservice.model; 

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*; 

@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor 

@Document(collection  = "client")
public class client {
	@Id
	private String idclient;
	private String firstname;
	private String lastname;  
}
