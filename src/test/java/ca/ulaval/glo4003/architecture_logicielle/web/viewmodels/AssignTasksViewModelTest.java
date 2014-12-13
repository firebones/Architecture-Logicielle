package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AssignTasksViewModelTest
{
	AssignTasksViewModel assignTasksViewModel;

	@Before
	public void setUp() throws Exception
	{
		assignTasksViewModel = new AssignTasksViewModel();
		assignTasksViewModel.userId = "joe@gmail.com";
		assignTasksViewModel.projects = new LinkedList<ProjectViewModel>();
	}

	@Test
	public void testGetUserId()
	{
		assertEquals(assignTasksViewModel.getUserId(), "joe@gmail.com");
	}

	@Test
	public void testSetUserId()
	{
		assignTasksViewModel.setUserId("jane@gmail.com");
		assertEquals(assignTasksViewModel.getUserId(), "jane@gmail.com");
	}

	@Test
	public void testGetProjects()
	{
		assertEquals(assignTasksViewModel.getProjects(), new LinkedList<ProjectViewModel>());
	}

	@Test
	public void testSetProjects()
	{
		List<ProjectViewModel> projects = new LinkedList<>();
		projects.add(new ProjectViewModel());
		assignTasksViewModel.setProjects(projects);
		assertEquals(assignTasksViewModel.getProjects(), projects);
	}

}
