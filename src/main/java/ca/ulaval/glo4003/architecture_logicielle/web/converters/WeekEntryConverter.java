package ca.ulaval.glo4003.architecture_logicielle.web.converters;

import java.util.ArrayList;
import java.util.List;

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
}
