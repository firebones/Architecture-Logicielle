package ca.ulaval.glo4003.architecture_logicielle.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

//import junit.framework.Assert;






import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeEntryTest
{
	private List<TaskEntry> tasks = new ArrayList<TaskEntry>();
	private EmployeeEntry employee;
	WeekEntry weekEntry;
	List<Double> hours;
	List<Integer> kilometers;
	List<Double> expenses;
	TaskEntry task1;
	TaskEntry task2;

	@Before
	public void setUp() throws Exception
	{
		employee = new EmployeeEntry();
		
		hours = new ArrayList<Double>();
		hours.add(8.0);hours.add(8.0);hours.add(8.0);hours.add(7.5);hours.add(8.0);hours.add(8.0);hours.add(8.0);

		kilometers = new ArrayList<Integer>();
		kilometers.add(25);kilometers.add(25);kilometers.add(25);kilometers.add(20);kilometers.add(25);kilometers.add(25);kilometers.add(25);

		expenses = new ArrayList<Double>();
		expenses.add(100.0);expenses.add(100.0);expenses.add(100.0);expenses.add(75.5);expenses.add(90.0);expenses.add(100.0);expenses.add(80.0);

		weekEntry = Mockito.mock(WeekEntry.class);
		Mockito.when(weekEntry.getKilometersEntries()).thenReturn(kilometers);
		Mockito.when(weekEntry.getHoursEntries()).thenReturn(hours);
		Mockito.when(weekEntry.getEmployeeExpensesEntries()).thenReturn(expenses);
		
		task1 = Mockito.mock(TaskEntry.class);
		Mockito.when(task1.getId()).thenReturn(1);
		Mockito.when(task1.getName()).thenReturn("tache1");
		employee.assignTask(task1);
		
		task2 = Mockito.mock(TaskEntry.class);
		Mockito.when(task1.getId()).thenReturn(2);
		Mockito.when(task1.getName()).thenReturn("tache2");
		employee.assignTask(task2);	

	}

	@Test
	public void testEmployeeEntry()
	{
		EmployeeEntry newemployee = new EmployeeEntry();
		
		assertNotNull(newemployee);
	}

	@Test
	public void testEmployeeEntryRoleUser()
	{
		EmployeeEntry newEmployee = new EmployeeEntry();
		EmployeeEntry newManager = new EmployeeEntry(RoleUser.MANAGER);
		
		assertEquals(newEmployee.getRole(), RoleUser.EMPLOYEE);
		assertEquals(newManager.getRole(), RoleUser.MANAGER);
	}

	@Test
	public void testGetRateHour()
	{
		Double rateHour = employee.getRateHour();
		
		Assert.assertEquals(rateHour.toString(), "20.0");
	}

	@Test
	public void testSetRateHour()
	{
		employee.setRateHour(35.5);
		
		Assert.assertEquals(employee.getRateHour().toString(), "35.5");
	}

	@Test
	public void testGetTasks()
	{
		tasks = employee.getTasks();
		
		assertEquals(tasks.size(), 2);

	}

	@Test
	public void testAssignTask()
	{
		TaskEntry task3 = Mockito.mock(TaskEntry.class);
		Mockito.when(task3.getId()).thenReturn(3);
		Mockito.when(task3.getName()).thenReturn("tache3");
		
		employee.assignTask(task3);
		
		assertEquals(employee.getTasks().size(), 3);

	}

	@Test
	public void testRemoveTask()
	{	
		employee.removeTask(task1);
		
		assertEquals(employee.getTasks().size(), 1);

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
		
		TaskEntry task6 = Mockito.mock(TaskEntry.class);
		Mockito.when(task6.getId()).thenReturn(6);
		Mockito.when(task6.getName()).thenReturn("tache6");
		
		tasks.add(task3);
		tasks.add(task4);
		tasks.add(task5);
		tasks.add(task6);
		employee.updateTasks(tasks);
		
		assertEquals(employee.getTasks().size(), 4);

	}

	@Test
	public void testGetTasksString()
	{
		List<String> tasksString = new ArrayList<String>();
		tasksString = employee.getTasksString();
		
		assertEquals(tasksString.isEmpty(), false);

	}

	@Test
	public void testGetCompany()
	{
		String company = employee.getCompany();
		
		assertEquals(company, "Company1");
	}

	@Test
	public void testSetCompany()
	{
		employee.setCompany("Company10");
		
		assertEquals(employee.getCompany(), "Company10");
	}

	@Test
	public void testGetDepartment()
	{
		String department = employee.getDepartment();
		
		assertEquals(department, "Department1");
	}

	@Test
	public void testSetDepartment()
	{
		employee.setDepartment("Department10");
		
		assertEquals(employee.getDepartment(), "Department10");
	}

	@Test
	public void testBecomeManager()
	{
		EmployeeEntry newEmployee = new EmployeeEntry();
		newEmployee.becomeManager();
		
		assertEquals(newEmployee.getRole(), RoleUser.MANAGER);
	}

	@Test
	public void testBecomeEmployee()
	{
		EmployeeEntry newManager = new EmployeeEntry(RoleUser.MANAGER);
		newManager.becomeEmployee();
		
		assertEquals(newManager.getRole(), RoleUser.EMPLOYEE);
	}

	@Test
	public void testEnterWorkHours()
	{		
		WeekEntry newWeekEntry = employee.enterWorkHours(weekEntry, hours);
		
		assertEquals(newWeekEntry.getHoursEntries(), hours);
	}

	@Test
	public void testEnterKilometers()
	{		
		WeekEntry newWeekEntry = employee.enterKilometers(weekEntry, kilometers);
		
		assertEquals(newWeekEntry.getKilometersEntries(), kilometers);
	}

	@Test
	public void testEnterExpenses()
	{		
		WeekEntry newWeekEntry = employee.enterWorkHours(weekEntry, expenses);
		
		assertEquals(newWeekEntry.getEmployeeExpensesEntries(), expenses);
	}

}
