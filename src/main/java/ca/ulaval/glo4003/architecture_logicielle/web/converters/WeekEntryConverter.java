package ca.ulaval.glo4003.architecture_logicielle.web.converters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.model.StateWeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedExpenses;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedHours;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.AssignedKilometers;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EntryViewModel;

public class WeekEntryConverter {

	public List<Integer> convertStringsToIntegers(List<String> valueListe)
	{
		List<Integer> list =  new ArrayList<Integer>();
		
		for (String value : valueListe)
		{
			list.add(Integer.parseInt(value));
		}
		
		return list;
	}
	
	public List<Double> convertStringsToDoubles(List<String> valueListe)
	{
		List<Double> list =  new ArrayList<Double>();
		
		for (String value : valueListe)
		{
			list.add(Double.parseDouble(value));
		}
		
		return list;
	}
	
	public List<String> convertIntegerToStringList(List<Integer> valueList)
	{
		List<String> list =  new ArrayList<String>();
		
		for (Integer integer : valueList)
		{
			list.add(integer.toString());
		}
		
		return list;
	}
	
	public List<String> convertDoublesToStringList(List<Double> valueList)
	{
		List<String> list =  new ArrayList<String>();
		
		for (Double value : valueList)
		{
			list.add(value.toString());
		}
		
		return list;
	}
	
	
	public List<Double> convertToDoubleList(List<String> valuelist)
	{
		List<Double> list =  new ArrayList<Double>();
		
		for (String value : valuelist)
		{
			list.add(Double.parseDouble(value));
		}
		
		return list;
	}
	
	public EntryViewModel toHoursEntryViewModel(WeekEntry weekEntry, AssignedHours hours){
		
		EntryViewModel entryViewModel = new EntryViewModel();
		
		List<String> entries;
		if (!hours.getIsNull())
			entries = hours.getHours();
		else
			entries = convertDoublesToStringList(weekEntry.getHoursEntries());
		
		entryViewModel.setEntries(entries);
		
		return toEntryViewModel(entryViewModel, weekEntry);
	}
	
	public EntryViewModel toExpensesEntryViewModel(WeekEntry weekEntry, AssignedExpenses expenses){
		
		EntryViewModel entryViewModel = new EntryViewModel();
		
		List<String> entries;
		if (!expenses.getIsNull())
			entries = expenses.getExpenses();
		else
			entries = convertDoublesToStringList(weekEntry.getEmployeeExpensesEntries());
		entryViewModel.setEntries(entries);
		
		return toEntryViewModel(entryViewModel, weekEntry);
	}
	
	public EntryViewModel toKilometersEntryViewModel(WeekEntry weekEntry, AssignedKilometers kilometers){
		
		EntryViewModel entryViewModel = new EntryViewModel();
		
		List<String> entries;
		if (!kilometers.getIsNull())
			entries = kilometers.getKilometers();
		else
			entries = convertIntegerToStringList(weekEntry.getKilometersEntries());
		
		entryViewModel.setEntries(entries);
		
		return toEntryViewModel(entryViewModel, weekEntry);
	}
	
	private EntryViewModel toEntryViewModel(EntryViewModel weekViewModel, WeekEntry weekEntry)
	{
		Boolean isReadOnly = false;
		if (weekEntry.getState() != StateWeekEntry.INPROGRESS)
			isReadOnly = true;
		weekViewModel.setIsReadOnly(isReadOnly);
		weekViewModel.setDaysOfWeek(weekEntry.getDaysOfWeek());
		weekViewModel.setDatesOfWeek(weekEntry.getDatesOfWeek());
		
		return weekViewModel;
	}
}
