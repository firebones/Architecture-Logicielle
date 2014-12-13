package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TaskViewModelTest
{
	TaskViewModel taskViewModel;

	@Before
	public void setUp() throws Exception
	{
		taskViewModel = new TaskViewModel();
		taskViewModel.id = 2;
		taskViewModel.isAssigned = false;
		taskViewModel.name = "Task 1";
		taskViewModel.rate = 2.3;
	}

	@Test
	public void testGetId()
	{
		assertEquals(taskViewModel.getId(), new Integer(2));
	}

	@Test
	public void testSetId()
	{
		taskViewModel.setId(5);
		assertEquals(taskViewModel.getId(), new Integer(5));
	}

	@Test
	public void testGetName()
	{
		assertEquals(taskViewModel.getName(), "Task 1");
	}

	@Test
	public void testSetName()
	{
		taskViewModel.setName("Task 2");
		assertEquals(taskViewModel.getName(), "Task 2");
	}

	@Test
	public void testGetIsAssigned()
	{
		assertFalse(taskViewModel.isAssigned);
	}

	@Test
	public void testSetIsAssigned()
	{
		taskViewModel.setIsAssigned(true);
		assertTrue(taskViewModel.isAssigned);
	}

	@Test
	public void testGetRate()
	{
		assertEquals(taskViewModel.getRate(), new Double(2.3));
	}

	@Test
	public void testSetRate()
	{
		taskViewModel.setRate(4.5);
		assertEquals(taskViewModel.getRate(), new Double(4.5));
	}

}
