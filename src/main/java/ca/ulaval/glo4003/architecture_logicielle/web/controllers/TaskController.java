package ca.ulaval.glo4003.architecture_logicielle.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.appConfig.AppConfiguration;
import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;

@Controller
public class TaskController {
	
	private AppConfiguration configuration = new AppConfiguration();

	@RequestMapping(value = "/tasksList", method = RequestMethod.GET)
	public String tasklist(Model model) {
		String email = configuration.getCurrentUser();
		model.addAttribute("tasks", configuration.getTasksEmployee(email));
		return "tasksList";
	}
	
	@RequestMapping(value = "/{id}/submitTaskHours", method = RequestMethod.GET)
	public String getEnterTaskHours(@PathVariable Integer id, Model model) {
		
		model.addAttribute("task", configuration.getTaskById(id));

		return "submitTaskHours";
	}
	
	@RequestMapping(value = "/{id}/submitTaskHours", method = RequestMethod.POST)
	public String getHoursOfTasks(@PathVariable Integer id, Model model, @ModelAttribute("task") TaskEntry task) {

		
		//configuration.updateTask(id, task);

		return "redirect:/tasksList";
	}

}
