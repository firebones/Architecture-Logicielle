package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.ProjectEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;
import ca.ulaval.glo4003.architecture_logicielle.web.converters.EmployeeEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.web.converters.ProjectEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.appConfig.AppConfiguration;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedTasks;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EmployeeViewModel;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.ProjectViewModel;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.TaskViewModel;

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
	
	@RequestMapping(value = "/projectList", method = RequestMethod.GET)
	public String projectlist(Model model) {
		model.addAttribute("projects", configuration.getAllProjects());
		return "projectList";
	}
	
	@RequestMapping(value = "/addProject", method = RequestMethod.GET)
	public String addNewProject(Model model) {
	    model.addAttribute("project", new ProjectEntry());
	    return "addProject";
	}
	
	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	public String addNewProject(ProjectViewModel newProjectViewModel){
		ProjectEntry newProject = projectConverter.toProjectEntry(newProjectViewModel);
		configuration.addProject(newProject);
		return "redirect:/projectList";
	}
	
	@RequestMapping(value = "/{id}/editProject", method = RequestMethod.GET)
	public String editProject(@PathVariable Integer id, Model model) {
		ProjectEntry project = configuration.getProjectById(id);
		model.addAttribute("project", project);
		return "editProject";
	}
	
	@RequestMapping(value = "/{id}/editProject", method = RequestMethod.POST)
	public String editProject(@PathVariable Integer id, ProjectViewModel updatedProjectViewModel){
		ProjectEntry updatedProject = projectConverter.toProjectEntry(updatedProjectViewModel);
		configuration.updateProject(id, updatedProject);
		return "redirect:/projectList";
	}
	
	@RequestMapping(value = "/{id}/addTask", method = RequestMethod.GET)
	public String addNewTask(Model model) {
	    model.addAttribute("task", new TaskEntry());
	    return "addTask";
	}
	
	@RequestMapping(value = "/{id}/addTask", method = RequestMethod.POST)
	public String addNewTask(@PathVariable Integer id, TaskViewModel newTaskViewModel){
		newTaskViewModel.setId(0);
		TaskEntry newTask = projectConverter.toTaskEntry(newTaskViewModel);
		configuration.addTask(id, newTask);
		return "redirect:/projectList";
	}
	
	@RequestMapping(value = "/{id}/editTask", method = RequestMethod.GET)
	public String editTask(@PathVariable Integer id, Model model) {
		TaskEntry task = configuration.getTaskById(id);
		model.addAttribute("task", task);
		return "editTask";
	}
	
	@RequestMapping(value = "/{id}/editTask", method = RequestMethod.POST)
	public String editTask(@PathVariable Integer id, TaskViewModel updatedTaskViewModel){
		TaskEntry updatedTask = projectConverter.toTaskEntry(updatedTaskViewModel);
		configuration.updateTask(id, updatedTask);
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
}
