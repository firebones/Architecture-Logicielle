package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import java.util.List;

public class AssignTasksViewModel {
	
	public String userId;
	public List<ProjectViewModel> projects;

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public List<ProjectViewModel> getProjects() {
		return projects;
	}
	
	public void setProjects(List<ProjectViewModel> projects) {
		this.projects = projects;
	}
}
