package es.uji.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.uji.proyecto.dao.CustomerDao;
import es.uji.proyecto.model.Customer;


@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerDao customerDao;
		
	
	
	@RequestMapping("/list")
	public String listCustomers(Model model) {
		model.addAttribute("customers", customerDao.getCustomers());
		
		return "customer/list";
	}
	
	
	
	@RequestMapping(value="/add") 
	public String addCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer/add";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST) 
	public String processAddSubmit(@ModelAttribute("customer") Customer customer,
			BindingResult bindingResult) {
		 if (bindingResult.hasErrors())
				return "customer/add";
		 customerDao.addCustomer(customer);
		 return "redirect:list";
	 }
	
	@RequestMapping(value="/update/{nid}", method = RequestMethod.GET) 
	public String editCustomer(Model model, @PathVariable String nid) { 
		model.addAttribute("customer", customerDao.getCustomer(nid));
		return "customer/update"; 
	}

	@RequestMapping(value="/update/{nid}", method = RequestMethod.POST) 
	public String processUpdateSubmit(@PathVariable String nid, 
	                        @ModelAttribute("customer") Customer customer, 
	                        BindingResult bindingResult) {
		 if (bindingResult.hasErrors()) 
			 return "customer/update";
		 customerDao.updateCustomer(customer);
		 return "redirect:../list"; 
	}

	@RequestMapping(value="/delete/{nid}")
	public String processDelete(@PathVariable String nid) {
	       customerDao.deleteCustomer(nid);
	       return "redirect:../list"; 
	}

	
}











