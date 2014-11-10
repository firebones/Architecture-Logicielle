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

import ca.ulaval.glo4003.architecture_logicielle.appConfig.AppConfiguration;
import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.web.converters.EmployeeEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EmployeeViewModel;



@Controller
public class EmployeeController {
	
	private AppConfiguration configuration = new AppConfiguration();
	private EmployeeEntryConverter employeeConverter =  new EmployeeEntryConverter();
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public String addNewEmployee(Model model) {
	    model.addAttribute("newEmployee", new EmployeeEntry());
	    return "addEmployee";
	}
	/*@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewEmployeeForm(@ModelAttribute("newEmploye") EmployeeEntry newEmployee) {
		try {
			configuration.addUser(newEmployee);//addEmployee(newEmployee);
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
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public String employeeValidation(@Validated @ModelAttribute("newEmployee") EmployeeEntry newEmployee, BindingResult result){
		if(result.hasErrors()){
			return "addEmployee";
		}
		return "employeeList";
	}
	
	
	public String processAddNewEmployeeForm(@ModelAttribute("newEmployee") EmployeeEntry newEmployee) {
		try {
			configuration.addUser(newEmployee);
			//delegate.addEmployee(newEmployee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return "addEmployee";
		
	}
	/*
	@RequestMapping(value = "/empList", method = RequestMethod.GET)
	public String list(Model model) {
		//model.addAttribute("entries", converter.convert(repository.getAll()));
		model.addAttribute("entries", delegate.getAllEmployees());
		return "employeeList";
	}


}*/
	
	
	
}
