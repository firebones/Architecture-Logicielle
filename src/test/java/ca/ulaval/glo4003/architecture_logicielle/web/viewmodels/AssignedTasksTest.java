package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AssignedTasksTest
{
	AssignedTasks assignedTasks;
	
	@Before
	public void setUp() throws Exception
	{
		assignedTasks = new AssignedTasks();
		List<String> tasks = new ArrayList<>();
		tasks.add("2");
		tasks.add("7");
		assignedTasks.tasks = tasks;
	}

	@Test
	public void testGetTasks()
	{
		List<String> tasks = new ArrayList<>();
		tasks.add("2");
		tasks.add("7");
		assertEquals(assignedTasks.getTasks(), tasks);
	}

	@Test
	public void testSetTasks()
	{
		List<String> tasks = new ArrayList<>();
		tasks.add("5");
		assignedTasks.setTasks(tasks);
		assertEquals(assignedTasks.getTasks(), tasks);
	}

}
