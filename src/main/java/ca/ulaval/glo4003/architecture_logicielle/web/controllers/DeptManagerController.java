package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.converters.EmployeeEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.converters.ProjectEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.dao.ProjectRepository;
import ca.ulaval.glo4003.architecture_logicielle.dao.ProjectRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.dao.UserRepository;
import ca.ulaval.glo4003.architecture_logicielle.dao.UserRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedTasks;

@Controller
public class DeptManagerController {

	private ProjectRepository projectRepository = new ProjectRepositoryImpl();
	private ProjectEntryConverter projectConverter =  new ProjectEntryConverter();
	
	private UserRepository userRepository = new UserRepositoryImpl();
	private EmployeeEntryConverter employeeConverter =  new EmployeeEntryConverter();
	
	@RequestMapping(value = "/deptManager", method = RequestMethod.GET)
	public String buildDeptManagerView(Model model) {
		model.addAttribute("employees", employeeConverter.convertEmployees(userRepository.getAllEmployees()));
		return "homeDeptManager";
	}
	
	@RequestMapping(value = "/{email}/assignTasks", method = RequestMethod.GET)
	public String buildAssignTasksView(@PathVariable String email, Model model) {
		
		EmployeeEntry employee = (EmployeeEntry) userRepository.getUserByEmail(email);
		String employeeId = employee.getName() + " - " + employee.getEmail();
		model.addAttribute("assignTasksView", projectConverter.convertProjects(projectRepository.getAllProjects(), employee.getTasksString(), employeeId));
		return "assignTasks";
	}
	
	@ModelAttribute("selectedTasks")
	public AssignedTasks assignedTasks() {
		return new AssignedTasks();
	}
	
	@RequestMapping(value = "/{email}/assignTasks", method = RequestMethod.POST)
	public String getAssignedTasks(@PathVariable String email, @ModelAttribute("assignedTasks") AssignedTasks assignTasks) {
		
		EmployeeEntry employee = (EmployeeEntry) userRepository.getUserByEmail(email);
		
		// TODO JP: d�commenter cette ligne lorsque le repository sera corrig�..
		//employeeRepository.setTasks(assignTasks);
		
		return "redirect:/deptManager";
	}
	
	@RequestMapping(value = "/{email}/assignTasksCancel", method = RequestMethod.GET)
	public String cancel() {
		return "redirect:/deptManager";
	}
	
	@RequestMapping(value = "/createEmployee", method = RequestMethod.GET)
	  public String addNewEmployee(Model model) {
	     EmployeeEntry newEmploye = new EmployeeEntry();
	     model.addAttribute("newEmploye", newEmploye);
	     return "createEmployee";
	  }
	
}
