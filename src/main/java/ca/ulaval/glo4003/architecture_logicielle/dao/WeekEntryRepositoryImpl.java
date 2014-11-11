package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.PeriodPayBuilder;
import ca.ulaval.glo4003.architecture_logicielle.model.PeriodPayBuilderImpl;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
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
			
			WeekEntry periodPay = createPeriodPayEntry(tabweek);
			
			weekEntryList.add(periodPay);
		}

		
		return weekEntryList;
	}
	
	@Override
	public WeekEntry getWeekEntryByEmailAndWeek(String email, String weekNumber) {
		
		ArrayList<String> listElement = new ArrayList<String>();
		
		listElement = xmlWeekPersistance.getWeekEntryByEmailAndWeek(email, weekNumber);
		
		WeekEntry periodPay = createPeriodPayEntry(listElement);

		return periodPay;
	}
	
	@Override
	public void addEmployeePeriodPay(UserEntry user, WeekEntry periodPay)
	{
			ArrayList<String> userelement = getPeriodPayString(periodPay);
			xmlWeekPersistance.addPeriodPay(userelement);
	}
	
	
/*	@Override
	public ArrayList<String> getKilometers()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getEmployeeExpenses()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getExpenses()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getHours()
	{
		// TODO Auto-generated method stub
		return null;
	}
*/	
	private WeekEntry createPeriodPayEntry(ArrayList<String> tabweek){
	
		PeriodPayBuilder periodPay = new PeriodPayBuilderImpl();
		boolean isApproved = false;
		if(tabweek.get(4) == "true")
			isApproved = true;
		
		periodPay.setInformation(tabweek.get(0), tabweek.get(1), tabweek.get(2), tabweek.get(3), isApproved);
		
		List<Integer> listKilometer = new ArrayList<Integer>();
		int i=6;
		
		if(tabweek.get(5) == "listKilometer"){
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

	private ArrayList<String> getPeriodPayString(WeekEntry periodPay) {
		ArrayList<String> periodPayElement = new ArrayList<String>();
		
		periodPayElement.add(0, periodPay.getEmail());
		periodPayElement.add(1, periodPay.getWeekNumber());
		periodPayElement.add(2, periodPay.getStartDate());
		periodPayElement.add(3, periodPay.getEndDate());
		
		String isApproved = "false";
		if(periodPay.isApproved() == true)
			isApproved = "true";
		periodPayElement.add(4, isApproved);
		
		periodPayElement.add(5, "listKilometer");
		
		int j = 6;
		if (periodPay instanceof WeekEntry && periodPay.getKilometersEntries().size() > 0) {
			
			List<Integer> listKilometer = periodPay.getKilometersEntries();
			for (int i=0; i < periodPay.getKilometersEntries().size(); i++) {		
				
				periodPayElement.add(j, listKilometer.get(i).toString());
				j++;
			}
		}
		
		periodPayElement.add(j, "listExpenses");
		j++;
		
		if (periodPay instanceof WeekEntry && periodPay.getKilometersEntries().size() > 0) {
			List<Double> listExpenses = periodPay.getEmployeeExpensesEntries();
			for (int i=0; i < periodPay.getEmployeeExpensesEntries().size(); i++) {		
				
				periodPayElement.add(j, listExpenses.get(i).toString());
				j++;
			}
		}
		
		periodPayElement.add(j, "listHours");
		j++;
		
		if (periodPay instanceof WeekEntry && periodPay.getKilometersEntries().size() > 0) {
			List<Double> listHours = periodPay.getHoursEntries();
			for (int i=0; i < periodPay.getHoursEntries().size(); i++) {		
				
				periodPayElement.add(j, listHours.get(i).toString());
				j++;
			}
		}
		
		periodPayElement.add(j, "fin");
		
		return periodPayElement;
	}
}
