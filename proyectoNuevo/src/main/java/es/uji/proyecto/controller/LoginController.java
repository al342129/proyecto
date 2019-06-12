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

import es.uji.proyecto.dao.UserDao;
import es.uji.proyecto.model.UserDetails;



class UserValidator implements Validator { 
	@Override
	public boolean supports(Class<?> cls) { 
		return UserDetails.class.isAssignableFrom(cls);
	}
	@Override 
	public void validate(Object obj, Errors errors) {
	  // Exercici: Afegeix codi per comprovar que 
         // l'usuari i la contrasenya no estiguen buits 
         // ...
		UserDetails userDetails = (UserDetails)obj;
		 if (userDetails.getNid().trim().equals(""))
		       errors.rejectValue("nid", "obligatori",
		                          "Cal introduir un valor");
	
		if (userDetails.getPassword().trim().equals(""))
		       errors.rejectValue("password", "obligatori",
	                   "Cal introduir un valor");
	}
}


@Controller
@RequestMapping("/user")
public class LoginController {
	@Autowired
	private UserDao userDao;

	@RequestMapping("/login")
	public String login(Model model, HttpSession session) {
//		if(session.getId()!=null) {
//			UserDetails user= (UserDetails) session.getAttribute("user");
//			if(user.getUserType().equals("customer")) {
//				return "/views/customer";
//			}
//			
//			if(user.getUserType().equals("instructor")) {
//				System.out.println("Traza 76");
//				return "views/instructor";
//			}
//			else return "views/admin";
//		}
		model.addAttribute("user", new UserDetails());
		
		System.out.println("Traza 1");
		return "user/login";
		
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String checkLogin(@ModelAttribute("user") UserDetails user,  		
				BindingResult bindingResult, HttpSession session) {
		
		System.out.println("Traza mil");
		UserValidator userValidator = new UserValidator(); 
		userValidator.validate(user, bindingResult); 
		if (bindingResult.hasErrors()) {
			System.out.println("Traza 2");
			return "user/login";
		}
	       // Comprova que el login siga correcte 
		// intentant carregar les dades de l'usuari 
		user = userDao.loadUserByUsername(user.getNid(),user.getPassword()); 
		if (user == null) {
			bindingResult.rejectValue("password", "badpw", "Contrasenya incorrecta"); 
			System.out.println("Traza no");
			return "user/login";
		}
		// Autenticats correctament. 
		// Guardem les dades de l'usuari autenticat a la sessió
		session.setAttribute("user", user); 
		
		
		//devuelve a la pagina donde se dirigia antes de autenticarse 
		if(session.getAttribute("nextUrl") != null ) {
			System.out.println("Traza 4");
			System.out.println(session.getAttribute("nextUrl"));
			return "redirect:" + session.getAttribute("nextUrl");
		}
		System.out.println("Traza 5");
		if(user.getUserType().equals("customer")) {
			return "/views/customer";
		}
		
		if(user.getUserType().equals("instructor")) {
			System.out.println("Traza 76");
			return "views/instructor";
		}
		else return "views/admin";
	}

	@RequestMapping("/logout") 
	public String logout(HttpSession session) {
		session.invalidate(); 
		return "redirect:/";
	}
	
}
