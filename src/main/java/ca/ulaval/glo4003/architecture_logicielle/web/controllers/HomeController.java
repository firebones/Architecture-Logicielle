package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.ulaval.glo4003.architecture_logicielle.delegate.BusinessDelegate;

@Controller
public class HomeController 
{
	
	private BusinessDelegate delegate;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/users")
	public String users() {
		return "users";
	}
	
	@RequestMapping("/admin/page")
	public String admins() {
		return "admins";
	}
	
	@RequestMapping("/denied")
	public String denied() {
		return "denied";
	}
}
