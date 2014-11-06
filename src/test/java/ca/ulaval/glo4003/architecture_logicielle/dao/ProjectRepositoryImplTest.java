package ca.ulaval.glo4003.architecture_logicielle.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.architecture_logicielle.model.ProjectEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;

public class ProjectRepositoryImplTest
{
	ProjectRepositoryImpl repository; 
	ArrayList<ProjectEntry> projects = new ArrayList<ProjectEntry>();
	
	@Before
	public void setUp() throws Exception
	{
		repository = new ProjectRepositoryImpl();
	}

	@Test
	public void testGetAllProjects()
	{
		projects = repository.getAllProjects();
		
		assertEquals(projects.isEmpty(), false);
	}

	@Test
	public void testGetProjectById()
	{
		ProjectEntry project = repository.getProjectById(2);
		
		assertEquals((int)project.getId(), 2);
		assertEquals(project.getName(), "Projet2");
	}
	
	@Test
	public void testGetProjectByNotExistingId()
	{
		ProjectEntry project = repository.getProjectById(999);
		
		assertEquals(project, null);
	}

	@Test
	public void testGetTaskById()
	{
		TaskEntry task = repository.getTaskById(3);
		assertEquals((int)task.getId(), 3);
		assertEquals(task.getName(), "Tache1");
	}
	
	@Test
	public void testGetTaskByNotExistingId()
	{
		TaskEntry task = repository.getTaskById(999);
		assertEquals(task, null);
	}

	@Test
	public void testAddProject()
	{
/*		ProjectEntry project = new ProjectEntry();
		project.setId(10);
		project.setName("ProjetTest");
		
		repository.addProject(project);
		
		ProjectEntry testeur = repository.getProjectById(10);
		
		assertEquals((int)testeur.getId(), 10);
		assertEquals(testeur.getName(), "ProjetTest");
		repository.deleteProject(10);*/
	}

	@Test
	public void testDeleteProject()
	{
//		fail("Not yet implemented");
	}

	@Test
	public void testUpdateProject()
	{
//		fail("Not yet implemented");
	}

	@Test
	public void testAddTaskToProject()
	{
//		fail("Not yet implemented");
	}

	@Test
	public void testRemoveTaskFromProject()
	{
//		fail("Not yet implemented");
	}

}
