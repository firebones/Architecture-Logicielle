package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import java.util.List;
import java.util.ArrayList;

public class EmployeeViewModel {

	public String email;
	public String name;
	public List<String> tasks = new ArrayList<String>();
	public Double rateHour; 
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getTasks() {
		return tasks;
	}
	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}
	public Double getRateHour() {
		return rateHour;
	}
	public void setRateHour(Double rateHour) {
		this.rateHour = rateHour;
	}
}
