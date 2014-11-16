package ca.ulaval.glo4003.architecture_logicielle.model;

public enum StateWeekEntry
{
	APPROVED("Approved"),
	SUBMITTED("Submitted"), 
	INPROGRESS("inProgress"), 
	REFUSED("Refused");

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
