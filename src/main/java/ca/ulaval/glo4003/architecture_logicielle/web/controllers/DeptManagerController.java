package ca.ulaval.glo4003.architecture_logicielle.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.model.ModelFacade;

import ca.ulaval.glo4003.architecture_logicielle.web.converters.ProjectEntryConverter;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedTasks;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.ProjectViewModel;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.TaskViewModel;

@Controller
public class DeptManagerController {

	private ModelFacade facade = new ModelFacade();

	private ProjectEntryConverter projectConverter = new ProjectEntryConverter();


	@RequestMapping(value = "/{email}/assignTasks", method = RequestMethod.GET)
	public String buildAssignTasksView(@PathVariable String email, Model model) {
		String employeeId = facade.getUser(email).getName() + " - " + facade.getUser(email).getEmail();
		model.addAttribute("assignTasksView", projectConverter
				.toProjectViewModels(facade.getAllProjects(), facade.getTaskEmployee(email), employeeId));
		return "assignTasks";
	}

	@ModelAttribute("selectedTasks")
	public AssignedTasks assignedTasks() {
		return new AssignedTasks();
	}

	@RequestMapping(value = "/{email}/assignTasks", method = RequestMethod.POST)
	public String getAssignedTasks(@PathVariable String email, @ModelAttribute("assignedTasks") AssignedTasks assignTasks) {
		facade.assignedTaskToEmployee(email, assignTasks.getTasks());
		return "redirect:/employeeList";
	}

	@RequestMapping(value = "/{email}/assignTasksCancel", method = RequestMethod.GET)
	public String cancel() {
		return "redirect:/employeeList";
	}

	@RequestMapping(value = "/employeeList", method = RequestMethod.GET)
	public String list(Model model) {
		
		model.addAttribute("entries", facade.getAllEmployees());
		return "employeeList";
	}

	@RequestMapping(value = "/{email}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String email) {

		facade.deleteUser(facade.getUser(email));
		return "redirect:/employeeList";
	}

	@RequestMapping(value = "/projectList", method = RequestMethod.GET)
	public String projectlist(Model model) {
		model.addAttribute("projects", facade.getAllProjects());
		return "projectList";
	}

	@RequestMapping(value = "/addProject", method = RequestMethod.GET)
	public String addNewProject(Model model) {
		model.addAttribute("project", new ProjectViewModel());
		return "addProject";
	}

	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	public String addNewProject(ProjectViewModel newProjectViewModel) {
		facade.addNewProject(projectConverter.toProjectEntry(newProjectViewModel));
		return "redirect:/projectList";
	}

	@RequestMapping(value = "/{id}/editProject", method = RequestMethod.GET)
	public String editProject(@PathVariable Integer id, Model model) {
		model.addAttribute("project", facade.getProject(id));
		return "editProject";
	}

	@RequestMapping(value = "/{id}/editProject", method = RequestMethod.POST)
	public String editProject(@PathVariable Integer id, ProjectViewModel updatedProjectViewModel) {
		facade.updateProject(id, projectConverter.toProjectEntry(updatedProjectViewModel));
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
		facade.addTask(id, projectConverter.toTaskEntry(newTaskViewModel));
		return "redirect:/projectList";
	}

	@RequestMapping(value = "/{id}/editTask", method = RequestMethod.GET)
	public String editTask(@PathVariable Integer id, Model model) {
		model.addAttribute("task", facade.getTask(id));
		return "editTask";
	}

	@RequestMapping(value = "/{id}/editTask", method = RequestMethod.POST)
	public String editTask(@PathVariable Integer id, TaskViewModel updatedTaskViewModel) {
		facade.updateTask(id, projectConverter.toTaskEntry(updatedTaskViewModel));
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
