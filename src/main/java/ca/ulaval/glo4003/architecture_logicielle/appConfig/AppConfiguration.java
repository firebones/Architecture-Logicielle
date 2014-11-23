package ca.ulaval.glo4003.architecture_logicielle.appConfig;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ca.ulaval.glo4003.architecture_logicielle.dao.ProjectRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.dao.UserRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.dao.WeekEntryRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.model.DepartmentEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.ProjectEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.StateWeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntry;

@Configuration
public class AppConfiguration {
	// Project
	@Bean
	public ArrayList<ProjectEntry> getAllProjects() {
		return new ProjectRepositoryImpl().getAllProjects();
	}

	public ProjectEntry getProjectById(Integer id) {
		return new ProjectRepositoryImpl().getProjectById(id);
	}

	public TaskEntry getTaskById(Integer id) {
		return new ProjectRepositoryImpl().getTaskById(id);
	}

	public void addProject(ProjectEntry project) {
		new ProjectRepositoryImpl().addProject(project);
	}

	public void addTask(Integer projectId, TaskEntry task) {
		new ProjectRepositoryImpl().addTaskToProject(projectId, task);
	}

	public void updateProject(Integer projectId, ProjectEntry project) {
		new ProjectRepositoryImpl().updateProject(projectId, project);
	}

	public void updateTask(Integer taskId, TaskEntry task) {
		new ProjectRepositoryImpl().updateTask(taskId, task);
	}

	// Employee - User
	@Bean
	public ArrayList<UserEntry> getAllUsers() {
		return new UserRepositoryImpl().getAllUsers();
	}

	@Bean
	public ArrayList<EmployeeEntry> getAllEmployees() {
		return new UserRepositoryImpl().getAllEmployees();
	}

	public UserEntry getUserByEmail(String email) {
		return new UserRepositoryImpl().getUserByEmail(email);
	}

	public void addUser(UserEntry user) {
		new UserRepositoryImpl().addUser(user);
	}

	public void setTasksToUser(List<TaskEntry> tasks, UserEntry user) {
		new UserRepositoryImpl().setTasksToUser(tasks, user);
	}

	public List<TaskEntry> getTasksEmployee(String email){
		EmployeeEntry employee = (EmployeeEntry) getUserByEmail(email);
		
		return employee.getTasks();
	}
	
	public void assignedTaskToEmployee(String email, List<String> assignTasks){
		EmployeeEntry employee = (EmployeeEntry) getUserByEmail(email);
		
		List<TaskEntry> tasksList = new ArrayList<TaskEntry>();

		for (String taskId : assignTasks) {
			tasksList.add(getTaskById(Integer.parseInt(taskId)));
		}
		setTasksToUser(tasksList, employee);
	}
	
	public void deleteUser(UserEntry user) {
		new UserRepositoryImpl().deleteUser(user);
	}

	// Week Entry Repository

	@Bean
	public ArrayList<WeekEntry> getAllWeekEntries() {
		return new WeekEntryRepositoryImpl().getAllWeekEntries();
	}

	public WeekEntry getWeekEntryByEmailAndWeek(String email, Integer weekNumber, Integer yearNumber) {
		return new WeekEntryRepositoryImpl().getWeekEntryByEmailAndWeekAndYear(email,
				weekNumber, yearNumber);
	}
	
	public ArrayList<WeekEntry> getWeekEntryByEmail(String email) {
		return new WeekEntryRepositoryImpl().getWeekEntryByEmail(email);
	}
	
	public List<WeekEntry> getCurrentUserWeekEntries() {
		
		String currentUser = getCurrentUser();
		List<WeekEntry> userWeekEntries =  new ArrayList<WeekEntry>(); 
		
		for (WeekEntry weekEntry : new WeekEntryRepositoryImpl().getAllWeekEntries())
		{
			if (weekEntry.getEmail().equals(currentUser))
				userWeekEntries.add(weekEntry);
		}
		
		return userWeekEntries;
	}
	
	public String getCurrentUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return ((EmployeeEntry) auth.getPrincipal()).getEmail();
	}
	
	public WeekEntry getCurrentUserWeekEntry(int weekNumber, int yearNumber)
	{
		return getWeekEntryByEmailAndWeek(getCurrentUser(), weekNumber, yearNumber);
	}
	
	public void assignHoursToEmployee(int weekNumber, int yearNumber, List<Double> hours) {
		WeekEntry weekEntry = getCurrentUserWeekEntry(weekNumber, yearNumber);
		weekEntry.setHoursEntries(hours);
		updateWeekEntry(weekEntry);
	}
	
	public void assignKilometersToEmployee(int weekNumber, int yearNumber, List<Integer> kilometers) {
		WeekEntry weekEntry = getCurrentUserWeekEntry(weekNumber, yearNumber);
		weekEntry.setKilometersEntries(kilometers);
		updateWeekEntry(weekEntry);
	}
	
	public void assignExpensesToEmployee(int weekNumber, int yearNumber, List<Double> expenses) {
		WeekEntry weekEntry = getCurrentUserWeekEntry(weekNumber, yearNumber);
		weekEntry.setEmployeeExpensesEntries(expenses);
		updateWeekEntry(weekEntry);
	}

	// Department Entry Repository
	@Bean
	public ArrayList<DepartmentEntry> getAllDepartmentEntries() {
		return null;
	}

	public DepartmentEntry getDepartmentEntryByName(String name) {
		return new DepartmentEntry(name);
	}
	
	public void updateWeekEntry(WeekEntry weekEntry) {
		new WeekEntryRepositoryImpl().updateWeekEntry(weekEntry);
	}

	public void submitWeekEntry(Integer week, Integer year) {
		WeekEntry weekEntry = getWeekEntryByEmailAndWeek(getCurrentUser(), week, year);
		weekEntry.setState(StateWeekEntry.SUBMITTED);
		updateWeekEntry(weekEntry);
	}
}
