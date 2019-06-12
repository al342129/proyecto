
package es.uji.proyecto.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.proyecto.dao.ActivityDao;
import es.uji.proyecto.dao.ReservationDao;
import es.uji.proyecto.dao.UserDao;
import es.uji.proyecto.model.Activity;
import es.uji.proyecto.model.ActivityType;
import es.uji.proyecto.model.Customer;
import es.uji.proyecto.model.Reservation;
import es.uji.proyecto.model.UserDetails; 



@Controller 
@RequestMapping("/reservation") 
public class ReservationController {
	
   private ReservationDao reservationDao;

   
   @Autowired
	private ActivityDao activityDao;
 //Guardar el objeto activityID
   
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
       System.out.println("Entro en el list del controller");
       model.addAttribute("reservations", reservationDao.getReservations());
       return "reservation/list";
   }
   
   @RequestMapping(value="/list/{nid}", method=RequestMethod.GET) 
   public String listReservationsByNid(HttpSession session, Model model, @PathVariable String nid) {
	   
       System.out.println("Si entro aqui es de milagro");
       model.addAttribute("reservations", reservationDao.getReservationsByNid(nid));
       System.out.println("Tercer milagro");

       return "customer/listReservations";
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
   
   
   
   
   //Codigo que falla en el ultimo commit
	
	
	@RequestMapping(value="/add/{idActivity}", method=RequestMethod.GET) 
	public String addReservation(HttpSession session , Model model, @PathVariable int idActivity) {
		
//		 if (session.getAttribute("user") == null) 
//	       { 
//			 String newId= String.valueOf(idActivity);
//	          model.addAttribute("user", new UserDetails()); 
//	          System.out.print(idActivity);
//	          session.setAttribute("nextUrl",  "/reservation/add/"+newId);
//	          return "/user/login";
//	       } 
		Activity activity = activityDao.getActivity(idActivity);
		model.addAttribute("reservation", new Reservation());
		model.addAttribute("activity", activity);
	
		return "reservation/add";
	}
   
	@RequestMapping(value="/add/{idActivity}", method=RequestMethod.POST) 
	public String processAddSubmit(@ModelAttribute("activity") Activity activity,@ModelAttribute("reservation") Reservation reservation,
			BindingResult bindingResult, @PathVariable int idActivity, HttpSession session , Model model) {
		ReservationValidator reservationValidator = new ReservationValidator(); 
		reservation.setBookingNumber(Integer.toString(activityDao.getActivity(idActivity).getVacancies()));
		 reservationValidator.validate(reservation, bindingResult);
		 
		 if (bindingResult.hasErrors())
				return "reservation/add";
		System.out.print("Entro en el add post de reserva");
		String bookingNumber= reservationDao.getSaltString();
		String transactionNumber=reservationDao.getSaltString();
		reservation.setBookingDate(LocalDate.now());
		reservation.setBookingNumber(bookingNumber);
		reservation.setTransactionNumber(transactionNumber);
		reservation.setNumberOfPeople(reservation.getNumberOfPeople());
		reservation.setState("Pendiente");
		UserDetails user= (UserDetails) session.getAttribute("user");
		reservation.setNid(user.getNid());
		System.out.println("enseñooooooo: ");
		System.out.println(user.getNid());
	
		System.out.println(reservation.toString());
		reservationDao.addReservation(reservation);
		
		
		System.out.println(reservation.toString());
		 return "redirect:../list/"+user.getNid();
	 }
	 
   
}


