package es.uji.proyecto.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.proyecto.dao.CustomerDao;
import es.uji.proyecto.dao.InstructorDao;
import es.uji.proyecto.dao.UserDao;
import es.uji.proyecto.model.Customer;
import es.uji.proyecto.model.UserDetails;

public class UserValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDetails.class.isAssignableFrom(cls);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		//Comprobamos que usuario y contraseña no esten vacios
		
	}

}
@Controller
public class LoginController{
	@Autowired
	//private UserDao usuario;
	private CustomerDao customerDao;
	private InstructorDao instructorDao;
	
	
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new UserDetails());
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String checkLogin(@ModelAttribute("user") UserDetails user,  		
				BindingResult bindingResult, HttpSession session) {
		UserValidator userValidator = new UserValidator(); 
		userValidator.validate(user, bindingResult); 
		if (bindingResult.hasErrors()) {
			return "login";
		}
	       // Comprova que el login siga correcte 
		// intentant carregar les dades de l'usuari 
		
		
		
		//Debemos crear un metodo getNombre en Customer y en Instructor
		
		
		Customer customer = customerDao.getCustomer(user.getName()); 
		
		
		
		
		
		if (user == null) {
			bindingResult.rejectValue("password", "badpw", "Contrasenya incorrecta"); 
			return "login";
		}
		// Autenticats correctament. 
		// Guardem les dades de l'usuari autenticat a la sessió
		session.setAttribute("user", user); 
			
		// Torna a la pàgina principal
		return "redirect:/";
	}

	@RequestMapping("/logout") 
	public String logout(HttpSession session) {
		session.invalidate(); 
		return "redirect:/";
	}
}

	
	
	
	

}