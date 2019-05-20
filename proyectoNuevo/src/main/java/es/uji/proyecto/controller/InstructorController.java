package es.uji.proyecto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uji.proyecto.dao.InstructorDao;
import es.uji.proyecto.model.Instructor;
import es.uji.proyecto.model.InstructorRequest;

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
	
	
	@RequestMapping(value="/createActivity/{nid}")
	public String createActivity(@PathVariable String nid, Model model) {
		String activities = instructorDao.getInstructor(nid).getActivities();
		List<String> items = Arrays.asList(activities.split("/"));
		model.addAttribute("activities", items);
		return "instructor/createActivity";
	}
	
	/*@RequestMapping(value="/createActivity", method=RequestMethod.POST) 
	public String processAddSubmit(@ModelAttribute("activities") InstructorRequest instructorRequest,
			@ModelAttribute("activityTypes") String activity,
			BindingResult bindingResult,@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
		 
	      
	      instructorRequestDao.addInstructorRequest(instructorRequest);
	      return "redirect:list";
	   }*/
	
	

}
