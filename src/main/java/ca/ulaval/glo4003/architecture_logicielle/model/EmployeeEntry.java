package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.List;
import java.util.ArrayList;

public class EmployeeEntry extends UserEntry {
	private List<TaskEntry> tasks = new ArrayList<TaskEntry>();
	private String departmentName;

	public EmployeeEntry() {
		this.role = RoleUser.EMPLOYEE;
	}

	public EmployeeEntry(RoleUser role) {
		if (role == RoleUser.EMPLOYEE || role == RoleUser.MANAGER) {
			this.role = role;
		}
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

	public List<String> getTasksString() {
		List<String> tasksString = new ArrayList<String>();
		for (TaskEntry entry : tasks) {
			tasksString.add(Integer.toString(entry.getId()));
		}
		return tasksString;
	}

	public String getDepartment() {
		return departmentName;
	}

	public void setDepartment(String departmentName) {
		this.departmentName = departmentName;
	}

	public void becomeManager() {
		role = RoleUser.MANAGER;
	}

	public void becomeEmployee() {
		role = RoleUser.EMPLOYEE;
	}
}
