package es.uji.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.proyecto.dao.ActivityDao;
import es.uji.proyecto.model.UserDetails;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	
	@Autowired
	private ActivityDao activityDao;
		
	
	
	@RequestMapping("/list")
	public String listCustomers(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		System.out.print("Entro en el listar actividades");
		return "activity/list";
		
		
	
	   }
	
	@RequestMapping(value="/show/{idActivity}")
	public String showActivity(@PathVariable int idActivity, Model model) {
		model.addAttribute("activity", activityDao.getActivity(idActivity));
		return "activity/show";
	}
	
//	@RequestMapping(value="/reserve/{idActivity}")
//	public String reserveActivity(@PathVariable int idActivity, Model model) {
//		model.addAttribute("reservation", activityDao.getReservation(idActivity));
//		return "activity/show";
//	}
	}
	

