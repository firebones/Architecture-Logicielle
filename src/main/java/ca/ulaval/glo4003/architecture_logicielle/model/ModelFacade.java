package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.dao.DepartmentRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.dao.ProjectRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.dao.UserRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.dao.WeekEntryRepositoryImpl;
import ca.ulaval.glo4003.architecture_logicielle.util.MailService;
import ca.ulaval.glo4003.architecture_logicielle.util.MailServiceImpl;


public class ModelFacade
{
	UserRepository userRepository = new UserRepositoryImpl();
	WeekEntryRepository weekRepository = new WeekEntryRepositoryImpl();
	ProjectRepository projectRepository = new ProjectRepositoryImpl();
	DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
	MailService mailService = new MailServiceImpl();
	
	//WeekEntry treatment
	public void createWeekEntry(String email, int weekNumber, int year){
		
		EmployeeEntry user = (EmployeeEntry) userRepository.getUserByEmail(email);
		
		user.createWeekEntry(weekNumber, year);
	}
	
	public WeekEntry getWeekEntry(String email, int weekNumber,int yearNumber) {
		UserEntry user = userRepository.getUserByEmail(email);
		return ((EmployeeEntry)user).getWeekEntry(weekNumber, yearNumber);
	}
	
	public List<WeekEntry> getWeekEntries(String currentUserEmail) {

		UserEntry user = userRepository.getUserByEmail(currentUserEmail);
		return ((EmployeeEntry)user).getWeekEntries();
	}
	
	public ArrayList<WeekEntry> getAllWeekEntries() {
		return weekRepository.getAllWeekEntries();
	}
	
	public void assignHoursToEmployeeWeek(String email, int weekNumber, int yearNumber, List<Double> hours) {
		WeekEntry weekEntry = weekRepository.getWeekEntryByEmailAndWeekAndYear(email, weekNumber, yearNumber);
		EmployeeEntry user = (EmployeeEntry) userRepository.getUserByEmail(email);
		weekEntry = user.enterWorkHours(weekEntry, hours);
		weekRepository.updateWeekEntry(weekEntry);
	}
	
	public void assignKilometersToEmployeeWeek(String email, int weekNumber, int yearNumber, List<Integer> kilometers) {
		WeekEntry weekEntry = weekRepository.getWeekEntryByEmailAndWeekAndYear(email, weekNumber, yearNumber);
		EmployeeEntry user = (EmployeeEntry) userRepository.getUserByEmail(email);
		weekEntry = user.enterKilometers(weekEntry, kilometers);
		weekRepository.updateWeekEntry(weekEntry);
	}
	
	public void assignExpensesToEmployeeWeek(String email, int weekNumber, int yearNumber, List<Double> expenses) {
		WeekEntry weekEntry = weekRepository.getWeekEntryByEmailAndWeekAndYear(email, weekNumber, yearNumber);
		EmployeeEntry user = (EmployeeEntry) userRepository
				.getUserByEmail(email);
		weekEntry = user.enterExpenses(weekEntry, expenses);
		weekRepository.updateWeekEntry(weekEntry);
	}
	
	public void submitWeekEntry(String email, Integer week, Integer year) {
		WeekEntry weekEntry = weekRepository.getWeekEntryByEmailAndWeekAndYear(email, week, year);
		weekEntry.setState(StateWeekEntry.SUBMITTED);
		weekRepository.updateWeekEntry(weekEntry);
	}
	
	public void approvedWeekEntry(String email, Integer week, Integer year) {
		WeekEntry weekEntry = weekRepository.getWeekEntryByEmailAndWeekAndYear(email, week, year);
		weekEntry.setState(StateWeekEntry.APPROVED);
		weekRepository.updateWeekEntry(weekEntry);
	}

	public void deniedWeekEntry(String email, Integer week, Integer year) {
		WeekEntry weekEntry = weekRepository.getWeekEntryByEmailAndWeekAndYear(email, week, year);
		weekEntry.setState(StateWeekEntry.REFUSED);
		weekRepository.updateWeekEntry(weekEntry);
	}
	
	//Project Treatment
	
	public ArrayList<ProjectEntry> getAllProjects() {
		return projectRepository.getAllProjects();
	}
	
	public ProjectEntry getProjectById(Integer id) {
		return projectRepository.getProjectById(id);
	}
	
	public TaskEntry getTaskById(Integer id) {
		return projectRepository.getTaskById(id);
	}
	
	public void addProject(ProjectEntry project) {
		projectRepository.addProject(project);
	}
	
	public void addTask(Integer projectId, TaskEntry task) {
		projectRepository.addTaskToProject(projectId, task);
	}
	
	public void updateProject(Integer projectId, ProjectEntry project) {
		projectRepository.updateProject(projectId, project);
	}
	
	public void updateTask(Integer taskId, TaskEntry task) {
		projectRepository.updateTask(taskId, task);
	}
	
	public void assignedTaskToEmployee(String email, List<String> assignTasks) {
		EmployeeEntry employee = (EmployeeEntry) userRepository.getUserByEmail(email);

		List<TaskEntry> tasksList = new ArrayList<TaskEntry>();

		for (String taskId : assignTasks) {
			TaskEntry task = projectRepository.getTaskById(Integer
					.parseInt(taskId));
			tasksList.add(task);
		}
		userRepository.setTasksToUser(tasksList, employee);
	}
	
	// Employee - User Treatment
	
	public ArrayList<UserEntry> getAllUsers() {
		return userRepository.getAllUsers();
	}
	
	public ArrayList<EmployeeEntry> getAllEmployees() {
		return userRepository.getAllEmployees();
	}
	
	public UserEntry getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}
	
	public void addUser(UserEntry user) {
		userRepository.addUser(user);
	}
	
	public void updateUser(UserEntry user, Double rateHour) {
		
		if(user.getRole().toString() == "EMPLOYEE" || user.getRole().toString() == "MANAGER")
			((EmployeeEntry)user).setRateHour(rateHour);
		userRepository.updateUser(user);
	}
	
	public void deleteUser(UserEntry user) {
		userRepository.deleteUser(user);
	}
	
	public List<TaskEntry> getTasksEmployee(String email) {
		EmployeeEntry employee = (EmployeeEntry) userRepository.getUserByEmail(email);

		return employee.getTasks();
	}
	
	// Department Treatment
	
	public DepartmentEntry getDepartmentEntryByName(String name) {
		return new DepartmentEntry(name);
	}
	
	// Mail Service Treatment
	
	public void aprovedSend(String to) throws Exception{
		mailService.aprovedSend(to);
	}
	
	public void refusedSend(String to) throws Exception{
		mailService.refusedSend(to);
	}
}
