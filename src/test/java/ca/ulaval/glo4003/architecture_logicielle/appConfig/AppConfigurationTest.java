package ca.ulaval.glo4003.architecture_logicielle.appConfig;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ca.ulaval.glo4003.architecture_logicielle.dao.ProjectRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.dao.UserRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.dao.WeekEntryRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.model.ProjectRepository;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserRepository;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntryRepository;
import ca.ulaval.glo4003.architecture_logicielle.util.MailService;
import ca.ulaval.glo4003.architecture_logicielle.util.MailServiceImpl;

public class AppConfigurationTest
{
	AppConfiguration appConfiguration;
	UserRepository userRepository;
	WeekEntryRepository weekRepository;
	ProjectRepository projectRepository;
	MailService mailService;
	
	WeekEntry weekEntry;
	UserEntry user1;
	String email = "joe@gmail.com";		
	Integer yearNumber = 2014;
	Integer weekNumber = 41;
	
	@Before
	public void setUp() throws Exception
	{
		appConfiguration = new AppConfiguration();
		userRepository = new UserRepositoryImpl();
		weekRepository = new WeekEntryRepositoryImpl();
		projectRepository = new ProjectRepositoryImpl();
		mailService = new MailServiceImpl();
		
		user1 = Mockito.mock(UserEntry.class);
		Mockito.when(user1.getEmail()).thenReturn(email);
		
		weekEntry = Mockito.mock(WeekEntry.class);
		Mockito.when(weekEntry.getEmail()).thenReturn(email);
		Mockito.when(weekEntry.getWeekNumber()).thenReturn(weekNumber);
		Mockito.when(weekEntry.getYearNumber()).thenReturn(yearNumber);
	}

	@Test
	public void testGetCurrentUser()
	{
//		String email = appConfiguration.getCurrentUser();
		
//		assertNotNull(email);
	}

	@Test
	public void testGetUserWeekEntry()
	{
		
		WeekEntry w = appConfiguration.getUserWeekEntry(email, weekNumber, yearNumber);
		
		assertEquals(w.getEmail(), email);
	}

	@Test
	public void testGetCurrentUserWeekEntries()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAllWeekEntries()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCreateWeekEntryStringIntInt()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetWeekEntryByEmail()
	{
		fail("Not yet implemented"); // TODO
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
	public void testAssignedTaskToEmployee()
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
	public void testGetTasksEmployee()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDeleteUser()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAllDepartmentEntries()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetDepartmentEntryByName()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdateWeekEntry()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSubmitWeekEntry()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testApprovedWeekEntry()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDeniedWeekEntry()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCreateWeekEntryCreatedWeekNumber()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetNoCreatedWeeks()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetCurrentWeekInYear()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetCurrentYear()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetNumberWeeksInYear()
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
