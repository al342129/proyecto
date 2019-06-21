package es.uji.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.proyecto.dao.ActivityTypeDao;
import es.uji.proyecto.dao.CustomerDao;
import es.uji.proyecto.model.ActivityType;
import es.uji.proyecto.model.Customer;

@Controller
@RequestMapping("/activityType")
public class ActivityTypeController {

	@Autowired
	private ActivityTypeDao activityTypeDao;
		
	
	
	@RequestMapping("/list")
	public String listActivityTypes(Model model) {
		model.addAttribute("activityTypes", activityTypeDao.getActivityTypes());
		
		return "activityType/list";
	}
	
	
	
	@RequestMapping(value="/add") 
	public String addCustomer(Model model) {
		model.addAttribute("activityType", new ActivityType());
		return "activityType/add";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST) 
	public String processAddSubmit(@ModelAttribute("activityType") ActivityType activityType,
			BindingResult bindingResult) {
		 ActivityTypeValidator activityTypeValidator = new ActivityTypeValidator(); 
		 activityTypeValidator.validate(activityType, bindingResult);
		 if (bindingResult.hasErrors())
				return "activityType/add";
		 try{
			 activityTypeDao.addActivityType(activityType);
		 }
		 catch(DuplicateKeyException e) {
			 throw new ProjectException(  
		             "Error! Ya existe este tipo de actividad en la base de datos " 
		            , "ClavePrimariaDuplicada"); 
		   } 
		   return "redirect:list";

	 }
	
	@RequestMapping(value="/update/{typeName}/{level}", method = RequestMethod.GET) 
	public String editActivityType(Model model, @PathVariable String typeName, @PathVariable String level) { 
		model.addAttribute("activityType", activityTypeDao.getActivityType(typeName,level));
		return "activityType/update"; 
	}

	@RequestMapping(value="/update/{typeName}/{level}", method = RequestMethod.POST) 
	public String processUpdateSubmit(@PathVariable String typeName, @PathVariable String level,
	                        @ModelAttribute("activityType") ActivityType activityType, 
	                        BindingResult bindingResult) {
		ActivityTypeValidator activityTypeValidator = new ActivityTypeValidator(); 
		 activityTypeValidator.validate(activityType, bindingResult);
		 if (bindingResult.hasErrors())
				return "activityType/update";
		 activityTypeDao.updateActivityType(activityType);
		 return "redirect:../../list"; 
	}

	@RequestMapping(value="/delete/{typeName}/{level}")
	public String processDelete(@PathVariable String typeName, @PathVariable String level) {
	       activityTypeDao.deleteActivityType(typeName,level);
	       return "redirect:../../list"; 
	}
	 


	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}


	
}