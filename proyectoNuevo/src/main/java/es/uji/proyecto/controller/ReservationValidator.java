package es.uji.proyecto.controller;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import es.uji.proyecto.model.Customer;
import es.uji.proyecto.model.Reservation;



public class ReservationValidator  implements Validator{
	
	
	  public boolean supports(Class<?> cls) {
		  return Customer.class.equals(cls);
		 // o, si volguérem tractar també les subclasses: 
		 // return Customer.class.isAssignableFrom(cls);
	   }
	 
	  
	  //public void validate(Object obj, Object obj2,  Errors errors) {
		// Activity activity = (Activity)obj;
		// Reservation reservation = (Reservation)obj2;
		 
		 //ActivityDao activity = new ActivityDao();
		 //int idActivity=reservation.getIdActivity();
		 //System.out.println("Ep:"+idActivity+"Ep \n");
		 //Activity act= activity.getActivity(idActivity);
		 //System.out.println("Opaop "+act+" aopa");

		 //if (reservation.getNumberOfPeople() > act.getVacancies())
		      // errors.rejectValue("numberOfPeople", 
		       //                   "Solo quedan "+ act.getVacancies()+" plazas disponobles." );
		 
		 
		//if (reservation.getNumberOfPeople() > activity.getVacancies() )
		//	errors.rejectValue("numberOfPeople", "obligatorio",
          //          "Es necesario introducir un valor.");
		 
	  // }


	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Reservation reservation = (Reservation)target;
		
		
		int vacancies = Integer.parseInt(reservation.getBookingNumber());
		System.out.println(vacancies);
		if (vacancies < reservation.getNumberOfPeople())
			errors.rejectValue("numberOfPeople", "obligatorio",
                    "No quedan suficientes plazas disponibles");
		
	}


	}
