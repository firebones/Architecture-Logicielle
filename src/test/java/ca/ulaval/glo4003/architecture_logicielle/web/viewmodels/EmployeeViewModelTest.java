package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EmployeeViewModelTest
{
	private EmployeeViewModel employeeViewModel;

	@Before
	public void setUp() throws Exception
	{
		employeeViewModel = new EmployeeViewModel();
		employeeViewModel.email = "jane@gmail.com";
		employeeViewModel.name = "jane";
		employeeViewModel.roleUser = "MANAGER";
		employeeViewModel.hashedPassword = "1234";
		employeeViewModel.rateHour = "12";
		employeeViewModel.companyName = "Company 1";
		employeeViewModel.departmentName = "Department 1";
		employeeViewModel.tasks = new ArrayList<String>();
	}

	@Test
	public void testGetEmail()
	{
		assertEquals(employeeViewModel.getEmail(), "jane@gmail.com");
	}

	@Test
	public void testSetEmail()
	{
		employeeViewModel.setEmail("jane_company@gmail.com");
		assertEquals(employeeViewModel.getEmail(), "jane_company@gmail.com");
	}

	@Test
	public void testGetName()
	{
		assertEquals(employeeViewModel.getName(), "jane");
	}

	@Test
	public void testSetName()
	{
		employeeViewModel.setName("Jane");
		assertEquals(employeeViewModel.getName(), "Jane");
	}

	@Test
	public void testGetTasks()
	{
		assertEquals(employeeViewModel.getTasks(), new ArrayList<String>());
	}

	@Test
	public void testSetTasks()
	{
		List<String> tasks = new ArrayList<>();
		tasks.add("3");
		tasks.add("5");
		employeeViewModel.setTasks(tasks);
		assertEquals(employeeViewModel.getTasks(), tasks);
	}

	@Test
	public void testGetTauxHoraire()
	{
		assertEquals(employeeViewModel.getRateHour(), "12");
	}

	@Test
	public void testSetTauxHoraie()
	{
		employeeViewModel.setRateHour("20");
		assertEquals(employeeViewModel.getRateHour(), "20");
	}
	
	@Test
	public void testGetRoleUser()
	{
		assertEquals(employeeViewModel.getRoleUser(), "MANAGER");
	}
	
	@Test
	public void testSetRoleUser()
	{
		employeeViewModel.setRoleUser("EMPLOYE");
		assertEquals(employeeViewModel.getRoleUser(), "EMPLOYE");
	}
	
	@Test
	public void testGetHashedPassword()
	{
		assertEquals(employeeViewModel.getHashedPassword(), "1234");
	}
	
	@Test
	public void testSetHashedPassword()
	{
		employeeViewModel.setHashedPassword("azerty");
		assertEquals(employeeViewModel.getHashedPassword(), "azerty");
	}
	
	@Test
	public void testGetCompany()
	{
		assertEquals(employeeViewModel.getCompany(), "Company 1");
	}
	
	@Test
	public void testSetCompany()
	{
		employeeViewModel.setCompany("Company 2");
		assertEquals(employeeViewModel.getCompany(), "Company 2");
	}
	
	@Test
	public void testGetDepartment()
	{
		assertEquals(employeeViewModel.getDepartment(), "Department 1");
	}
	
	@Test
	public void testSetDepartment()
	{
		employeeViewModel.setDepartment("Department 2");
		assertEquals(employeeViewModel.getDepartment(), "Department 2");
	}

}
