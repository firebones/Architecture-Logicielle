package ca.ulaval.glo4003.architecture_logicielle.model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ModelFacadeTest
{
	ModelFacade facade;
	WeekEntry weekEntry;
	EmployeeEntry employee;
	String currentUserEmail = "joe@gmail.com";
	String email = "pape@test.com"; 
	int weekNumber = 1; 
	int yearNumber = 2016;
	
	@Before
	public void setUp() throws Exception
	{
		facade = new ModelFacade();
		employee = Mockito.mock(EmployeeEntry.class);
		weekEntry = Mockito.mock(WeekEntry.class);
		
		Mockito.when(employee.getEmail()).thenReturn(email);
		Mockito.when(weekEntry.getEmail()).thenReturn(email);
		Mockito.when(weekEntry.getWeekNumber()).thenReturn(weekNumber);
		Mockito.when(weekEntry.getYearNumber()).thenReturn(yearNumber);
		Mockito.when(weekEntry.getState()).thenReturn(StateWeekEntry.INPROGRESS);
	}

	@Test
	public void testCreateWeekEntry()
	{
		facade.createWeekEntry(email, weekNumber, yearNumber);
		
		assertEquals(facade.getWeekEntry(email, weekNumber, yearNumber), weekEntry);	
	}

	@Test
	public void testGetWeekEntry()
	{
		facade.createWeekEntry(email, weekNumber, yearNumber);
		assertNotNull(facade.getWeekEntry(email, weekNumber, yearNumber));
	}

	@Test
	public void testGetWeekEntries()
	{
		List<WeekEntry> list = new LinkedList<WeekEntry>();
		list = facade.getWeekEntries(currentUserEmail);
		
		assertNotNull(list);
	}

	@Test
	public void testGetAllWeekEntries()
	{
		assertNotNull(facade.getAllWeekEntries());
	}

	@Test
	public void testAssignHoursToEmployeeWeek()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAssignKilometersToEmployeeWeek()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAssignExpensesToEmployeeWeek()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSubmitWeekEntry()
	{
		facade.submitWeekEntry(email, weekNumber, yearNumber);
		
		assertEquals(facade.getWeekEntry(email, weekNumber, yearNumber).getState(), StateWeekEntry.SUBMITTED);
	}

	@Test
	public void testApprovedWeekEntry()
	{
		facade.approvedWeekEntry(email, weekNumber, yearNumber);
		
		assertEquals(facade.getWeekEntry(email, weekNumber, yearNumber).getState(), StateWeekEntry.APPROVED);
	}

	@Test
	public void testDeniedWeekEntry()
	{
		facade.deniedWeekEntry(email, weekNumber, yearNumber);
		
		assertEquals(facade.getWeekEntry(email, weekNumber, yearNumber).getState(), StateWeekEntry.REFUSED);
	}

	@Test
	public void testGetAllProjects()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetProjectById()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetTaskById()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddProject()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddTask()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdateProject()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdateTask()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAssignedTaskToEmployee()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAllUsers()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAllEmployees()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetUserByEmail()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddUser()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdateUser()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDeleteUser()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetTasksEmployee()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCreateEmployee()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetDepartmentEntryByName()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAprovedSend()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRefusedSend()
	{
		fail("Not yet implemented"); // TODO
	}

}
