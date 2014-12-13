package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EntryViewModelTest
{
	private EntryViewModel entryViewModel;
	
	@Before
	public void setUp() throws Exception
	{
		entryViewModel = new EntryViewModel();
		entryViewModel.setDatesOfWeek(new LinkedList<String>());
		entryViewModel.setDaysOfWeek(new LinkedList<String>());
		entryViewModel.setEntries(new LinkedList<String>());
		entryViewModel.setIsReadOnly(false);
	}

	@Test
	public void testGetEntries()
	{
		assertEquals(entryViewModel.getEntries(), new LinkedList<String>());
	}

	@Test
	public void testSetEntries()
	{
		List<String> entries = new LinkedList<>();
		entries.add("3");
		entryViewModel.setEntries(entries);
		assertEquals(entryViewModel.getEntries(), entries);
	}

	@Test
	public void testGetDaysOfWeek()
	{
		assertEquals(entryViewModel.getDaysOfWeek(), new LinkedList<String>());
	}

	@Test
	public void testSetDaysOfWeek()
	{
		List<String> daysOfWeek = new LinkedList<>();
		daysOfWeek.add("1");
		entryViewModel.setEntries(daysOfWeek);
		assertEquals(entryViewModel.getEntries(), daysOfWeek);
	}

	@Test
	public void testGetDatesOfWeek()
	{
		assertEquals(entryViewModel.getDatesOfWeek(), new LinkedList<String>());
	}

	@Test
	public void testSetDatesOfWeek()
	{
		List<String> datesOfWeek = new LinkedList<>();
		datesOfWeek.add("03/12");
		entryViewModel.setEntries(datesOfWeek);
		assertEquals(entryViewModel.getEntries(), datesOfWeek);
	}

	@Test
	public void testGetIsReadOnly()
	{
		assertFalse(entryViewModel.getIsReadOnly());
	}

	@Test
	public void testSetIsReadOnly()
	{
		entryViewModel.setIsReadOnly(true);
		assertTrue(entryViewModel.getIsReadOnly());
	}

}
