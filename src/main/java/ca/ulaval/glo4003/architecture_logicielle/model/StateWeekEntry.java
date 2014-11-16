package ca.ulaval.glo4003.architecture_logicielle.model;

public enum StateWeekEntry
{
	APPROVED("APPROVED"),
	SUBMITTED("SUBMITTED"), 
	INPROGRESS("INPROGRESS"), 
	REFUSED("REFUSED");

	private final String stateString;

	private StateWeekEntry(String stateString)
	{
		this.stateString = stateString;
	}

	public String getStateWeekEntry()
	{
		return stateString;
	}

}
