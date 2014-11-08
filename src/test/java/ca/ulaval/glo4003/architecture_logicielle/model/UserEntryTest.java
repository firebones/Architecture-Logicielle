package ca.ulaval.glo4003.architecture_logicielle.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserEntryTest
{
	private UserEntry user;

	@Before
	public void setUp() throws Exception
	{		
		user = new EmployeeEntry();
		user.setName("Pape");
		user.setEmail("pape@gmail.com");
		user.setHashedPassword("1234");
	}

	@Test
	public void testGetName()
	{
		String name = user.getName();
		
		assertEquals(name, "Pape");
	}

	@Test
	public void testSetName()
	{
		user.setName("Jean");
		
		assertEquals(user.getName(), "Jean");
	}

	@Test
	public void testGetEmail()
	{
		String email = user.getEmail();
		
		assertEquals(email, "pape@gmail.com");
	}

	@Test
	public void testSetEmail()
	{
		user.setEmail("jean@gmail.com");
		
		assertEquals(user.getEmail(), "jean@gmail.com");
	}

	@Test
	public void testGetHashedPassword()
	{
		String hashedPassword = user.getHashedPassword();
		
		assertEquals(hashedPassword, "1234");
	}

	@Test
	public void testSetHashedPassword()
	{
		user.setHashedPassword("4321");
		
		assertEquals(user.getHashedPassword(), "4321");
	}

	@Test
	public void testGetRole()
	{
		String role = user.getRole();
		
		assertEquals(role, "EMPLOYEE");
	}

	@Test
	public void testIsPasswordValid()
	{
		boolean test = user.isPasswordValid("1234");
		
		assertEquals(test, true);
	}
	
	@Test
	public void testIsNotPasswordValid()
	{
		boolean test = user.isPasswordValid("4321");
		
		assertEquals(test, false);
	}

}
