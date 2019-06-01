
package es.uji.proyecto.controller;

import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.proyecto.dao.ReservationDao;
import es.uji.proyecto.dao.UserDao;
import es.uji.proyecto.model.ActivityType;
import es.uji.proyecto.model.Customer;
import es.uji.proyecto.model.Reservation;
import es.uji.proyecto.model.UserDetails; 



@Controller 
@RequestMapping("/reservation") 
public class ReservationController {
   private ReservationDao reservationDao;

   @Autowired 
   public void setReservation(ReservationDao reservationDao) {
       this.reservationDao = reservationDao;
   }
  
   @RequestMapping("/list") 
   public String listReservations(HttpSession session, Model model) {
       if (session.getAttribute("user") == null) 
       { 
          model.addAttribute("user", new UserDetails()); 
          session.setAttribute("nextUrl", "/reservation/list");
          return "redirect:../user/login";
       } 
       model.addAttribute("reservations", reservationDao.getReservations());
       return "user/list";
   }
   
	
	
	
   
	
	
//	@RequestMapping(value="/add/{idActivity}", method=RequestMethod.POST) 
//	public String processAddSubmit(@ModelAttribute("reservation") Reservation reservation,
//			BindingResult bindingResult) {
//		 	//falta todo el codigo validador
//		System.out.print("Entro en el add 2 de reserva");
//			reservationDao.addReservation(reservation);
//			return "reservation/add";
//		 
//	 }
//	
//	
	@RequestMapping(value="/add{idActivity}", method=RequestMethod.POST) 
	public String processAddSubmit(@ModelAttribute("reservation") Reservation reservation,
			BindingResult bindingResult) {
		// CustomerValidator customerValidator = new CustomerValidator(); 
		 //customerValidator.validate(customer, bindingResult);
		 //if (bindingResult.hasErrors())
			//	return "customer/add";
		System.out.print("Entro en el add post de reserva");
		String bookingNumber= reservationDao.getSaltString();
		String transactionNumber=reservationDao.getSaltString();
		Localdate fecha=LocalDate.now();
		reservation.setBookingDate(Date.now());
		reservation.setBookingNumber(bookingNumber);
		reservation.setTransactionNumber(transactionNumber);
		reservation.setNumberOfPeople();
		 reservationDao.addReservation(reservation);
		 return "redirect:list";
	 }
	
	@RequestMapping(value="/add/{idActivity}", method=RequestMethod.GET) 
	public String addReservation(HttpSession session , Model model, @PathVariable int idActivity) {
		
		 if (session.getAttribute("user") == null) 
	       { 
			 String newId= String.valueOf(idActivity);
	          model.addAttribute("user", new UserDetails()); 
	          System.out.print(idActivity);
	          session.setAttribute("nextUrl",  "/reservation/add/"+newId);
	          return "/user/login";
	       } 
		model.addAttribute("reservation", new Reservation());
		System.out.print("Entro en el add de ests reserva");
		return "reservation/add";
	}
   
   
}


