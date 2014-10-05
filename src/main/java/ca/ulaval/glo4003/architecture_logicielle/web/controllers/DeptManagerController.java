package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.converters.EmployeeEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.converters.ProjectEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.dao.EmployeeRepositoryImp;
import ca.ulaval.glo4003.architecture_logicielle.dao.ProjectRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.model.Employee;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedTasks;

@Controller
public class DeptManagerController {

	private ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl();
	private ProjectEntryConverter projectConverter =  new ProjectEntryConverter();
	
	private EmployeeRepositoryImp employeeRepository = new EmployeeRepositoryImp();
	private EmployeeEntryConverter employeeConverter =  new EmployeeEntryConverter();
	
	@RequestMapping(value = "/deptManager", method = RequestMethod.GET)
	public String buildDeptManagerView(Model model) {
		model.addAttribute("employees", employeeConverter.convertEmployees(employeeRepository.getAllEmployees()));
		return "homeDeptManager";
	}
	
	@RequestMapping(value = "/{email}/assignTasks", method = RequestMethod.GET)
	public String buildAssignTasksView(@PathVariable String email, Model model) {
		
		Employee employee = employeeRepository.getEmployeByEmail(email);
		String employeeId = employee.getLastName().toUpperCase() + ", " + employee.getFirstName() + " - " + employee.getEmail();
		model.addAttribute("assignTasksView", projectConverter.convertProjects(projectRepository.getAllProjects(), employee.getTasks(), employeeId));
		return "assignTasks";
	}
	
	@ModelAttribute("selectedTasks")
	public AssignedTasks assignedTasks() {
		return new AssignedTasks();
	}
	
	@RequestMapping(value = "/{email}/assignTasks", method = RequestMethod.POST)
	public String getAssignedTasks(@PathVariable String email, @ModelAttribute("assignedTasks") AssignedTasks assignTasks) {
		
		Employee employee = employeeRepository.getEmployeByEmail(email);
		
		// TODO JP: décommenter cette ligne lorsque le repository sera corrigé..
		//employeeRepository.setTasks(assignTasks);
		
		return "redirect:/deptManager";
	}
	
	@RequestMapping(value = "/{email}/assignTasksCancel", method = RequestMethod.GET)
	public String cancel() {
		return "redirect:/deptManager";
	}
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	  public String addNewEmployee(Model model) {
	     Employee newEmploye = new Employee();
	     model.addAttribute("newEmploye", newEmploye);
	     return "addEmployee";
	  }
	
}
