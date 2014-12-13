package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class AssignedKilometersTest
{
	private AssignedKilometers assignedKilometers;

	@Before
	public void setUp() throws Exception
	{
		assignedKilometers = new AssignedKilometers();
		LinkedList<String> kilometers = new LinkedList<String>();
		kilometers.add("12");
		kilometers.add("5");
		kilometers.add("8");
		kilometers.add("3");
		kilometers.add("7");
		kilometers.add("12");
		kilometers.add("15");
		assignedKilometers.kilometers = kilometers;
	}

	@Test
	public void testGetKilometers()
	{
		LinkedList<String> kilometers = new LinkedList<String>();
		kilometers.add("12");
		kilometers.add("5");
		kilometers.add("8");
		kilometers.add("3");
		kilometers.add("7");
		kilometers.add("12");
		kilometers.add("15");
		assertEquals(assignedKilometers.getKilometers(), kilometers);
	}

	@Test
	public void testSetKilometers()
	{
		LinkedList<String> kilometers = new LinkedList<String>();
		kilometers.add("10");
		kilometers.add("0");
		kilometers.add("8");
		kilometers.add("3");
		kilometers.add("7");
		kilometers.add("2");
		kilometers.add("5");
		assignedKilometers.setKilometers(kilometers);
		
		assertEquals(assignedKilometers.getKilometers(), kilometers);
	}

	@Test
	public void testGetIsNullFalse()
	{
		assertFalse(assignedKilometers.getIsNull());
	}
	
	@Test
	public void testGetIsNullTrue()
	{
		assignedKilometers = new AssignedKilometers();
		assertTrue(assignedKilometers.getIsNull());
	}

}
