package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.ArrayList;

public interface ProjectRepository {
	public ArrayList<ProjectEntry> getAllProjects();
	public ProjectEntry getProjectById(Integer id);
	public TaskEntry getTaskById(Integer id);
	public void addProject(ProjectEntry project);
	public void deleteProject(int id);
	public void updateProject(int projectId, ProjectEntry project);
	public void addTaskToProject(int projectId, TaskEntry task);
	public void removeTaskFromProject(int taskId, int projectId);
}
