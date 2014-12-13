package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
					task.setRate(Double.parseDouble(tabProject.get(i+2)));
					i+=3;
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
				if (projects.get(i).getTasks().get(j).getId() == id){
					TaskEntry temp = projects.get(i).getTasks().get(j);
					temp.setHours(8.5);
					return temp;
				}
			}
		}
		return null;
	}

	@Override
	public void addProject(ProjectEntry project)
	{
		if (getTaskById(project.getId()) == null){
			
			project.setId(GetNextProjectId());
			ArrayList<String> projectelement = getProjectString(project);
			xmlProjectPersistance.addProject(projectelement);
		}

	}
	
	@Override
	public void deleteProject(int id)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProject(int projectId, ProjectEntry project)
	{
		if (project != null){
			
			ArrayList<String> projectelement = getProjectString(project);
			xmlProjectPersistance.updateProject(projectelement);
		}
	}
	
	@Override
	public void addTaskToProject(int projectId, TaskEntry task)
	{
		if (getTaskById(task.getId()) == null){
			
			task.setId(GetNextTaskId());
			ArrayList<String> taskelement = getTaskString(task);
			xmlProjectPersistance.addTaskToProject(projectId, taskelement);
		}
	}
	
	@Override
	public void updateTask(Integer taskId, TaskEntry task)
	{
		if (task != null){
			
			ArrayList<String> taskelement = getTaskString(task);
			xmlProjectPersistance.updateTask(taskelement);
		}
	}


	@Override
	public void removeTaskFromProject(int taskId, int projectId)
	{
		// TODO Auto-generated method stub

	}
	
	private ArrayList<String> getProjectString(ProjectEntry project){
		ArrayList<String> projectElement = new ArrayList<String>();
		
		projectElement.add(0, project.getId().toString());
		projectElement.add(1, project.getName());
		
		return projectElement;
	}
	
	private ArrayList<String> getTaskString(TaskEntry task){
		ArrayList<String> taskElement = new ArrayList<String>();
		
		taskElement.add(0, task.getId().toString());
		taskElement.add(1, task.getName());
		taskElement.add(2, task.getRate().toString());
		
		return taskElement;
	}
	
	private Integer GetNextProjectId()
	{
		List<Integer> ids = new ArrayList<Integer>();
		for (ProjectEntry project : getAllProjects())
		{
			ids.add(project.getId());
		}
		
		return Collections.max(ids) + 1;
	}
	
	private Integer GetNextTaskId()
	{
		List<Integer> ids = new ArrayList<Integer>();
		for (ProjectEntry project : getAllProjects())
		{
			for (TaskEntry task : project.getTasks())
			{
				ids.add(task.getId());
			}
		}
		
		return Collections.max(ids) + 1;
	}

}
