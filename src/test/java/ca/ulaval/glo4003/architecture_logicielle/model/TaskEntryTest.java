package ca.ulaval.glo4003.architecture_logicielle.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TaskEntryTest
{
	private TaskEntry task;
	
	@Before
	public void setUp() throws Exception
	{
		Integer id = 1;
		String name = "tache1";
		Double rate = 1.11;
		
		task = new TaskEntry();
		task.setId(id);
		task.setName(name);
		task.setRate(rate);

	}

	@Test
	public void testGetId()
	{
		int id1 = task.getId();
		
		assertEquals(id1, 1);
	}

	@Test
	public void testSetId()
	{
		task.setId(2);
		
		assertEquals((Object)task.getId(), 2);

	}

	@Test
	public void testGetName()
	{
		String name = task.getName();
		
		assertEquals(name, "tache1");
	}

	@Test
	public void testSetName()
	{
		task.setName("newTache");
		
		assertEquals(task.getName(), "newTache");

	}

	@Test
	public void testGetRate()
	{
		Double id1 = task.getRate();
		
		assertEquals((Object)id1, 1.11);

	}

	@Test
	public void testSetRate()
	{
		task.setRate(1.21);
		
		assertEquals((Object)task.getRate(), 1.21);

	}

	@Test
	public void testEqualsObject()
	{
		boolean test = task.equals(task);
		
		assertEquals(test, true);

	}

}
