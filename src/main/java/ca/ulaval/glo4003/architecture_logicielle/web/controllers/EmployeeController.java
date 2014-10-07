package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.delegate.BusinessDelegate;
import ca.ulaval.glo4003.architecture_logicielle.model.Employee;

@Controller
public class EmployeeController {
	
	private BusinessDelegate delegate = new BusinessDelegate();
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	  public String getAddNewEmployeetForm(Model model) {
	     Employee newEmploye = new Employee();
	     model.addAttribute("newEmploye", newEmploye);
	     return "addEmployee";
	  }
	
	
	/*
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewEmployeeForm(@ModelAttribute("newEmploye") Employee newEmployee) {
		try {
			delegate.addEmployee(newEmployee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return "addEmployee";
	}*/
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new ValidatorEmployee());
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String employeeValidation(@Validated @ModelAttribute("newEmploye") Employee newEmployee, BindingResult result){
		if(result.hasErrors()){
			return "addEmployee";
		}
		return "index";
	}
	
	
	public String processAddNewEmployeeForm(@ModelAttribute("newEmploye") Employee newEmployee) {
		try {
			delegate.addEmployee(newEmployee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return "addEmployee";
	}


}
