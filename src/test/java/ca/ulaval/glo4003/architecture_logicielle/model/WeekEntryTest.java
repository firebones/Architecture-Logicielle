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
		Integer weekNumber = 41;
		Integer yearNumber = 2014;
		String email = "pape@gmail.com";
		StateWeekEntry state = StateWeekEntry.INPROGRESS;
		List<Integer> kilometers = new ArrayList<Integer>();
		kilometers.add(0);
		kilometers.add(10);
		kilometers.add(10);
		kilometers.add(10);
		kilometers.add(10);
		kilometers.add(10);
		kilometers.add(0);	
		List<Double> expenses = new ArrayList<Double>();
		expenses.add(0.0);
		expenses.add(100.0);
		expenses.add(200.0);
		expenses.add(100.0);
		expenses.add(200.0);
		expenses.add(100.0);
		expenses.add(0.0);
		List<String> expensesEntries = new ArrayList<String>();
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
		oneweek.setYearNumber(yearNumber);
		oneweek.setEmail(email);
		oneweek.setState(state);
		oneweek.setKilometersEntries(kilometers);
		oneweek.setEmployeeExpensesEntries(expenses);
		oneweek.setExpensesEntries(expensesEntries);
		oneweek.setHoursEntries(hours);

	}

	@Test
	public void testGetWeekNumber()
	{
		Integer number = oneweek.getWeekNumber();
		
		assertEquals(number.toString(), "41");

	}

	@Test
	public void testSetWeekNumber()
	{
		oneweek.setWeekNumber(1);
		
		assertEquals(oneweek.getWeekNumber().toString(), "1");
	}

	@Test
	public void testGetYearNumber()
	{
		Integer year = oneweek.getYearNumber();
		
		assertEquals(year.toString(), "2014");
	}

	@Test
	public void testSetYearNumber()
	{
		oneweek.setYearNumber(2015);
		
		assertEquals(oneweek.getYearNumber().toString(), "2015");
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
	public void testGetState()
	{
		StateWeekEntry state = oneweek.getState();
		
		assertEquals(state.getStateWeekEntry(), "inProgress");
	}

	@Test
	public void testSetState()
	{
		oneweek.setState(StateWeekEntry.APPROVED);
		
		assertEquals(oneweek.getState().getStateWeekEntry(), "Approved");
	}

	@Test
	public void testCanSubmit()
	{
		Boolean test = oneweek.canSubmit();
		
		assertEquals(test, true);
	}
	
	@Test
	public void testCanNotSubmit()
	{
		oneweek.setState(StateWeekEntry.APPROVED);
		Boolean test = oneweek.canSubmit();
		
		assertEquals(test, false);
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

	@Test
	public void testGetDaysOfWeek()
	{
		List<String> list = oneweek.getDaysOfWeek();
		
		assertEquals(list.size(), 7);
	}

	@Test
	public void testGetDatesOfWeek()
	{
		List<String> list = oneweek.getDatesOfWeek();
		
		assertEquals(list.size(), 7);
	}

	@Test
	public void testGetStartDate()
	{
		String test = oneweek.getStartDate();
		
		assertEquals(test.isEmpty(), false);
	}

	@Test
	public void testGetEndDate()
	{
		String test = oneweek.getEndDate();
		
		assertEquals(test.isEmpty(), false);
	}

}
