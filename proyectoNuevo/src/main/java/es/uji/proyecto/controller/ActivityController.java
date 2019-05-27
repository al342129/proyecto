package es.uji.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.proyecto.dao.ActivityDao;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	
	@Autowired
	private ActivityDao activityDao;
		
	
	
	@RequestMapping("/list")
	public String listCustomers(Model model) {
		model.addAttribute("activities", activityDao.getActivities());
		
		return "activity/list";
	}
	
}
