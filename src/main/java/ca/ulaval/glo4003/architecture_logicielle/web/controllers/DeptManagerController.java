package ca.ulaval.glo4003.architecture_logicielle.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.appConfig.AppConfiguration;
import ca.ulaval.glo4003.architecture_logicielle.web.converters.ProjectEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedTasks;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.ProjectViewModel;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.TaskViewModel;
import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EmployeeViewModel;
import ca.ulaval.glo4003.architecture_logicielle.web.converters.EmployeeEntryConverter;

@Controller
public class DeptManagerController {

	private AppConfiguration configuration = new AppConfiguration();

	private ProjectEntryConverter projectConverter = new ProjectEntryConverter();
	private EmployeeEntryConverter employeeConverter = new EmployeeEntryConverter();


	@RequestMapping(value = "/manager/{email}/assignTasks", method = RequestMethod.GET)
	public String buildAssignTasksView(@PathVariable String email, Model model) {
		String employeeId = configuration.getUserByEmail(email).getName() + " - " + configuration.getUserByEmail(email).getEmail();
		model.addAttribute("assignTasksView", projectConverter
				.toProjectViewModels(configuration.getAllProjects(), configuration.getTasksEmployee(email), employeeId));
		return "assignTasks";
	}

	@ModelAttribute("selectedTasks")
	public AssignedTasks assignedTasks() {
		return new AssignedTasks();
	}

	@RequestMapping(value = "/manager/{email}/assignTasks", method = RequestMethod.POST)
	public String getAssignedTasks(@PathVariable String email, @ModelAttribute("assignedTasks") AssignedTasks assignTasks) {
		configuration.assignedTaskToEmployee(email, assignTasks.getTasks());
		return "redirect:/manager/employeeList";
	}

	@RequestMapping(value = "/manager/{email}/assignTasksCancel", method = RequestMethod.GET)
	public String cancel() {
		return "redirect:/manager/employeeList";
	}

	@RequestMapping(value = "/manager/employeeList", method = RequestMethod.GET)
	public String list(Model model) {
		
		model.addAttribute("entries", configuration.getAllEmployees());
		return "employeeList";
	}

	@RequestMapping(value = "/manager/{email}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String email) {

		configuration.deleteUser(configuration.getUserByEmail(email));
		return "redirect:/manager/employeeList";
	}

	@RequestMapping(value = "/manager/projectList", method = RequestMethod.GET)
	public String projectlist(Model model) {
		model.addAttribute("projects", configuration.getAllProjects());
		return "projectList";
	}

	@RequestMapping(value = "/manager/addProject", method = RequestMethod.GET)
	public String addNewProject(Model model) {
		model.addAttribute("project", new ProjectViewModel());
		return "addProject";
	}

	@RequestMapping(value = "/manager/addProject", method = RequestMethod.POST)
	public String addNewProject(ProjectViewModel newProjectViewModel) {
		configuration.addProject(projectConverter.toProjectEntry(newProjectViewModel));
		return "redirect:/manager/projectList";
	}

	@RequestMapping(value = "/manager/{id}/editProject", method = RequestMethod.GET)
	public String editProject(@PathVariable Integer id, Model model) {
		model.addAttribute("project", configuration.getProjectById(id));
		return "editProject";
	}

	@RequestMapping(value = "/manager/{id}/editProject", method = RequestMethod.POST)
	public String editProject(@PathVariable Integer id, ProjectViewModel updatedProjectViewModel) {
		configuration.updateProject(id, projectConverter.toProjectEntry(updatedProjectViewModel));
		return "redirect:/manager/projectList";
	}

	@RequestMapping(value = "/manager/{id}/addTask", method = RequestMethod.GET)
	public String addNewTask(Model model) {
		model.addAttribute("task", new TaskViewModel());
		return "addTask";
	}

	@RequestMapping(value = "/manager/{id}/addTask", method = RequestMethod.POST)
	public String addNewTask(@PathVariable Integer id, TaskViewModel newTaskViewModel) {
		newTaskViewModel.setId(0);
		configuration.addTask(id, projectConverter.toTaskEntry(newTaskViewModel));
		return "redirect:/manager/projectList";
	}

	@RequestMapping(value = "/manager/{id}/editTask", method = RequestMethod.GET)
	public String editTask(@PathVariable Integer id, Model model) {
		model.addAttribute("task", configuration.getTaskById(id));
		return "editTask";
	}

	@RequestMapping(value = "/manager/{id}/editTask", method = RequestMethod.POST)
	public String editTask(@PathVariable Integer id, TaskViewModel updatedTaskViewModel) {
		configuration.updateTask(id, projectConverter.toTaskEntry(updatedTaskViewModel));
		return "redirect:/manager/projectList";
	}

	@RequestMapping(value = "/manager/cancel", method = RequestMethod.GET)
	public String cancelProject() {
		return "redirect:/manager/projectList";
	}

	@RequestMapping(value = "/manager/{id}/cancel", method = RequestMethod.GET)
	public String cancelTask() {
		return "redirect:/manager/projectList";
	}
	
/*	@RequestMapping(value = "/manager/addEmployee", method = RequestMethod.GET)
	public String addNewEmployee(Model model) {
	    model.addAttribute("newEmployee", new EmployeeViewModel());
	    return "addEmployee";
	}
	
	@RequestMapping(value = "/manager/addEmployee", method = RequestMethod.POST)
	public String addNewEmployee(EmployeeViewModel newEmployeeViewModel){
		EmployeeEntry newEmployee = employeeConverter.toEmployee(newEmployeeViewModel);
		configuration.addUser(newEmployee);
		return "redirect:/manager/employeeList";
	}*/
	
	@RequestMapping(value = "/manager/{email}/updateRateHour", method = RequestMethod.GET)
	public String editRateHour(@PathVariable String email, Model model) {
		model.addAttribute("employee", (EmployeeEntry)configuration.getUserByEmail(email));
		return "updateRateHour";
	}

	@RequestMapping(value = "/manager/{email}/updateRateHour", method = RequestMethod.POST)
	public String editRateHour(@PathVariable String email, EmployeeViewModel employeeViewModel) {
		configuration.updateRateHourUser((EmployeeEntry)configuration.getUserByEmail(email), Double.parseDouble(employeeViewModel.rateHour));
		return "redirect:/manager/employeeList";
	}
	
	@RequestMapping(value = "/manager/{id}/employeeList", method = RequestMethod.GET)
	public String cancelChangeRateHour() {
		return "redirect:/manager/employeeList";
	}
}
