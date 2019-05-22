package es.uji.proyecto.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ProjectControllerAdvice {   

	   @ExceptionHandler(value = ProjectException.class)
	   public ModelAndView handleClubException(ProjectException ex){ 

	       ModelAndView mav = new ModelAndView("error/exceptionError");
	       mav.addObject("message", ex.getMessage());
	       mav.addObject("name", ex.getName());
	       return mav;
	   }

	}

