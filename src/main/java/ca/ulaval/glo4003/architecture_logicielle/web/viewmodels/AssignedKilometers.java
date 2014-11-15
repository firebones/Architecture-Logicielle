package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import java.util.List;

import org.springframework.validation.Errors;

import ca.ulaval.glo4003.architecture_logicielle.util.Configuration;


public class AssignedKilometers {

	public List<String> kilometers;

	public List<String> getKilometers() {
		return kilometers;
	}

	public void setKilometers(List<String> kilometers) {
		this.kilometers = kilometers;
	}
	
//	public boolean validate() {
//		
//		int kilometerMin = Integer.parseInt(Configuration.getConfig("MIN_KM_DAY"));
//		int kilometerMax = Integer.parseInt(Configuration.getConfig("MAX_KM_DAY"));
//		
//		Boolean error = false;
//		
//		for (String km : kilometers)
//		{
//			if(tryParseInt(km) && (Integer.parseInt(km) < kilometerMin || Integer.parseInt(km) > kilometerMax))
//			{
//				errors.rejectValue("name", "field.role.required","Max value");
//				return false;
//			}
//		}
//	}
//	
//	boolean tryParseInt(String value)  
//	{  
//	     try  
//	     {  
//	         Integer.parseInt(value);  
//	         return true;  
//	      } catch(NumberFormatException nfe)  
//	      {  
//	          return false;  
//	      }  
//	}
}
