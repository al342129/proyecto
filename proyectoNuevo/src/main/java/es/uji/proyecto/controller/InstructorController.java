package es.uji.proyecto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import es.uji.proyecto.dao.ActivityDao;
import es.uji.proyecto.dao.InstructorDao;
import es.uji.proyecto.model.Activity;
import es.uji.proyecto.model.Instructor;
import es.uji.proyecto.model.InstructorRequest;
import es.uji.proyecto.model.UserDetails;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	@Autowired
	private InstructorDao instructorDao;
	
	@Autowired
	private ActivityDao activityDao;
		
	
	
	@RequestMapping("/list")
	public String listInstructors(Model model) {
		model.addAttribute("instructors", instructorDao.getInstructors());
		
		return "instructor/list";
	}
	
	@RequestMapping(value="/confirm/{nid}")
	public String confirmDelete(@PathVariable String nid, Model model) {
		model.addAttribute("instructor", instructorDao.getInstructor(nid));
	       return "instructor/confirm"; 
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
	
	
	@RequestMapping(value="/profile/{nid}")
	public String profileInstructors(@PathVariable String nid, Model model) {
		model.addAttribute("instructor", instructorDao.getInstructor(nid));
		return "instructor/profile";
	}
	
	@RequestMapping(value="/modify/{nid}", method = RequestMethod.GET)
	public String editInstructor(Model model, @PathVariable String nid) {
		model.addAttribute("instructor", instructorDao.getInstructor(nid));
		return "instructor/modify"; 
	}
	
	@RequestMapping(value="/modify/{nid}", method = RequestMethod.POST) 
	public String processUpdateSubmit(@PathVariable String nid, 
                            @ModelAttribute("instructor") Instructor instructor, 
                            BindingResult bindingResult, @RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {
//		 InstructorValidator instructorValidator = new InstructorValidator(); 
//		 instructorValidator.validate(instructor, bindingResult);
//		 if (bindingResult.hasErrors()) 
//			 return "instructor/modify";
		 
		 if (file.isEmpty()) {
	          // Enviar mensaje de error porque no hay fichero seleccionado
//	          redirectAttributes.addFlashAttribute("message", 
//	                                           "Please select a file to upload");
//	          instructorRequestValidator.setFichero(0);
//	          instructorRequestValidator.validate(instructorRequest, bindingResult); 
//	          if (bindingResult.hasErrors()){
//	 			 
//	 			 return "/instructorRequest/add";
//	 		 }
	 		 
	      }

	      try {
	          // Obtener el fichero y guardarlo
	          byte[] bytes = file.getBytes();
	          Path path = Paths.get(uploadDirectory + "fotos/" 
	                                        + instructor.getNid()+file.getOriginalFilename());
	          Files.write(path, bytes);

	          redirectAttributes.addFlashAttribute("message",
	                  "You successfully uploaded '" + path + "'");

	      } catch (IOException e) {
	          e.printStackTrace();
	      }
		 
		 
		 
		 
		 instructor.setProfileImage(instructor.getNid()+file.getOriginalFilename());
		 instructorDao.updateInstructor(instructor);
		 return "views/instructor"; 
	}
	
	
	
	
	
	
	
	
	
	@Value("${upload.file.directory}")
	   private String uploadDirectory;
	@RequestMapping(value="/createActivity/{nid}", method = RequestMethod.GET)
	public String createActivity(@PathVariable String nid, Model model) {
		String activities = instructorDao.getInstructor(nid).getActivities();
		List<String> items = Arrays.asList(activities.split("/"));
		Instructor instructor = instructorDao.getInstructor(nid);
		System.out.println(instructor);

		model.addAttribute("activity", new Activity());
		model.addAttribute("activities", items);
		model.addAttribute("instructor", instructor);
		return "instructor/createActivity";
	}
	
	@RequestMapping(value="/createActivity", method=RequestMethod.POST) 
	public String processAddSubmit(@ModelAttribute("activity") Activity activity,
			BindingResult bindingResult,@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
		 
		if (file.isEmpty()) {
	          // Enviar mensaje de error porque no hay fichero seleccionado
	          redirectAttributes.addFlashAttribute("message", 
	                                           "Please select a file to upload");
	          return "redirect:/add";
	      }

	      try {
	          // Obtener el fichero y guardarlo
	          byte[] bytes = file.getBytes();
	          Path path = Paths.get(uploadDirectory + "images/" 
	                                        + activity.getNidInstructor()+ file.getOriginalFilename());
	          Files.write(path, bytes);

	          redirectAttributes.addFlashAttribute("message",
	                  "You successfully uploaded '" + path + "'");

	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	      
	      String[] activityType = activity.getTypeName().split(",");
	      String typeName = activityType[0];
	      String level = activityType[1];
	      activity.setTypeName(typeName);
	      activity.setLevel(level);
	      instructorDao.createActivity(activity, file.getOriginalFilename());
	      return "redirect:/list";
	   }
	
	@RequestMapping("/listActivities/{nid}")
	public String listActivities(@PathVariable String nid,Model model,HttpSession session) {
		
		model.addAttribute("activities", instructorDao.getActivities(nid));
		if (session.getAttribute("user") == null) 
	       { 
	          model.addAttribute("user", new UserDetails()); 
	          session.setAttribute("nextUrl", "/instructor/listActivities/"+nid);
	          return "redirect:/user/login";
	       } 
		return "instructor/listActivities";
	}
	
	
	
	
	
	
	@RequestMapping(value="/modifyActivity/{idActivity}", method = RequestMethod.GET)
	public String modifyActivity(Model model, @PathVariable int idActivity) {
		model.addAttribute("activity", activityDao.getActivity(idActivity));
		return "instructor/modifyActivity"; 
	}
	
	@RequestMapping(value="/modifyActivity/{idActivity}", method = RequestMethod.POST) 
	public String processUpdateSubmit(@PathVariable int idActivity, 
                            @ModelAttribute("activity") Activity activity, 
                            BindingResult bindingResult) {
		 //InstructorValidator instructorValidator = new InstructorValidator(); 
		 //instructorValidator.validate(instructor, bindingResult);
		 //if (bindingResult.hasErrors()) 
		//	 return "instructor/modify";
		 activityDao.updateActivity(activity);
		 return "redirect:../list"; 
	}
	
	
	
	
	

}