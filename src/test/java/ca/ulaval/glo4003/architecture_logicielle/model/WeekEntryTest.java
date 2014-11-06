package ca.ulaval.glo4003.architecture_logicielle.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class WeekEntryTest
{
	private WeekEntry oneweek;

	@Before
	public void setUp() throws Exception
	{
		String weekNumber = "41";
		String email = "pape@gmail.com";
		String startDate = "Lundi 27-10-2014";
		String endDate = "Dimance 2-11-2014";
		List<Integer> kilometers = new ArrayList<Integer>(7);
		kilometers.add(0);
		kilometers.add(10);
		kilometers.add(10);
		kilometers.add(10);
		kilometers.add(10);
		kilometers.add(10);
		kilometers.add(0);	
		List<Double> expenses = new ArrayList<Double>(7);
		expenses.add(0.0);
		expenses.add(100.0);
		expenses.add(200.0);
		expenses.add(100.0);
		expenses.add(200.0);
		expenses.add(100.0);
		expenses.add(0.0);
		List<String> expensesEntries = new ArrayList<String>(7);
		expensesEntries.add("");
		expensesEntries.add("tac");
		expensesEntries.add("toc");
		expensesEntries.add("tuc");
		expensesEntries.add("tec");
		expensesEntries.add("tic");
		expensesEntries.add("");
		List<Double> hours = new ArrayList<Double>(7);
		hours.add(0.0);
		hours.add(8.0);
		hours.add(8.0);
		hours.add(7.0);
		hours.add(8.0);
		hours.add(8.0);
		hours.add(0.0);
		
		oneweek = new WeekEntry();
		oneweek.setWeekNumber(weekNumber);
		oneweek.setEmail(email);
		oneweek.setStartDate(startDate);
		oneweek.setEndDate(endDate);
		oneweek.setKilometersEntries(kilometers);
		oneweek.setEmployeeExpensesEntries(expenses);
		oneweek.setExpensesEntries(expensesEntries);
		oneweek.setHoursEntries(hours);
	}

	@Test
	public void testGetWeekNumber()
	{
		String number = oneweek.getWeekNumber();
		
		assertEquals(number, "41");
	}

	@Test
	public void testSetWeekNumber()
	{
		oneweek.setWeekNumber("1");
		
		assertEquals(oneweek.getWeekNumber(), "1");
	}

	@Test
	public void testGetEmail()
	{
		String email = oneweek.getEmail();
		
		assertEquals(email, "pape@gmail.com");
	}

	@Test
	public void testSetEmail()
	{
		oneweek.setEmail("patrick@gmail.com");
		
		assertEquals(oneweek.getEmail(), "patrick@gmail.com");
	}

	@Test
	public void testGetStartDate()
	{
		String start = oneweek.getStartDate();
		
		assertEquals(start, "Lundi 27-10-2014");
	}

	@Test
	public void testSetStartDate()
	{
		oneweek.setStartDate("Lundi 3-11-2014");
		
		assertEquals(oneweek.getStartDate(), "Lundi 3-11-2014");
	}

	@Test
	public void testGetEndDate()
	{
		String end = oneweek.getEndDate();
		
		assertEquals(end, "Dimance 2-11-2014");
	}

	@Test
	public void testSetEndDate()
	{
		oneweek.setEndDate("Dimance 9-11-2014");
		
		assertEquals(oneweek.getEndDate(), "Dimance 9-11-2014");
	}

	@Test
	public void testGetKilometersEntries()
	{
		List<Integer> kilometerstest = oneweek.getKilometersEntries();
		
		assertEquals(kilometerstest.isEmpty(), false);
	}

	@Test
	public void testSetKilometersEntries()
	{
		List<Integer> kilometerstest = new ArrayList<Integer>(7);
		kilometerstest.add(0);
		kilometerstest.add(20);
		kilometerstest.add(60);
		kilometerstest.add(100);
		kilometerstest.add(5);
		kilometerstest.add(10);
		kilometerstest.add(30);
		
		oneweek.setKilometersEntries(kilometerstest);
		
		assertEquals(oneweek.getKilometersEntries().contains(100), true);
		assertEquals(oneweek.getKilometersEntries().contains(300), false);
	}

	@Test
	public void testGetEmployeeExpensesEntries()
	{
		List<Double> expenses = oneweek.getEmployeeExpensesEntries();
		
		assertEquals(expenses.isEmpty(), false);
	}

	@Test
	public void testSetEmployeeExpensesEntries()
	{
		List<Double> expensestest = new ArrayList<Double>(7);
		expensestest.add(0.0);
		expensestest.add(800.0);
		expensestest.add(400.0);
		expensestest.add(800.0);
		expensestest.add(1000.0);
		expensestest.add(4500.0);
		expensestest.add(10.0);
		
		oneweek.setEmployeeExpensesEntries(expensestest);
		
		assertEquals(oneweek.getEmployeeExpensesEntries().contains(4500.0), true);
		assertEquals(oneweek.getEmployeeExpensesEntries().contains(100.0), false);
	}

	@Test
	public void testGetExpensesEntries()
	{
		List<String> expensesEntries = oneweek.getExpensesEntries();
		
		assertEquals(expensesEntries.isEmpty(), false);
	}

	@Test
	public void testSetExpensesEntries()
	{
		List<String> expensesEntriestest = new ArrayList<String>(7);
		expensesEntriestest.add("");
		expensesEntriestest.add("test1");
		expensesEntriestest.add("test2");
		expensesEntriestest.add("test3");
		expensesEntriestest.add("test4");
		expensesEntriestest.add("test5");
		expensesEntriestest.add("test6");
		
		oneweek.setExpensesEntries(expensesEntriestest);
		
		assertEquals(oneweek.getExpensesEntries().contains("test5"), true);
		assertEquals(oneweek.getExpensesEntries().contains("test7"), false);
	}

	@Test
	public void testGetHoursEntries()
	{
		List<Double> hours = oneweek.getHoursEntries();
		
		assertEquals(hours.isEmpty(), false);
	}

	@Test
	public void testSetHoursEntries()
	{
		List<Double> hourstest = new ArrayList<Double>(7);
		hourstest.add(0.0);
		hourstest.add(9.0);
		hourstest.add(1.0);
		hourstest.add(7.0);
		hourstest.add(8.0);
		hourstest.add(3.0);
		hourstest.add(4.0);
		
		oneweek.setHoursEntries(hourstest);
		
		assertEquals(oneweek.getHoursEntries().contains(9.0), true);
		assertEquals(oneweek.getHoursEntries().contains(10.0), false);
	}

}
