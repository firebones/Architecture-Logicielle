package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.ArrayList;

public interface WeekEntryRepository {
	public ArrayList<String> getKilometers();
	public ArrayList<String> getExpenses();
}