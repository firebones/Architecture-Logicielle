package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.ArrayList;

public interface WeekEntryRepository {
	public ArrayList<WeekEntry> getAllWeekEntries();
	public WeekEntry getWeekEntryByEmailAndWeek(String email, String weekNumber);
	public ArrayList<WeekEntry> getWeekEntryByEmail(String email);
	public void addEmployeeWeekEntry(UserEntry user, WeekEntry periodPay);
	public void deleteWeekEntry(WeekEntry weekEntry);
}
