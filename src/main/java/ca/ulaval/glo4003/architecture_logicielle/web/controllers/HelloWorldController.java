package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
