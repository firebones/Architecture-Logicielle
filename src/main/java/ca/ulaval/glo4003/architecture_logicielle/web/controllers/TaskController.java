package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.architecture_logicielle.appConfig.AppConfiguration;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;

@Controller
public class TaskController {
	
	private AppConfiguration configuration = new AppConfiguration();

	@RequestMapping(value = "/tasksList", method = RequestMethod.GET)
	public String projectlist(Model model) {
		String email = configuration.getCurrentUser();
		model.addAttribute("tasks", configuration.getTasksEmployee(email));
		return "tasksList";
	}
	
	@RequestMapping(value = "/submitTaskHours", method = RequestMethod.POST)
	public String getValuesOfWeek( @ModelAttribute("tasks") List<TaskEntry> tasks) {
		String email = configuration.getCurrentUser();
		UserEntry user = configuration.getUserByEmail(email);
		configuration.updateTasksEmployee(tasks, user);

		return "redirect:/tasksList";
	}

}
