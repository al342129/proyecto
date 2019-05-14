package es.uji.proyecto.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.proyecto.model.Instructor;



public class InstructorValidator implements Validator {
	
	  public boolean supports(Class<?> cls) {
		  return Instructor.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Instructor.class.isAssignableFrom(cls);
	   }
	 
	  
	  public void validate(Object obj, Errors errors) {
		 Instructor instructor = (Instructor)obj;
		 // Validación para que el nombre no sea nulo:
		 if (instructor.getName().trim().equals(""))
		       errors.rejectValue("name", "obligatorio",
		                          "Es necesario introducir un valor.");
		 
		 // Validación para que el estado no sea nulo:
		 if (instructor.getState().trim().equals(""))
		       errors.rejectValue("state", "obligatorio",
		                          "Es necesario introducir un estado: Disponible o No disponible.");  
		 

	   
	   }


	}




