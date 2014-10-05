package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.TaskViewModel;

public class ProjectViewModel {

	public Integer id;
	public String name;
	public List<TaskViewModel> tasks;
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public List<TaskViewModel> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskViewModel> tasks) {
		this.tasks = tasks;
	}
}
