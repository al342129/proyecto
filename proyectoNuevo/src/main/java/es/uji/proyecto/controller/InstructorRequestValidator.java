package es.uji.proyecto.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import es.uji.proyecto.model.InstructorRequest;

public class InstructorRequestValidator implements Validator {
	
	  public boolean supports(Class<?> cls) {
		  return InstructorRequest.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Instructor.class.isAssignableFrom(cls);
	   }
	 
	  
	  public void validate(Object obj, Errors errors) {
		 InstructorRequest instructorRequest = (InstructorRequest)obj;
		 
		 if (instructorRequest.getNid().trim().equals(""))
		       errors.rejectValue("nid", "obligatorio",
		                          "Es necesario introducir un valor.");
		 
		 if (instructorRequest.getName().trim().equals(""))
		       errors.rejectValue("name", "obligatorio",
		                          "Es necesario introducir un valor.");
		 
		 
		 
		 

	   
	   }


	}




