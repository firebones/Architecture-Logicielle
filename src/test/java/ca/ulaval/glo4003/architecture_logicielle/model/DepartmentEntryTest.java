package ca.ulaval.glo4003.architecture_logicielle.model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;

public class DepartmentEntryTest
{
	DepartmentEntry department;
	EmployeeEntry manager;
	EmployeeEntry employee;
	
	@Before
	public void setUp() throws Exception
	{
		department = new DepartmentEntry();
		department.setDepartmentName("Department11");
		
		manager = Mockito.mock(EmployeeEntry.class);
		Mockito.when(manager.getRole()).thenReturn(RoleUser.MANAGER);
		employee = Mockito.mock(EmployeeEntry.class);
		Mockito.when(employee.getRole()).thenReturn(RoleUser.EMPLOYEE);
	}

	@Test
	public void testDepartmentEntry()
	{
		DepartmentEntry newdepartment = new DepartmentEntry();
		
		assertNotNull(newdepartment);
	}

	@Test
	public void testDepartmentEntryString()
	{
		String nameDepartment = "DepartmentTest";
		DepartmentEntry newdepartment = new DepartmentEntry(nameDepartment);
		
		assertEquals(newdepartment.getDepartmentName(), "DepartmentTest");
	}

	@Test
	public void testAddDepartmentManager()
	{
		department.addDepartmentManager(manager);
		
		Assert.notEmpty(department.getDeptManagers());
	}

	@Test
	public void testRemoveDepartmentManager()
	{
		department.addDepartmentManager(manager);
		
		EmployeeEntry manager2 = Mockito.mock(EmployeeEntry.class);
		Mockito.when(manager2.getRole()).thenReturn(RoleUser.MANAGER);
		department.addDepartmentManager(manager2);
		
		department.removeDepartmentManager(manager);
		
		assertEquals(department.getDeptManagers().size(), 1);
	}

	@Test
	public void testIsManagerInDepartment()
	{		
		EmployeeEntry manager2 = Mockito.mock(EmployeeEntry.class);
		Mockito.when(manager2.getRole()).thenReturn(RoleUser.MANAGER);
		department.addDepartmentManager(manager2);
		
		assertEquals(department.isManagerInDepartment(manager2), true);
		assertEquals(department.isManagerInDepartment(manager), false);
	}

	@Test
	public void testAddEmployee()
	{
		department.addEmployee(employee);
		
		Assert.notEmpty(department.getEmployees());
	}

	@Test
	public void testRemoveEmployee()
	{
		department.addEmployee(employee);
		
		EmployeeEntry employee2 = Mockito.mock(EmployeeEntry.class);
		Mockito.when(employee2.getRole()).thenReturn(RoleUser.EMPLOYEE);
		department.addDepartmentManager(employee2);
		
		department.removeEmployee(employee2);
		
		assertEquals(department.getEmployees().size(), 1);
	}

	@Test
	public void testIsEmployeeInDepartment()
	{
		EmployeeEntry employee2 = Mockito.mock(EmployeeEntry.class);
		Mockito.when(employee2.getRole()).thenReturn(RoleUser.EMPLOYEE);
		department.addEmployee(employee2);
				
		assertEquals(department.isEmployeeInDepartment(employee2), true);
		assertEquals(department.isEmployeeInDepartment(manager), false);
	}

	@Test
	public void testGetDepartmentName()
	{
		assertEquals(department.getDepartmentName(), "Department11");
	}

	@Test
	public void testSetDepartmentName()
	{
		department.setDepartmentName("Department22");
		
		assertEquals(department.getDepartmentName(), "Department22");
	}

	@Test
	public void testGetEmployees()
	{
		department.addEmployee(employee);
		
		EmployeeEntry employee2 = Mockito.mock(EmployeeEntry.class);
		Mockito.when(employee2.getRole()).thenReturn(RoleUser.EMPLOYEE);
		department.addEmployee(employee2);
		
		assertEquals(department.getEmployees().size(), 2);
	}

	@Test
	public void testSetEmployees()
	{	
		List<EmployeeEntry> listEmployees = new LinkedList<EmployeeEntry>();
		
		EmployeeEntry employee2 = Mockito.mock(EmployeeEntry.class);
		Mockito.when(employee2.getRole()).thenReturn(RoleUser.EMPLOYEE);
		
		listEmployees.add(employee);
		listEmployees.add(employee2);
		
		department.setEmployees(listEmployees);
		
		assertEquals(department.getEmployees().size(), 2);
	}

	@Test
	public void testGetDeptManagers()
	{
		department.addDepartmentManager(manager);
		
		EmployeeEntry manager2 = Mockito.mock(EmployeeEntry.class);
		Mockito.when(manager2.getRole()).thenReturn(RoleUser.MANAGER);
		department.addDepartmentManager(manager2);
		
		assertEquals(department.getDeptManagers().size(), 2);
	}

	@Test
	public void testSetDeptManagers()
	{
		List<EmployeeEntry> listManager = new LinkedList<EmployeeEntry>();
		
		EmployeeEntry manager2 = Mockito.mock(EmployeeEntry.class);
		Mockito.when(manager2.getRole()).thenReturn(RoleUser.MANAGER);
		
		listManager.add(manager2);
		listManager.add(manager);
		
		department.setDeptManagers(listManager);
		
		assertEquals(department.getDeptManagers().size(), 2);
	}

	@Test
	public void testGetSubmittedWeekEntries()
	{
//		WeekEntry weekEntry = Mockito.mock(WeekEntry.class);
//		Mockito.when(weekEntry.getState()).thenReturn(StateWeekEntry.SUBMITTED);
		fail("Not yet implemented");
	}

	@Test
	public void testGetApprovedWeekEntries()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetRefusedWeekEntries()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetInProgressWeekEntries()
	{
		fail("Not yet implemented"); // TODO
	}

}
