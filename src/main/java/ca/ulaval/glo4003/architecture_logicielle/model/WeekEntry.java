package ca.ulaval.glo4003.architecture_logicielle.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeekEntry {

	private Integer weekNumber;
	private Integer yearNumber;
	private String email;
	private StateWeekEntry state;
	private List<Integer> kilometersEntries = new ArrayList<Integer>();
	private List<Double> employeeExpensesEntries = new ArrayList<Double>();
	private List<String> expensesEntries = new ArrayList<String>();
	private List<Double> hoursEntries = new ArrayList<Double>();
	
	public Integer getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(Integer weekNumber) {
		this.weekNumber = weekNumber;
	}
	public Integer getYearNumber() {
		return yearNumber;
	}
	public void setYearNumber(Integer yearNumber) {
		this.yearNumber = yearNumber;
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
	
	public Boolean canSubmit() {
		if (state == StateWeekEntry.INPROGRESS)
			return true;
		else
			return false;
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
	
	public List<String> getDaysOfWeek() {

		List<String> daysOfWeek = new ArrayList<String>();
		daysOfWeek.add("Dimanche");
		daysOfWeek.add("Lundi");
		daysOfWeek.add("Mardi");
		daysOfWeek.add("Mercredi");
		daysOfWeek.add("Jeudi");
		daysOfWeek.add("Vendredi");
		daysOfWeek.add("Samedi");

		return daysOfWeek;
	}
	
	public List<String> getDatesOfWeek() {

		List<String> dates = new ArrayList<String>(7);

		Date refDate = getFirstDateForWeekNumber();
		
		SimpleDateFormat format = new SimpleDateFormat("dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(refDate);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		for (int i = 0; i < 7; i++) {
			dates.add(format.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		return dates;
	}
	
	public String getStartDate(){
		Date refDate = getFirstDateForWeekNumber();
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(refDate.getTime());
	}
	
	public String getEndDate(){
		Date refDate = getLastDateForWeekNumber();
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(refDate.getTime());
	}

	private Date getFirstDateForWeekNumber() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);
		calendar.set(Calendar.YEAR, yearNumber);

		return calendar.getTime();
	}
	
	private Date getLastDateForWeekNumber() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);
		calendar.set(Calendar.YEAR, yearNumber);
		calendar.add(Calendar.DAY_OF_YEAR, 6);

		return calendar.getTime();
	}
}
