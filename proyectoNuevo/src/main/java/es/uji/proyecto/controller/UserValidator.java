package es.uji.proyecto.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.proyecto.model.UserDetails;

class UserValidator implements Validator{
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDetails.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		
		//Comprobamos que usuario y contraseña no esten vacios
		UserDetails userDetails = (UserDetails)obj;
		 if (userDetails.getUsername().trim().equals(""))
		       errors.rejectValue("username", "obligatori",
		                          "Cal introduir un valor");
	
		if (userDetails.getPassword().trim().equals(""))
		       errors.rejectValue("password", "obligatori",
	                   "Cal introduir un valor");
		
	}
	
}