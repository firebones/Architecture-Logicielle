package ca.ulaval.glo4003.architecture_logicielle.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;

public class UserRepositoryImplTest
{
	ArrayList<UserEntry> users = new ArrayList<UserEntry>();
	ArrayList<EmployeeEntry> employees = new ArrayList<EmployeeEntry>();
	UserRepository repository;
	
	@Before
	public void setUp() throws Exception
	{
		repository = new UserRepositoryImpl();
	}

	@Test
	public void testGetAllUsers()
	{
		users = repository.getAllUsers();
		
		assertEquals(users.isEmpty(), false);
	}

	@Test
	public void testGetAllEmployees()
	{
		employees = repository.getAllEmployees();
		
		assertEquals(employees.isEmpty(), false);
	}

	@Test
	public void testGetUserByEmail()
	{
		UserEntry user = new EmployeeEntry();
		user = repository.getUserByEmail("joe@gmail.com");
		
		assertEquals(user.getName(), "Joe");
		assertEquals(user.getRole(), "EMPLOYEE");
	}

	@Test
	public void testAddUser()
	{
		UserEntry user = new EmployeeEntry();
		user.setName("Pape");
		user.setHashedPassword("1234");
		user.setEmail("pape@gmail.com");
		
		repository.addUser(user);
		
		UserEntry usertest = new EmployeeEntry();
		usertest = repository.getUserByEmail("pape@gmail.com");		
		
		assertEquals(usertest.getName(), "Pape");
		assertEquals(usertest.getRole(), "EMPLOYEE");	
		repository.deleteUser(user);
	}
	

	@Test
	public void testUpdateUser()
	{
		UserEntry user = new EmployeeEntry();
		user.setName("Pape");
		user.setHashedPassword("1234");
		user.setEmail("pape@gmail.com");
		repository.addUser(user);
		
		user.setName("Pascal");
		repository.updateUser(user);
		
		UserEntry usertest = new EmployeeEntry();
		usertest = repository.getUserByEmail("pape@gmail.com");		
		
		assertEquals(usertest.getName(), "Pascal");
		assertEquals(usertest.getRole(), "EMPLOYEE");	
		repository.deleteUser(user);
	}

	@Test
	public void testDeleteUser()
	{
		UserEntry user = new EmployeeEntry();
		user.setName("Pape");
		user.setHashedPassword("1234");
		user.setEmail("pape@gmail.com");
		
		repository.addUser(user);
		repository.deleteUser(user);
			
		assertEquals(repository.getUserByEmail("pape@gmail.com"), null);	
	}

	@Test
	public void testAddTaskToUser()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetTasksToUser()
	{
		fail("Not yet implemented");
	}

}
