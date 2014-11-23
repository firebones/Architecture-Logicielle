package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import java.util.List;


public class AssignedExpenses {

	public List<String> expenses;
	public Boolean isNull;

	public List<String> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<String> expenses) {
		this.expenses = expenses;
	}
	
	public Boolean getIsNull() {
		isNull = false;
		if (expenses == null)
			isNull = true;
		return isNull;
	}
}
