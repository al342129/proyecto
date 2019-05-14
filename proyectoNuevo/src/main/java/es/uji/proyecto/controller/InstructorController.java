package es.uji.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import es.uji.proyecto.dao.InstructorDao;
import es.uji.proyecto.model.Instructor;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	@Autowired
	private InstructorDao instructorDao;
		
	
	
	@RequestMapping("/list")
	public String listInstructors(Model model) {
		model.addAttribute("instructors", instructorDao.getInstructors());
		
		return "instructor/list";
	}
	
	@RequestMapping(value="/delete/{nid}")
	public String processDelete(@PathVariable String nid) {
		instructorDao.deleteInstructor(nid);
	       return "redirect:../list"; 
	}
	
	@RequestMapping(value="/show/{nid}")
	public String showInstructors(@PathVariable String nid, Model model) {
		model.addAttribute("instructor", instructorDao.getInstructor(nid));
		return "instructor/show";
	}
	
	@RequestMapping(value="/modify/{nid}", method = RequestMethod.GET)
	public String editInstructor(Model model, @PathVariable String nid) {
		model.addAttribute("instructor", instructorDao.getInstructor(nid));
		return "instructor/modify"; 
	}
	
	@RequestMapping(value="/modify/{nid}", method = RequestMethod.POST) 
	public String processUpdateSubmit(@PathVariable String nid, 
                            @ModelAttribute("nadador") Instructor instructor, 
                            BindingResult bindingResult) {
		 InstructorValidator instructorValidator = new InstructorValidator(); 
		 instructorValidator.validate(instructor, bindingResult);
		 if (bindingResult.hasErrors()) 
			 return "instructor/modify";
		 instructorDao.updateInstructor(instructor);
		 return "redirect:../list"; 
	}

}
