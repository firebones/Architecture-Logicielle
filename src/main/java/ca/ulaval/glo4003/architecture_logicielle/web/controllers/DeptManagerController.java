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


	@RequestMapping(value = "/{email}/assignTasks", method = RequestMethod.GET)
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

	@RequestMapping(value = "/{email}/assignTasks", method = RequestMethod.POST)
	public String getAssignedTasks(@PathVariable String email, @ModelAttribute("assignedTasks") AssignedTasks assignTasks) {
		configuration.assignedTaskToEmployee(email, assignTasks.getTasks());
		return "redirect:/employeeList";
	}

	@RequestMapping(value = "/{email}/assignTasksCancel", method = RequestMethod.GET)
	public String cancel() {
		return "redirect:/employeeList";
	}

	@RequestMapping(value = "/employeeList", method = RequestMethod.GET)
	public String list(Model model) {
		
		model.addAttribute("entries", configuration.getAllEmployees());
		return "employeeList";
	}

	@RequestMapping(value = "/{email}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String email) {

		configuration.deleteUser(configuration.getUserByEmail(email));
		return "redirect:/employeeList";
	}

	@RequestMapping(value = "/projectList", method = RequestMethod.GET)
	public String projectlist(Model model) {
		model.addAttribute("projects", configuration.getAllProjects());
		return "projectList";
	}

	@RequestMapping(value = "/addProject", method = RequestMethod.GET)
	public String addNewProject(Model model) {
		model.addAttribute("project", new ProjectViewModel());
		return "addProject";
	}

	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	public String addNewProject(ProjectViewModel newProjectViewModel) {
		configuration.addProject(projectConverter.toProjectEntry(newProjectViewModel));
		return "redirect:/projectList";
	}

	@RequestMapping(value = "/{id}/editProject", method = RequestMethod.GET)
	public String editProject(@PathVariable Integer id, Model model) {
		model.addAttribute("project", configuration.getProjectById(id));
		return "editProject";
	}

	@RequestMapping(value = "/{id}/editProject", method = RequestMethod.POST)
	public String editProject(@PathVariable Integer id, ProjectViewModel updatedProjectViewModel) {
		configuration.updateProject(id, projectConverter.toProjectEntry(updatedProjectViewModel));
		return "redirect:/projectList";
	}

	@RequestMapping(value = "/{id}/addTask", method = RequestMethod.GET)
	public String addNewTask(Model model) {
		model.addAttribute("task", new TaskViewModel());
		return "addTask";
	}

	@RequestMapping(value = "/{id}/addTask", method = RequestMethod.POST)
	public String addNewTask(@PathVariable Integer id, TaskViewModel newTaskViewModel) {
		newTaskViewModel.setId(0);
		configuration.addTask(id, projectConverter.toTaskEntry(newTaskViewModel));
		return "redirect:/projectList";
	}

	@RequestMapping(value = "/{id}/editTask", method = RequestMethod.GET)
	public String editTask(@PathVariable Integer id, Model model) {
		model.addAttribute("task", configuration.getTaskById(id));
		return "editTask";
	}

	@RequestMapping(value = "/{id}/editTask", method = RequestMethod.POST)
	public String editTask(@PathVariable Integer id, TaskViewModel updatedTaskViewModel) {
		configuration.updateTask(id, projectConverter.toTaskEntry(updatedTaskViewModel));
		return "redirect:/projectList";
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public String cancelProject() {
		return "redirect:/projectList";
	}

	@RequestMapping(value = "/{id}/cancel", method = RequestMethod.GET)
	public String cancelTask() {
		return "redirect:/projectList";
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
}
