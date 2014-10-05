package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import java.util.List;

public class EmployeeViewModel {

	public String email;
	public String lastName;
	public String firstName;
	public List<String> tasks;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirtName(String firstName) {
		this.firstName = firstName;
	}
	public List<String> getTasks() {
		return tasks;
	}
	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}
}
