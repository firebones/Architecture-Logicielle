package ca.ulaval.glo4003.architecture_logicielle.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ProjectEntryTest
{
	ProjectEntry project;
	List<TaskEntry> tasks = new ArrayList<TaskEntry>();
	
	@Before
	public void setUp() throws Exception
	{
		Integer id = 1;
		String name = "projet1";
		
		TaskEntry task1 = new TaskEntry();
		task1.setId(1);
		task1.setName("tache1");
		
		TaskEntry task2 = new TaskEntry();
		task2.setId(2);
		task2.setName("tache2");
		
		project = new ProjectEntry();
		project.setId(id);
		project.setName(name);
		project.addTask(task1);
		project.addTask(task2);
	}

	@Test
	public void testGetId()
	{
		int idtest = project.getId();
		
		assertEquals(idtest, 1);
	}

	@Test
	public void testSetId()
	{
		project.setId(2);
		
		assertEquals((int)project.getId(), 2);

	}

	@Test
	public void testGetName()
	{
		String nametest = project.getName();
		
		assertEquals(nametest, "projet1");

	}

	@Test
	public void testSetName()
	{
		project.setName("newprojet");
		
		assertEquals(project.getName(), "newprojet");

	}

	@Test
	public void testGetTasks()
	{
		tasks = project.getTasks();
		
		assertEquals(tasks.size(), 2);

	}

	@Test
	public void testAddTask()
	{
		TaskEntry task3 = new TaskEntry();
		task3.setId(3);
		task3.setName("tache3");
		
		project.addTask(task3);
		
		assertEquals(project.getTasks().size(), 3);

	}

	@Test
	public void testRemoveTask()
	{
		TaskEntry task2 = new TaskEntry();
		task2.setId(2);
		task2.setName("tache2");
		
		project.removeTask(task2);
		
		assertEquals(project.getTasks().size(), 1);
	}

	@Test
	public void testUpdateTasks()
	{
		TaskEntry task3 = new TaskEntry();
		task3.setId(3);
		task3.setName("tache1");
		
		TaskEntry task4 = new TaskEntry();
		task4.setId(4);
		task4.setName("tache2");
		
		TaskEntry task5 = new TaskEntry();
		task5.setId(5);
		task5.setName("tache2");
		
		tasks.add(task3);
		tasks.add(task4);
		tasks.add(task5);
		
		project.updateTasks(tasks);
		
		assertEquals(project.getTasks().size(), 3);

	}

}
