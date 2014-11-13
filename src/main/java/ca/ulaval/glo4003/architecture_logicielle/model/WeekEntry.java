package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.ArrayList;
import java.util.List;

public class WeekEntry {

	private String weekNumber;
	private String email;
	private StateWeekEntry state;
	private List<Integer> kilometersEntries = new ArrayList<Integer>();
	private List<Double> employeeExpensesEntries = new ArrayList<Double>();
	private List<String> expensesEntries = new ArrayList<String>();
	private List<Double> hoursEntries = new ArrayList<Double>();
	
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
	public StateWeekEntry getState() {
		return state;
	}
	public void setState(StateWeekEntry state) {
		this.state = state;
	}

	public List<Integer> getKilometersEntries() {
		return kilometersEntries;
	}
	public void setKilometersEntries(List<Integer> kilometersEntries) {
		this.kilometersEntries = kilometersEntries;
	}
	public List<Double> getEmployeeExpensesEntries(){
		return employeeExpensesEntries;
	}
	public void setEmployeeExpensesEntries(List<Double> employeeExpensesEntries){
		this.employeeExpensesEntries = employeeExpensesEntries;
	}
	public List<String> getExpensesEntries() {
		return expensesEntries;
	}
	public void setExpensesEntries(List<String> expensesEntries) {
		this.expensesEntries = expensesEntries;
	}
	public List<Double> getHoursEntries(){
		return hoursEntries;
	}
	public void setHoursEntries(List<Double> hoursEntries){
		this.hoursEntries = hoursEntries;
	}
	
}
