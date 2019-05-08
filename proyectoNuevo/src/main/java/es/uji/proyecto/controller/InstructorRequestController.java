package es.uji.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.proyecto.dao.CustomerDao;
import es.uji.proyecto.dao.InstructorDao;
import es.uji.proyecto.dao.InstructorRequestDao;

@Controller
@RequestMapping("/instructor")
public class InstructorRequestController {
	
	@Autowired
	private InstructorRequestDao instructorRequestDao;
		
	
	
	/*@RequestMapping("/list")
	public String listInstructorRequests(Model model) {
		model.addAttribute("instructorRequests", instructorRequestDao.getInstructorRequests());
		
		return "instructorRequests/list";
	}
	
	//Codigo en prueba..............................................................
	@RequestMapping(value="/delete/{nid}")
	public String processDelete(@PathVariable String nid) {
		instructorRequestDao.deleteInstructorRequest(nid);
	       return "redirect:../list"; 
	}*/

}
