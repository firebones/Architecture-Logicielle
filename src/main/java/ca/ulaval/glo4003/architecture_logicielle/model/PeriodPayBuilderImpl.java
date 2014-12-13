package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.List;

public class PeriodPayBuilderImpl implements PeriodPayBuilder
{

	private WeekEntry weekEntry;
	
	public PeriodPayBuilderImpl(){
		this.weekEntry = new WeekEntry();
	}
	
	@Override
	public void setInformation(String email, Integer weekNumber, Integer yearNumber, StateWeekEntry state)
	{
		this.weekEntry.setEmail(email);
		this.weekEntry.setWeekNumber(weekNumber);
		this.weekEntry.setYearNumber(yearNumber);
		this.weekEntry.setState(state);

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
	
	@Override
	public boolean equals(Object obj)
	{
		if ((!obj.getClass().equals(getClass())) || (obj == null)) {
			return false;
		}
		PeriodPayBuilderImpl ppbl = (PeriodPayBuilderImpl) obj;
		return weekEntry.equals(ppbl.weekEntry);
	}

}
