package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.List;

public class PeriodPayBuilderImpl implements PeriodPayBuilder
{

	private WeekEntry weekEntry;
	
	public PeriodPayBuilderImpl(){
		this.weekEntry = new WeekEntry();
	}
	
	@Override
	public void setInformation(String email, String weekNumber, String startDate, String endDate)
	{
		this.weekEntry.setEmail(email);
		this.weekEntry.setWeekNumber(weekNumber);
		this.weekEntry.setStartDate(startDate);
		this.weekEntry.setEndDate(endDate);

	}

	@Override
	public void setEmployeeKilometers(List<Integer> kilometersEntries)
	{
		this.weekEntry.setKilometersEntries(kilometersEntries);

	}

	@Override
	public void setEmployeeExpenses(List<Double> employeeExpensesEntries)
	{
		this.weekEntry.setEmployeeExpensesEntries(employeeExpensesEntries);

	}

	@Override
	public void setEmployeeHours(List<Double> hoursEntries)
	{
		this.weekEntry.setHoursEntries(hoursEntries);

	}

	@Override
	public WeekEntry getPeriodPayEntry()
	{
		return this.weekEntry;
	}

}
