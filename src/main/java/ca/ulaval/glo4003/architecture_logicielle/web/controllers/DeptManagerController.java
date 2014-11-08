package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.web.converters.EmployeeEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.web.converters.ProjectEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.appConfig.AppConfiguration;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedTasks;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EmployeeViewModel;

@Controller
public class DeptManagerController {

	private AppConfiguration configuration = new AppConfiguration();
	
	
	private ProjectEntryConverter projectConverter =  new ProjectEntryConverter();
	private EmployeeEntryConverter employeeConverter =  new EmployeeEntryConverter();
	
	
	@RequestMapping(value = "/{email}/assignTasks", method = RequestMethod.GET)
	public String buildAssignTasksView(@PathVariable String email, Model model) {
		EmployeeEntry employee = (EmployeeEntry) configuration.getUserByEmail(email);
		String employeeId = employee.getName() + " - " + employee.getEmail();
		model.addAttribute("assignTasksView", projectConverter.toProjectViewModels(configuration.getAllProjects(), employee.getTasks(), employeeId));
		return "assignTasks";
	}
	
	@ModelAttribute("selectedTasks")
	public AssignedTasks assignedTasks() {
		return new AssignedTasks();
	}
	
	@RequestMapping(value = "/{email}/assignTasks", method = RequestMethod.POST)
	public String getAssignedTasks(@PathVariable String email, @ModelAttribute("assignedTasks") AssignedTasks assignTasks) {
		EmployeeEntry employee = (EmployeeEntry) configuration.getUserByEmail(email);
		List<TaskEntry> tasksList = new ArrayList<TaskEntry>();
		for (String taskId : assignTasks.getTasks()) {
			tasksList.add(configuration.getTaskById(Integer.parseInt(taskId)));
		}
		configuration.setTasksToUser(tasksList, employee);
		return "redirect:/employeeList";
	}
	
	@RequestMapping(value = "/{email}/assignTasksCancel", method = RequestMethod.GET)
	public String cancel() {
		return "redirect:/employeeList";
	}
	
	
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public String addNewEmployee(Model model) {
	    model.addAttribute("newEmployee", new EmployeeEntry());
	    return "addEmployee";
	}
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public String addNewEmployee(EmployeeViewModel newEmployeeViewModel){
		EmployeeEntry newEmployee = employeeConverter.toEmployee(newEmployeeViewModel);
		configuration.addUser(newEmployee);
		return "redirect:/employeeList";
	}
	
	@RequestMapping(value = "/employeeList", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("entries", configuration.getAllEmployees());
		return "employeeList";
	}
	
	@RequestMapping(value = "/{email}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String email) {
		EmployeeEntry employee = (EmployeeEntry) configuration.getUserByEmail(email);
		configuration.deleteUser(employee);
		return "redirect:/employeeList";
	}
}
