package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.appConfig.AppConfiguration;

public class ModelFacade
{

	private AppConfiguration configuration = new AppConfiguration();
	
	public LinkedList<String> getEmployeeString(UserEntry user){
		return null;
		
	}
	
	public UserEntry getUserEntry(LinkedList<String> entry){
		return null;
		
	}
	
	public void assignTasks(UserEntry user, LinkedList<TaskEntry> task){
		
	}
	
	public UserEntry getUser(String email){
		return configuration.getUserByEmail(email);
	}
	
	public void deleteUser(UserEntry user){
		configuration.deleteUser(user);
	}
	
	public List<TaskEntry> getTaskEmployee(String email){
		EmployeeEntry employee = (EmployeeEntry) configuration.getUserByEmail(email);
		
		return employee.getTasks();
	}
	
	public void assignedTaskToEmployee(String email, List<String> assignTasks){
		EmployeeEntry employee = (EmployeeEntry) configuration.getUserByEmail(email);
		
		List<TaskEntry> tasksList = new ArrayList<TaskEntry>();

		for (String taskId : assignTasks) {
			tasksList.add(configuration.getTaskById(Integer.parseInt(taskId)));
		}
		configuration.setTasksToUser(tasksList, employee);
	}
	
	public ArrayList<EmployeeEntry> getAllEmployees(){
		ArrayList<EmployeeEntry> list = configuration.getAllEmployees();
		return list;
	}
	
	public ArrayList<ProjectEntry> getAllProjects(){
		
		ArrayList<ProjectEntry> listProject = configuration.getAllProjects();
		
		return listProject;
	}
	
	public ProjectEntry getProject(Integer id){
		
		ProjectEntry project = configuration.getProjectById(id);
		
		return project;
	}
	
	public void addNewProject(ProjectEntry newProject){
		configuration.addProject(newProject);
	}
	
	public void updateProject(Integer id, ProjectEntry updatedProject){
		
		configuration.updateProject(id, updatedProject);
	}
	
	public TaskEntry getTask(Integer id){
		
		TaskEntry task = configuration.getTaskById(id);
		
		return task;
	}
	
	public void addTask(Integer id, TaskEntry newTask){
		configuration.addTask(id, newTask);
	}
	
	public void updateTask(Integer id, TaskEntry updatedTask){
		
		configuration.updateTask(id, updatedTask);
	}
		
	

}
