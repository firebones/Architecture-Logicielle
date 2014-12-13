package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import java.util.List;

public class AssignedKilometers {

	public List<String> kilometers;
	public Boolean isNull;

	public List<String> getKilometers() {
		return kilometers;
	}

	public void setKilometers(List<String> kilometers) {
		this.kilometers = kilometers;
	}
	
	public Boolean getIsNull() {
		isNull = false;
		if (kilometers == null)
			isNull = true;
		return isNull;
	}
	
}
