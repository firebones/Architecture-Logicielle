package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CreatedWeekNumberTest
{
	private CreatedWeekNumber createdWeekNumber;

	@Before
	public void setUp() throws Exception
	{
		createdWeekNumber = new CreatedWeekNumber();
		createdWeekNumber.setWeekNumber(43);
	}

	@Test
	public void testGetWeekNumber()
	{
		assertEquals(createdWeekNumber.getWeekNumber(), new Integer(43));
	}

	@Test
	public void testSetWeekNumber()
	{
		createdWeekNumber.setWeekNumber(45);
		assertEquals(createdWeekNumber.getWeekNumber(), new Integer(45));
	}

}
