package es.uji.proyecto.controller;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.proyecto.model.Customer;



public class CustomerValidator implements Validator {
	
	  public boolean supports(Class<?> cls) {
		  return Customer.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Customer.class.isAssignableFrom(cls);
	   }
	 
	  
	  public void validate(Object obj, Errors errors) {
		 Customer customer = (Customer)obj;
		 
		 
		 if (customer.getNid().trim().equals(""))
		       errors.rejectValue("nid", "obligatorio",
		                          "Es necesario introducir un valor.");
		 
		 // Validación para que el nombre no sea nulo:
		 if (customer.getName().trim().equals(""))
		       errors.rejectValue("name", "obligatorio",
		                          "Es necesario introducir un valor.");
		 
		 // Validación para que el género no sea nulo:
		 if (customer.getGender().trim().equals(""))
		       errors.rejectValue("gender", "obligatorio",
		                          "Es necesario introducir un valor: M para mujer o H para hombre.");  
		 
		 // Validación para que el email no sea nulo:
		 if (customer.getEmail().trim().equals(""))
		       errors.rejectValue("email", "obligatorio",
		                          "Es necesario introducir un email."); 
		 //Validación para que el email contenga una "@":
		 if (!customer.getEmail().contains("@"))
		       errors.rejectValue("email", "obligatorio",
		                          "Formato correcto: ejemplo@ejemplo.com"); 
		 
		 if(customer.getPassword().trim().equals("") || customer.getRepeat().trim().equals(""))
			 errors.rejectValue("password", "obligatorio",
					 			"Es necesario introducir la contraseña y repetirla");
		 
		 if(!customer.getPassword().equals(customer.getRepeat()))
			 errors.rejectValue("repeat", "coincidir",
					 			"Las contraseñas no coinciden");
	   
	   }


	}
