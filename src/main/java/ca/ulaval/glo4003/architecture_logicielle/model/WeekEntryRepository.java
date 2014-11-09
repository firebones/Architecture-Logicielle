package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.ArrayList;

public interface WeekEntryRepository {
	public ArrayList<String> getKilometers();
	public ArrayList<String> getEmployeeExpenses();
	public ArrayList<String> getExpenses();
	public ArrayList<String> getHours();
	public ArrayList<WeekEntry> getAllWeekEntries();
	public WeekEntry getWeekEntryByEmailAndWeek(String email, String weekNumber);
}
