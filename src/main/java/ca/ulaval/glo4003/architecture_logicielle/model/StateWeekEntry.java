package ca.ulaval.glo4003.architecture_logicielle.model;

public enum StateWeekEntry
{
	APPROVED("STATE_APPROVED"),
	SUBMITTED("STATE_SUBMITTED"), 
	INPROGRESS("STATE_INPROGRESS"), 
	REFUSED("STATE_REFUSED");

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
