package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.model.Employe;


@Controller
public class HelloWorldController 
{
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
	
	/*@RequestMapping(value = "/createEmployee", method = RequestMethod.GET)
	public String createEmployee() {
		return "createEmployee";
	}*/
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String createEmployee(Model model) {
		model.addAttribute("entry", new Employe());
		return "createEmployee";
	}
}
