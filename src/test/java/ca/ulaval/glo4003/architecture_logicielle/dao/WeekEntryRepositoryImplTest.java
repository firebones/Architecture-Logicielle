package ca.ulaval.glo4003.architecture_logicielle.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntry;

public class WeekEntryRepositoryImplTest
{
	ArrayList<WeekEntry> weeks = new ArrayList<WeekEntry>();
	WeekEntryRepositoryImpl repository; 
	
	@Before
	public void setUp() throws Exception
	{
		repository = new WeekEntryRepositoryImpl();
	}

	@Test
	public void testGetAllWeekEntries()
	{
		weeks = repository.getAllWeekEntries();
		
		assertEquals(weeks.isEmpty(), false);
	}

	@Test
	public void testGetWeekEntryByEmailAndWeek()
	{
		WeekEntry week = repository.getWeekEntryByEmailAndWeek("joe@gmail.com", "41");
		
		assertEquals(week.getStartDate(), "2014-10-05");
		assertEquals(week.getEndDate(), "2014-10-11");
		assertEquals(week.getEmployeeExpensesEntries().size(), 7);
		assertEquals(week.getKilometersEntries().size(), 7);
	}

	@Test
	public void testGetWeekEntryByNotExistingEmailAndWeek()
	{
		WeekEntry week = repository.getWeekEntryByEmailAndWeek("pape@gmail.com", "999");
		
		assertEquals(week, null);
	}

	@Test
	public void testGetExpenses()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetKilometers()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmployeeExpenses()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetHours()
	{
		fail("Not yet implemented");
	}

}
