package es.uji.proyecto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uji.proyecto.dao.ActivityTypeDao;
import es.uji.proyecto.dao.CustomerDao;
import es.uji.proyecto.dao.InstructorDao;
import es.uji.proyecto.dao.InstructorRequestDao;
import es.uji.proyecto.model.ActivityType;
import es.uji.proyecto.model.Customer;
import es.uji.proyecto.model.InstructorRequest;

@Controller
@RequestMapping("/instructorRequest")
public class InstructorRequestController {
	
	@Autowired
	private InstructorRequestDao instructorRequestDao;
	private void loadReferenceData(ModelMap model) {
        model.put("noms",activityTypes());
    }

	@Autowired
	private ActivityTypeDao actDao;
		
	
	
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
	
	
	@RequestMapping(value="/adjunto/{nid}")
	public String showPDF(@PathVariable String nid, Model model) {
		model.addAttribute("instructorRequest", instructorRequestDao.getInstructorRequest(nid));
		return "instructorRequest/adjunto";
	}
	
	
	@RequestMapping(value="/accept/{nid}")
	public String processAccept(@PathVariable String nid) {
			instructorRequestDao.acceptInstructorRequest(nid);
	       return "redirect:../../instructor/list"; 
	}
	@Value("${upload.file.directory}")
	   private String uploadDirectory;
	
	
	@ModelAttribute("activityTypes")
	public List<String> activityTypes(){
		List<ActivityType> actTypes = actDao.getActivityTypes();
		List<String> noms = actTypes.stream()
		           .map(ActivityType::getStructure)
		           .collect(Collectors.toList());
		return noms;
	}
	
	
	
	@RequestMapping(value="/add", method=RequestMethod.GET) 
	public String addInstructorRequest(@ModelAttribute(name="activityTypes") List<String> activityTypes,Model model) {
		/*List<ActivityType> actTypes = actDao.getActivityTypes();
		   List<String> noms = actTypes.stream()
		           .map(ActivityType::getStructure)
		           .collect(Collectors.toList());*/
		model.addAttribute("instructorRequest", new InstructorRequest());
		//model.addAttribute("activityTypes", noms);
		return "instructorRequest/add";
	}
	
	
	
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST) 
	public String processAddSubmit(@ModelAttribute("instructorRequest") InstructorRequest instructorRequest,
			BindingResult bindingResult,
			@ModelAttribute("activityTypes") String activity,
			 @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
		 InstructorRequestValidator instructorRequestValidator = new InstructorRequestValidator(); 
		 instructorRequestValidator.validate(instructorRequest, bindingResult);
		 if (bindingResult.hasErrors()){
			 
			 return "/instructorRequest/add";
		 }
			 
				
			
		
		
		
		if (file.isEmpty()) {
	          // Enviar mensaje de error porque no hay fichero seleccionado
	          redirectAttributes.addFlashAttribute("message", 
	                                           "Please select a file to upload");
	          instructorRequestValidator.setFichero(0);
	          instructorRequestValidator.validate(instructorRequest, bindingResult); 
	          if (bindingResult.hasErrors()){
	 			 
	 			 return "/instructorRequest/add";
	 		 }
	 		 
	      }

	      try {
	          // Obtener el fichero y guardarlo
	          byte[] bytes = file.getBytes();
	          Path path = Paths.get(uploadDirectory + "peticiones/" 
	                                        + instructorRequest.getNid()+".pdf");
	          Files.write(path, bytes);

	          redirectAttributes.addFlashAttribute("message",
	                  "You successfully uploaded '" + path + "'");

	      } catch (IOException e) {
	          e.printStackTrace();
	      }

	      instructorRequestDao.addInstructorRequest(instructorRequest);
	      return "redirect:list";
	   }

}

