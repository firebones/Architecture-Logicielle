package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.List;
import java.util.ArrayList;

public class EmployeeEntry extends UserEntry {
	private List<TaskEntry> tasks = new ArrayList<TaskEntry>();
	private Double rateHour = 20.0;
	private String companyName = "Company1";
	private String departmentName = "Department1";

	public EmployeeEntry() {
		this.role = RoleUser.EMPLOYEE;
	}

	public EmployeeEntry(RoleUser role) {
		if (role == RoleUser.EMPLOYEE || role == RoleUser.MANAGER) {
			this.role = role;
		}
	}

	public Double getRateHour() {
		return rateHour;
	}
	
	public void setRateHour(Double rateHour) {
		this.rateHour = rateHour;
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

	public String getCompany() {
		return companyName;
	}

	public void setCompany(String companyName) {
		this.companyName = companyName;
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
	
	public WeekEntry enterWorkHours(WeekEntry weekEntry, List<Double> listHours){
		weekEntry.setHoursEntries(listHours);
		return weekEntry;
	}
	
	public WeekEntry enterKilometers(WeekEntry weekEntry, List<Integer> kilometers){
		weekEntry.setKilometersEntries(kilometers);
		return weekEntry;
	}
	
	public WeekEntry enterExpenses(WeekEntry weekEntry, List<Double> expenses){
		weekEntry.setEmployeeExpensesEntries(expenses);
		return weekEntry;
	}
	
/*	public void assignTasks(List<TaskEntry> assignTasks){
		
		for (TaskEntry task : assignTasks) {
			this.assignTask(task);
		}
	}*/
}
