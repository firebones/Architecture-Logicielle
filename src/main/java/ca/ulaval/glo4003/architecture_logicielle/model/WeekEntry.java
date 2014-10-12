package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeekEntry {

	private String weekNumber;
	private String email;
	private String startDate;
	private String endDate;
	private List<Integer> kilometersEntries = new ArrayList<Integer>(7);
	private List<String> expensesEntries = new ArrayList<String>(7);
	
	public String getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(String weekNumber) {
		this.weekNumber = weekNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<Integer> getKilometersEntries() {
		return kilometersEntries;
	}
	public void setKilometersEntries(List<Integer> kilometersEntries) {
		this.kilometersEntries = kilometersEntries;
	}
	public List<String> getExpensesEntries() {
		return expensesEntries;
	}
	public void setExpensesEntries(List<String> expensesEntries) {
		this.expensesEntries = expensesEntries;
	}
}
