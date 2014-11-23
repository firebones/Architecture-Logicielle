package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import java.util.List;


public class AssignedHours {

	public List<String> hours;
	public Boolean isNull;

	public List<String> getHours() {
		return hours;
	}

	public void setHours(List<String> hours) {
		this.hours = hours;
	}
	
	public Boolean getIsNull() {
		isNull = false;
		if (hours == null)
			isNull = true;
		return isNull;
	}
}
