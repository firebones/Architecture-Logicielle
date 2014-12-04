package ca.ulaval.glo4003.architecture_logicielle.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProjectEntryTest
{
	ProjectEntry project;
	List<TaskEntry> tasks = new ArrayList<TaskEntry>();
	TaskEntry task1;
	TaskEntry task2;
	
	@Before
	public void setUp() throws Exception
	{
		Integer id = 1;
		String name = "projet1";
		
		task1 = Mockito.mock(TaskEntry.class);
		Mockito.when(task1.getId()).thenReturn(1);
		Mockito.when(task1.getName()).thenReturn("tache1");
		
		task2 = Mockito.mock(TaskEntry.class);
		Mockito.when(task1.getId()).thenReturn(2);
		Mockito.when(task1.getName()).thenReturn("tache2");
		
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
		TaskEntry task3 = Mockito.mock(TaskEntry.class);
		Mockito.when(task3.getId()).thenReturn(3);
		Mockito.when(task3.getName()).thenReturn("tache3");
		
		project.addTask(task3);
		
		assertEquals(project.getTasks().size(), 3);

	}

	@Test
	public void testRemoveTask()
	{		
		project.removeTask(task2);
		
		assertEquals(project.getTasks().size(), 1);
	}

	@Test
	public void testUpdateTasks()
	{
		TaskEntry task3 = Mockito.mock(TaskEntry.class);
		Mockito.when(task3.getId()).thenReturn(3);
		Mockito.when(task3.getName()).thenReturn("tache3");
		
		TaskEntry task4 = Mockito.mock(TaskEntry.class);
		Mockito.when(task4.getId()).thenReturn(4);
		Mockito.when(task4.getName()).thenReturn("tache4");
		
		TaskEntry task5 = Mockito.mock(TaskEntry.class);
		Mockito.when(task5.getId()).thenReturn(5);
		Mockito.when(task5.getName()).thenReturn("tache5");
		
		tasks.add(task3);
		tasks.add(task4);
		tasks.add(task5);
		
		project.updateTasks(tasks);
		
		assertEquals(project.getTasks().size(), 3);

	}

}
