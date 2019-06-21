package es.uji.proyecto.controller;

import javax.servlet.http.HttpSession;

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
	
	@RequestMapping("/listAdmin")
	public String listAdmin(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		System.out.print("Entro en el listar actividades");
		return "activity/listAdmin";
		
		
	
	   }
	
	@RequestMapping(value="/show/{idActivity}")
	public String showActivity(@PathVariable int idActivity, Model model) {
		model.addAttribute("activity", activityDao.getActivity(idActivity));
		return "activity/show";
	}
	
	
	@RequestMapping("/escalada")
	public String home1(Model model) {
		model.addAttribute("activities", activityDao.getActivitiesHome1());
		return "views/home1";
		
	}
	
	
	@RequestMapping("/rafting")
	public String home2(Model model) {
		model.addAttribute("activities", activityDao.getActivitiesHome2());
		return "views/home2";
		
	}
	
	@RequestMapping("/piragüismo")
	public String home3(Model model) {
		model.addAttribute("activities", activityDao.getActivitiesHome3());
		return "views/home3";
		
	}

	
}
	
