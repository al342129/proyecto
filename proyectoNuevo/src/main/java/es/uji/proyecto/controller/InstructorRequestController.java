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
@RequestMapping("/instructorRequest")
public class InstructorRequestController {
	
	@Autowired
	private InstructorRequestDao instructorRequestDao;
		
	
	
	@RequestMapping("/list")
	public String listInstructorRequests(Model model) {
		model.addAttribute("instructorRequests", instructorRequestDao.getInstructorRequests());
		
		return "instructorRequest/list";
	}
	
	
	
	@RequestMapping(value="/delete/{nid}")
	public String processDelete(@PathVariable String nid) {
		instructorRequestDao.deleteInstructorRequest(nid);
	       return "redirect:../list"; 
	}
	
	@RequestMapping(value="/show/{nid}")
	public String showInstructorRequests(@PathVariable String nid, Model model) {
		model.addAttribute("instructorRequest", instructorRequestDao.getInstructorRequest(nid));
		return "instructorRequest/show";
	}
	
	
	@RequestMapping(value="/accept/{nid}")
	public String processAccept(@PathVariable String nid) {
			instructorRequestDao.acceptInstructorRequest(nid);
	       return "redirect:../../instructor/list"; 
	}


}