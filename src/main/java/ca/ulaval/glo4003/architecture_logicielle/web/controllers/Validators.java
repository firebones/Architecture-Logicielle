package ca.ulaval.glo4003.architecture_logicielle.web.controllers;

import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.util.Configuration;

public class Validators {

	public String ValidateAssignedKilometers(List<String> kilometers){
		
		int kilometerMin = Integer.parseInt(Configuration.getConfig("MIN_KM_DAY"));
		int kilometerMax = Integer.parseInt(Configuration.getConfig("MAX_KM_DAY"));
		
		for (String km : kilometers)
		{
			if(!tryParseInt(km) || Integer.parseInt(km) < kilometerMin || Integer.parseInt(km) > kilometerMax)
			{
				return "Les kilom�tres entr�s doivent �tre des valeurs num�riques entre " +  kilometerMin + " et " + kilometerMax;
			}
		}
		
		return "";
	}
	
	public String ValidateAssignedHours(List<String> hours){
		
		Double hourMin = Double.parseDouble(Configuration.getConfig("MIN_HRS_DAY"));
		Double hourMax = Double.parseDouble(Configuration.getConfig("MAX_HRS_DAY"));
		
		for (String hour : hours)
		{
			if(!tryParseDouble(hour) || Double.parseDouble(hour) < hourMin || Double.parseDouble(hour) > hourMax)
			{
				return "Les heures entr�es doivent �tre des valeurs num�riques entre " +  hourMin + " et " + hourMax;
			}
		}
		
		return "";
	}
	
	public String ValidateAssignedExpenses(List<String> hours){
		
		Double expenseMin = Double.parseDouble(Configuration.getConfig("MIN_EXPENSE_DAY"));
		Double expenseMax = Double.parseDouble(Configuration.getConfig("MAX_EXPENSE_DAY"));
		
		for (String hour : hours)
		{
			if(!tryParseDouble(hour) || Double.parseDouble(hour) < expenseMin || Double.parseDouble(hour) > expenseMax)
			{
				return "Les d�penses entr�es doivent �tre des valeurs num�riques entre " +  expenseMin + " et " + expenseMax;
			}
		}
		
		return "";
	}	
	
	public String ValidateTasksRate(List<String> rates){
		for (String rate : rates)
		{
			if(!tryParseDouble(rate))
			{
				return "Les taux horaires doivent �tre des nombres";
			}
		}
		
		return "";
	}
	
	boolean tryParseInt(String value)  
	{  
	     try  
	     {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch(NumberFormatException nfe)  
	      {  
	          return false;  
	      }  
	}
	
	boolean tryParseDouble(String value)  
	{  
	     try  
	     {  
	         Double.parseDouble(value);  
	         return true;  
	      } catch(NumberFormatException nfe)  
	      {  
	          return false;  
	      }  
	}
	
}
