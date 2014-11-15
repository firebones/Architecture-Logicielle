package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.model.PeriodPayBuilder;
import ca.ulaval.glo4003.architecture_logicielle.model.PeriodPayBuilderImpl;
import ca.ulaval.glo4003.architecture_logicielle.model.StateWeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntryRepository;

public class WeekEntryRepositoryImpl implements WeekEntryRepository
{
	XMLWeekPersistance xmlWeekPersistance;
	
	public WeekEntryRepositoryImpl(){
		xmlWeekPersistance = XMLWeekPersistance.getInstance();
	}
	
	@Override
	public ArrayList<WeekEntry> getAllWeekEntries() {
		ArrayList<WeekEntry> weekEntryList = new ArrayList<WeekEntry>();
		ArrayList<ArrayList<String>> weekList = new ArrayList<ArrayList<String>>();
		
		weekList = xmlWeekPersistance.getAllWeekEntries();
				
		for(ArrayList<String> tabweek: weekList){
			
			WeekEntry periodPay = createWeekEntry(tabweek);
			
			weekEntryList.add(periodPay);
		}

		return weekEntryList;
	}
	
	@Override
	public WeekEntry getWeekEntryByEmailAndWeek(String email, Integer weekNumber, Integer yearNumber) {
		
		ArrayList<String> listElement = new ArrayList<String>();
		
		listElement = xmlWeekPersistance.getWeekEntryByEmailAndWeek(email, weekNumber, yearNumber);
		
		WeekEntry periodPay = createWeekEntry(listElement);

		return periodPay;
	}
	
	@Override
	public ArrayList<WeekEntry> getWeekEntryByEmail(String email)
	{
		ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
		ArrayList<WeekEntry> listWeekEntry = new ArrayList<WeekEntry>();
		
		lists = xmlWeekPersistance.getWeekEntryByEmail(email);
		
		for(ArrayList<String> listElement: lists){
			WeekEntry weekEntry = createWeekEntry(listElement);
			listWeekEntry.add(weekEntry);
		}

		return listWeekEntry;
	}
	
	@Override
	public void addEmployeeWeekEntry(UserEntry user, WeekEntry weekEntry)
	{
			ArrayList<String> userelement = getweekEntryString(weekEntry);
			xmlWeekPersistance.addPeriodPay(userelement);
	}
	
	@Override
	public void deleteWeekEntry(WeekEntry weekEntry)
	{
//		if (getWeekEntryByEmail(weekEntry.getEmail()) != null){
			
//			xmlWeekPersistance.deleteAllweekEntry(weekEntry.getEmail());	
//		}
		
	}

	private WeekEntry createWeekEntry(ArrayList<String> tabweek){
	
		PeriodPayBuilder periodPay = new PeriodPayBuilderImpl();
		
		StateWeekEntry state = null;
		switch(tabweek.get(3)){
		case "Approved": state = StateWeekEntry.APPROVED; break;
		case "Refused": state = StateWeekEntry.REFUSED; break;
		case "Submitted": state = StateWeekEntry.SUBMITTED; break;
		case "inProgress": state = StateWeekEntry.INPROGRESS; break;
		}
		
		periodPay.setInformation(tabweek.get(0), Integer.parseInt(tabweek.get(1)), Integer.parseInt(tabweek.get(2)), state);
		
		List<Integer> listKilometer = new ArrayList<Integer>();
		int i=5;
		
		if(tabweek.get(4) == "listKilometer"){
			do{
				listKilometer.add(Integer.parseInt(tabweek.get(i)));
				i++;
			}while(tabweek.get(i) != "listExpenses");
		}
		
		periodPay.setEmployeeKilometers(listKilometer);

		
		List<Double> listExpenses = new ArrayList<Double>();
		if(tabweek.get(i) == "listExpenses"){
			i++;
			do{
				listExpenses.add(Double.parseDouble(tabweek.get(i)));
				i++;
			}while(tabweek.get(i) != "listHours");
		}
		
		periodPay.setEmployeeExpenses(listExpenses);
		
		List<Double> listHours = new ArrayList<Double>();
		if(tabweek.get(i) == "listHours"){
			i++;
			do{
				listHours.add(Double.parseDouble(tabweek.get(i)));
				i++;
			}while(tabweek.get(i) != "fin");
		}
		
		periodPay.setEmployeeHours(listHours);

		return periodPay.getPeriodPayEntry();
	}

	private ArrayList<String> getweekEntryString(WeekEntry periodPay) {
		ArrayList<String> weekEntryElement = new ArrayList<String>();
		
		weekEntryElement.add(0, periodPay.getEmail());
		weekEntryElement.add(1, periodPay.getWeekNumber().toString());
		weekEntryElement.add(2, periodPay.getYearNumber().toString());
		weekEntryElement.add(3, periodPay.getState().getStateWeekEntry());
/*		String isApproved = "false";
		if(periodPay.isApproved() == true)
			isApproved = "true";
		
		weekEntryElement.add(2, isApproved);
		
		String isSubmitted = "false";
		if(periodPay.isSubmitted() == true)
			isSubmitted = "true";
		weekEntryElement.add(3, isSubmitted);
		
		String inProgess = "true";
		if(periodPay.inProgess() == false)
			inProgess = "false";
		weekEntryElement.add(4, inProgess);*/
		
		weekEntryElement.add(4, "listKilometer");
		
		int j = 5;
		if (periodPay instanceof WeekEntry && periodPay.getKilometersEntries().size() > 0) {
			
			List<Integer> listKilometer = periodPay.getKilometersEntries();
			for (int i=0; i < periodPay.getKilometersEntries().size(); i++) {		
				
				weekEntryElement.add(j, listKilometer.get(i).toString());
				j++;
			}
		}
		
		weekEntryElement.add(j, "listExpenses");
		j++;
		
		if (periodPay instanceof WeekEntry && periodPay.getKilometersEntries().size() > 0) {
			List<Double> listExpenses = periodPay.getEmployeeExpensesEntries();
			for (int i=0; i < periodPay.getEmployeeExpensesEntries().size(); i++) {		
				
				weekEntryElement.add(j, listExpenses.get(i).toString());
				j++;
			}
		}
		
		weekEntryElement.add(j, "listHours");
		j++;
		
		if (periodPay instanceof WeekEntry && periodPay.getKilometersEntries().size() > 0) {
			List<Double> listHours = periodPay.getHoursEntries();
			for (int i=0; i < periodPay.getHoursEntries().size(); i++) {		
				
				weekEntryElement.add(j, listHours.get(i).toString());
				j++;
			}
		}
		
		weekEntryElement.add(j, "fin");
		
		return weekEntryElement;
	}

	
}
