package es.uji.proyecto.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.proyecto.model.ActivityType;

public class ActivityTypeValidator implements Validator {
	
	  public boolean supports(Class<?> cls) {
		  return ActivityType.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Instructor.class.isAssignableFrom(cls);
	   }
	 
	  
	  public void validate(Object obj, Errors errors) {
		 ActivityType activityType = (ActivityType)obj;
		 // Validación para que el nombre no sea nulo:
		 if (activityType.getTypeName().trim().equals(""))
		       errors.rejectValue("typeName", "obligatorio",
		                          "Es necesario introducir un valor.");

		 

	   
	   }


	}




