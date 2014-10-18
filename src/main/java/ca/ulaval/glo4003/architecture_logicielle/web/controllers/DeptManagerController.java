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
import ca.ulaval.glo4003.architecture_logicielle.dao.ProjectRepository;
import ca.ulaval.glo4003.architecture_logicielle.dao.ProjectRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.dao.UserRepository;
import ca.ulaval.glo4003.architecture_logicielle.dao.UserRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedTasks;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EmployeeViewModel;

@Controller
public class DeptManagerController {

	private ProjectRepository projectRepository = new ProjectRepositoryImpl();
	private ProjectEntryConverter projectConverter =  new ProjectEntryConverter();
	
	private UserRepository userRepository = new UserRepositoryImpl();
	private EmployeeEntryConverter employeeConverter =  new EmployeeEntryConverter();
	
	
	@RequestMapping(value = "/{email}/assignTasks", method = RequestMethod.GET)
	public String buildAssignTasksView(@PathVariable String email, Model model) {
		EmployeeEntry employee = (EmployeeEntry) userRepository.getUserByEmail(email);
		String employeeId = employee.getName() + " - " + employee.getEmail();
		model.addAttribute("assignTasksView", projectConverter.convertProjects(projectRepository.getAllProjects(), employee.getTasks(), employeeId));
		return "assignTasks";
	}
	
	@ModelAttribute("selectedTasks")
	public AssignedTasks assignedTasks() {
		return new AssignedTasks();
	}
	
	@RequestMapping(value = "/{email}/assignTasks", method = RequestMethod.POST)
	public String getAssignedTasks(@PathVariable String email, @ModelAttribute("assignedTasks") AssignedTasks assignTasks) {
		EmployeeEntry employee = (EmployeeEntry) userRepository.getUserByEmail(email);
		List<TaskEntry> tasksList = new ArrayList<TaskEntry>();
		for (String taskId : assignTasks.getTasks()) {
			tasksList.add(projectRepository.getTaskById(Integer.parseInt(taskId)));
		}
		userRepository.setTasksToUser(tasksList, employee);
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
		EmployeeEntry newEmployee = employeeConverter.convertEmployee(newEmployeeViewModel);
		userRepository.addUser(newEmployee);
		return "redirect:/employeeList";
	}
	
	@RequestMapping(value = "/employeeList", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("entries", userRepository.getAllEmployees());
		return "employeeList";
	}
	
	@RequestMapping(value = "/{email}/delete", method = RequestMethod.GET)
	public String list(@PathVariable String email) {
		EmployeeEntry employee = (EmployeeEntry) userRepository.getUserByEmail(email);
		userRepository.deleteUser(employee);
		return "redirect:/employeeList";
	}
}
