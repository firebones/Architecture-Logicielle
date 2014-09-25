package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;

public class UserEntry {
	private String name;
	private String email;
	private List<TaskEntry> tasks;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String setEmail() {
		return email;
	}
	
	public List<TaskEntry> getTasks() {
		return tasks;
	}
	
	public void assignTask(TaskEntry task) {
		if (tasks.contains(task) != true) {
			tasks.add(task);
		}
	}
	
	public void removeTask(TaskEntry task) {
		if (tasks.contains(task) == true) {
			tasks.remove(task);
		}
	}
	
	public void updateTasks(List<TaskEntry> tasks) {
		this.tasks = tasks;
	}
}
