package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class AssignedExpensesTest
{
	private AssignedExpenses assignedExpenses;

	@Before
	public void setUp() throws Exception
	{
		assignedExpenses = new AssignedExpenses();
		LinkedList<String> expenses = new LinkedList<String>();
		expenses.add("12");
		expenses.add("5");
		expenses.add("8");
		expenses.add("3");
		expenses.add("7");
		expenses.add("12");
		expenses.add("15");
		assignedExpenses.expenses = expenses;
	}

	@Test
	public void testGetExpenses()
	{
		LinkedList<String> expenses = new LinkedList<String>();
		expenses.add("12");
		expenses.add("5");
		expenses.add("8");
		expenses.add("3");
		expenses.add("7");
		expenses.add("12");
		expenses.add("15");
		
		assertEquals(assignedExpenses.getExpenses(), expenses);
	}

	@Test
	public void testSetExpenses()
	{
		LinkedList<String> expenses = new LinkedList<String>();
		expenses.add("2");
		expenses.add("15");
		expenses.add("8");
		expenses.add("3");
		expenses.add("27");
		expenses.add("12");
		expenses.add("1");
		assignedExpenses.setExpenses(expenses);

		assertEquals(assignedExpenses.getExpenses(), expenses);
	}
	
	@Test
	public void testGetIsNullFalse()
	{
		assertFalse(assignedExpenses.getIsNull());
	}

	@Test
	public void testGetIsNullTrue()
	{
		AssignedExpenses ae= new AssignedExpenses();
		assertTrue(ae.getIsNull());
	}

}
