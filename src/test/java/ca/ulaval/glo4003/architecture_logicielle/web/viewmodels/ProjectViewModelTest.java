package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ProjectViewModelTest
{
	private ProjectViewModel projectViewModel;

	@Before
	public void setUp() throws Exception
	{
		projectViewModel = new ProjectViewModel();
		projectViewModel.id = 3;
		projectViewModel.name = "Project 1";
		projectViewModel.tasks = new LinkedList<TaskViewModel>();
	}

	@Test
	public void testGetId()
	{
		assertEquals(projectViewModel.getId(), new Integer(3));
	}

	@Test
	public void testSetId()
	{
		projectViewModel.setId(5);
		assertEquals(projectViewModel.getId(), new Integer(5));
	}

	@Test
	public void testGetName()
	{
		assertEquals(projectViewModel.getName(), "Project 1");
	}

	@Test
	public void testSetName()
	{
		projectViewModel.setName("Project 2");
		assertEquals(projectViewModel.getName(), "Project 2");
	}

	@Test
	public void testGetTasks()
	{
		List<TaskViewModel> tasks = new LinkedList<TaskViewModel>();
		assertEquals(projectViewModel.getTasks(), tasks);
	}

	@Test
	public void testSetTasks()
	{
		List<TaskViewModel> tasks = new LinkedList<TaskViewModel>();
		tasks.add(new TaskViewModel());
		projectViewModel.setTasks(tasks);
		assertEquals(projectViewModel.getTasks(), tasks);
	}

}
