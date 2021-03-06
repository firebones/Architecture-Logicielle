package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.List;

public interface PeriodPayBuilder
{
	public void setInformation(String email, Integer weekNumber, Integer yearNumber, StateWeekEntry state);
	public void setEmployeeKilometers(List<Integer> kilometersEntries);
	public void setEmployeeExpenses(List<Double> employeeExpensesEntries);
	public void setEmployeeHours(List<Double> hoursEntries);
	
	public WeekEntry getPeriodPayEntry();
}
