package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AssignedHoursTest
{
	private AssignedHours assignedHours;

	@Before
	public void setUp() throws Exception
	{
		assignedHours = new AssignedHours();
		List<String> hours = new LinkedList<String>(); 
		hours.add("3");
		hours.add("5");
		hours.add("7");
		hours.add("4");
		hours.add("8");
		hours.add("2");
		hours.add("1");
		assignedHours.hours = hours;
	}

	@Test
	public void testGetHours()
	{
		List<String> hours = new LinkedList<>(); 
		hours.add("3");
		hours.add("5");
		hours.add("7");
		hours.add("4");
		hours.add("8");
		hours.add("2");
		hours.add("1");
		assertEquals(assignedHours.getHours(), hours);
	}

	@Test
	public void testSetHours()
	{
		List<String> hours = new LinkedList<>(); 
		hours.add("5");
		hours.add("3");
		hours.add("2");
		hours.add("8");
		hours.add("8");
		hours.add("2");
		hours.add("1");
		assignedHours.setHours(hours);
		assertEquals(assignedHours.getHours(), hours);
	}

	@Test
	public void testGetIsNullFalse()
	{
		assertFalse(assignedHours.getIsNull());
	}
	
	@Test
	public void testGetIsNullTrue()
	{
		assignedHours = new AssignedHours();
		assertTrue(assignedHours.getIsNull());
	}

}
