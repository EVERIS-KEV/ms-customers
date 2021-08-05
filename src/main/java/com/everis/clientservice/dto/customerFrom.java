package com.everis.clientservice.dto;

import javax.validation.constraints.*; 
import lombok.*; 

@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor 

public class customerFrom {
	private String idclient;
	
	@NotBlank(message= "El campo nombre no debe estar vacio.")
	@Size(min = 3, message = "El campo nombre de tener mas de 3 carácteres como mínimo.")
	private String firstname;
	
	@NotBlank(message= "El campo apellido no debe estar vacio.")
	@Size(min = 4, message = "El campo apellido de tener mas de 4 carácteres como mínimo.")
	private String lastname;  
	
	@NotBlank(message= "El campo tipo no debe estar vacio.")
	private String typecustomer;  
}
