package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.ArrayList;

import ca.ulaval.glo4003.architecture_logicielle.model.ProjectEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.ProjectRepository;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;

public class ProjectRepositoryImpl implements ProjectRepository
{
	XMLProjectPersistance xmlProjectPersistance;
	
	public ProjectRepositoryImpl(){
		xmlProjectPersistance = XMLProjectPersistance.getInstance();
	}
	
	@Override
	public ArrayList<ProjectEntry> getAllProjects()
	{
		ArrayList<ProjectEntry> projects = new ArrayList<ProjectEntry>();
		ArrayList<ArrayList<String>> projectList = new ArrayList<ArrayList<String>>();
		
		projectList = xmlProjectPersistance.getAllProjects();
		
		for(ArrayList<String> tabProject: projectList){
			
			ProjectEntry project = new ProjectEntry();
			project.setId(Integer.parseInt(tabProject.get(0)));
			project.setName(tabProject.get(1));
			
			if(tabProject.size() > 2){
				int i=2;
				do{
					TaskEntry task = new TaskEntry();
					task.setId(Integer.parseInt(tabProject.get(i)));
					task.setName(tabProject.get(i+1));
					i+=2;
					project.addTask(task);

				}while(i<tabProject.size());
			}
			
			projects.add(project);
		}
		
		return projects;
	}

	@Override
	public ProjectEntry getProjectById(Integer id)
	{
		ArrayList<ProjectEntry> projects = getAllProjects();
		
		for (int i = 0; i < projects.size(); i++) {
				if (projects.get(i).getId() == id)
					return projects.get(i);
		}
		return null;
	}

	@Override
	public TaskEntry getTaskById(Integer id)
	{
		ArrayList<ProjectEntry> projects = getAllProjects();
		for (int i = 0; i < projects.size(); i++) {
			for (int j = 0; j < projects.get(i).getTasks().size(); j++) {
				if (projects.get(i).getTasks().get(j).getId() == id)
					return projects.get(i).getTasks().get(j);
			}
		}
		return null;
	}

	@Override
	public void addProject(ProjectEntry project)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProject(int id)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProject(int projectId, ProjectEntry project)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void addTaskToProject(int projectId, TaskEntry task)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTaskFromProject(int taskId, int projectId)
	{
		// TODO Auto-generated method stub

	}

}
