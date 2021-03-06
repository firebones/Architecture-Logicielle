package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.List;
import java.util.ArrayList;

import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;

public class ProjectEntry {
	private Integer id;
	private String name;
	private List<TaskEntry> tasks;
	
	public ProjectEntry() {
		tasks = new ArrayList<TaskEntry>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<TaskEntry> getTasks() {
		return tasks;
	}
	
	public void addTask(TaskEntry task) {
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
