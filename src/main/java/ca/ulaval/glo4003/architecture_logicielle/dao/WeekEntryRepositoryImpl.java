package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.model.PeriodPayBuilder;
import ca.ulaval.glo4003.architecture_logicielle.model.PeriodPayBuilderImpl;
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
	
	private WeekEntry createPeriodPayEntry(ArrayList<String> tabweek){
	
		PeriodPayBuilder periodPay = new PeriodPayBuilderImpl();
		periodPay.setInformation(tabweek.get(0), tabweek.get(1), tabweek.get(2), tabweek.get(3));
		
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

	
}
