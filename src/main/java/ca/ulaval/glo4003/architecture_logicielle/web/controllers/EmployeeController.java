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
	
	@RequestMapping(value = "/manager/addEmployee", method = RequestMethod.GET)
	public String addNewEmployee(Model model) {
	    model.addAttribute("newEmployee", new EmployeeViewModel());
	    return "addEmployee";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new ValidatorEmployee());
		
	}
	
	@RequestMapping(value = "/manager/addEmployee", method = RequestMethod.POST)
	public String employeeValidation(@Validated @ModelAttribute("newEmployee") EmployeeViewModel newEmployeeViewModel, BindingResult result){
		if(result.hasErrors()){
			return "addEmployee";
		}
		
		try {
			EmployeeEntry newEmployee = employeeConverter.toEmployee(newEmployeeViewModel);
			configuration.addUser(newEmployee);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	   return "redirect:/manager/employeeList";
	}
	
}
