package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import java.util.List;
import java.util.ArrayList;

public class AssignedTasks {
	public List<String> tasks = new ArrayList<String>();

	public List<String> getTasks() {
		return tasks;
	}

	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}
}

