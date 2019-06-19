
package es.uji.proyecto.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.proyecto.dao.UserDao;
import es.uji.proyecto.model.UserDetails; 



@Controller 
@RequestMapping("/user") 
public class UserController {
   private UserDao userDao;

   @Autowired 
   public void setUserDao(UserDao userDao) {
       this.userDao = userDao;
   }
  
   @RequestMapping("/list") 
   public String listUsers(HttpSession session, Model model) {
	   System.out.println("Entro en el /user/list \n");
       if (session.getAttribute("user") == null) 
       { 
    	  System.out.println("Redirijo a /user/login \n");
          model.addAttribute("user", new UserDetails()); 
          session.setAttribute("nextUrl", "/user/list");
          return "redirect:../user/login";
       } 
       model.addAttribute("users", userDao.listAllUsers());
       return "user/list";
   }
}


