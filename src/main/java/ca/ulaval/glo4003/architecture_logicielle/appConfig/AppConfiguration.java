package ca.ulaval.glo4003.architecture_logicielle.appConfig;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.ulaval.glo4003.architecture_logicielle.dao.ProjectRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.dao.UserRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.dao.WeekEntryRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.ProjectEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntry;

@Configuration
public class AppConfiguration {
	//Project
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
		
		//Employee - User
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
		
		public void deleteUser(UserEntry user) {
			new UserRepositoryImpl().deleteUser(user);
		}
		
		//Week Entry Repository
		
		@Bean
		public ArrayList<WeekEntry> getAllWeekEntries() {
			return new WeekEntryRepositoryImpl().getAllWeekEntries();
		}
		
		public WeekEntry getWeekEntryByEmailAndWeek(String email, String weekNumber) {
			return new WeekEntryRepositoryImpl().getWeekEntryByEmailAndWeek(email, weekNumber);
		}
		
		

}
