package es.uji.proyecto.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.proyecto.model.Customer;
import es.uji.proyecto.model.Reservation;

public class ConfirmValidator implements Validator {
	
	  public boolean supports(Class<?> cls) {
		  return Reservation.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Customer.class.isAssignableFrom(cls);
	   }
	 
	  
	  public void validate(Object obj, Errors errors) {
		 Reservation customer = (Reservation)obj;
		 
		 
		 if (customer.getState().trim().equals("Pendiente"))
		       errors.rejectValue("nid", "obligatorio",
		                          "Es necesario introducir un valor.");
		 
		 
	   
	   }


	}
