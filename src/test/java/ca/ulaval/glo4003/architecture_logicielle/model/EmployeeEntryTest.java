package ca.ulaval.glo4003.architecture_logicielle.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EmployeeEntryTest
{
	private List<TaskEntry> tasks = new ArrayList<TaskEntry>();
	private EmployeeEntry employee;

	@Before
	public void setUp() throws Exception
	{
		employee = new EmployeeEntry();
		
		TaskEntry task1 = new TaskEntry();
		task1.setId(1);
		task1.setName("tache1");
		employee.assignTask(task1);
		
		TaskEntry task2 = new TaskEntry();
		task2.setId(2);
		task2.setName("tache2");
		employee.assignTask(task2);	
	}

	@Test
	public void testGetTasks()
	{
		tasks = employee.getTasks();
		
		assertEquals(tasks.size(), 2);
	}

	@Test
	public void testAssignTask()
	{
		TaskEntry task3 = new TaskEntry();
		task3.setId(3);
		task3.setName("tache3");
		employee.assignTask(task3);
		
		assertEquals(employee.getTasks().size(), 3);
	}

	@Test
	public void testRemoveTask()
	{
		TaskEntry task1 = new TaskEntry();
		task1.setId(1);
		task1.setName("tache1");
		
		employee.removeTask(task1);
		
		assertEquals(employee.getTasks().size(), 1);
	}

	@Test
	public void testUpdateTasks()
	{
		TaskEntry task3 = new TaskEntry();
		task3.setId(3);
		task3.setName("tache3");
		
		TaskEntry task4 = new TaskEntry();
		task4.setId(4);
		task4.setName("tache4");
		
		TaskEntry task5 = new TaskEntry();
		task5.setId(5);
		task5.setName("tache5");
		
		TaskEntry task6 = new TaskEntry();
		task6.setId(6);
		task6.setName("tache6");
		
		tasks.add(task3);
		tasks.add(task4);
		tasks.add(task5);
		tasks.add(task6);
		employee.updateTasks(tasks);
		
		assertEquals(employee.getTasks().size(), 4);
	}

	@Test
	public void testGetTasksString()
	{
		List<String> tasksString = new ArrayList<String>();
		tasksString = employee.getTasksString();
		
		assertEquals(tasksString.isEmpty(), false);
	}

}
