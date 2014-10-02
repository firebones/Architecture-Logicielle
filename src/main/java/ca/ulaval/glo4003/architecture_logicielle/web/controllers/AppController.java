package ca.ulaval.glo4003.architecture_logicielle.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.delegate.BusinessDelegate;
import ca.ulaval.glo4003.architecture_logicielle.model.Employe;




@Controller
public class AppController 
{
	private BusinessDelegate delegate;
	

	@RequestMapping("/")
	public String index() {
		return "login";
	}
	
	@RequestMapping("/index")
	public String indexPage() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	  public String getAddNewProductForm(Model model) {
	     Employe newEmploye = new Employe();
	     model.addAttribute("newEmploye", newEmploye);
	     return "addEmployee";
	  }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewEmployeeForm(@ModelAttribute("newEmploye") Employe newEmploye) {
		delegate = new BusinessDelegate();
		delegate.addEmployee(newEmploye);
	   return "addEmployee";
	}

}
