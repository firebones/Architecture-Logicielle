package ca.ulaval.glo4003.architecture_logicielle.model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PeriodPayBuilderImplTest {
	private PeriodPayBuilderImpl periodPayBuilderImpl;

	@Before
	public void setUp() throws Exception {
		periodPayBuilderImpl = new PeriodPayBuilderImpl();
	}

	@Test
	public void testPeriodPayBuilderImpl() {
		PeriodPayBuilderImpl ppbl = new PeriodPayBuilderImpl();
		assertEquals(periodPayBuilderImpl, ppbl);
	}

	@Test
	public void testSetInformation() {
		String email = "jane@gmail.com";
		int weekN = 42;
		int year = 2014;
		StateWeekEntry state= StateWeekEntry.APPROVED;
		periodPayBuilderImpl.setInformation(email, weekN, year, state);
		WeekEntry we = new WeekEntry();
		we.setEmail(email);
		we.setWeekNumber(weekN);
		we.setYearNumber(year);
		we.setState(state);
		assertEquals(periodPayBuilderImpl.getPeriodPayEntry(), we);
	}

	@Test
	public void testSetEmployeeKilometers() {
		List<Integer> kilometers = new LinkedList<>();
		kilometers.add(12);
		kilometers.add(15);
		kilometers.add(42);
		kilometers.add(0);
		kilometers.add(1);
		kilometers.add(2);
		kilometers.add(7);
		periodPayBuilderImpl.setEmployeeKilometers(kilometers);
		WeekEntry we = new WeekEntry();
		we.setKilometersEntries(kilometers);
		assertEquals(periodPayBuilderImpl.getPeriodPayEntry(), we);
	}

	@Test
	public void testSetEmployeeExpenses() {
		List<Double> expenses = new LinkedList<>();
		expenses.add(12.3);
		expenses.add(15.5);
		expenses.add(2.8);
		expenses.add(0.0);
		expenses.add(1.7);
		expenses.add(2.3);
		expenses.add(7.9);
		periodPayBuilderImpl.setEmployeeExpenses(expenses);
		WeekEntry we = new WeekEntry();
		we.setEmployeeExpensesEntries(expenses);
		assertEquals(periodPayBuilderImpl.getPeriodPayEntry(), we);
	}

	@Test
	public void testSetEmployeeHours() {
		List<Double> hours = new LinkedList<>();
		hours.add(5.3);
		hours.add(4.5);
		hours.add(3.8);
		hours.add(6.0);
		hours.add(2.7);
		hours.add(2.3);
		hours.add(7.9);
		periodPayBuilderImpl.setEmployeeHours(hours);
		WeekEntry we = new WeekEntry();
		we.setHoursEntries(hours);
		assertEquals(periodPayBuilderImpl.getPeriodPayEntry(), we);
	}

	@Test
	public void testGetPeriodPayEntry() {
		assertEquals(periodPayBuilderImpl.getPeriodPayEntry(), new WeekEntry());
	}

}
