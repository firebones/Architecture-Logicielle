package ca.ulaval.glo4003.architecture_logicielle.appConfig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import ca.ulaval.glo4003.architecture_logicielle.model.ProjectRepository;
import ca.ulaval.glo4003.architecture_logicielle.model.StateWeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserRepository;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntryRepository;
import ca.ulaval.glo4003.architecture_logicielle.util.MailService;
import ca.ulaval.glo4003.architecture_logicielle.util.MailServiceImpl;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.CreatedWeekNumber;

@Configuration
public class AppConfiguration {
	UserRepository userRepository = new UserRepositoryImpl();
	WeekEntryRepository weekRepository = new WeekEntryRepositoryImpl();
	ProjectRepository projectRepository = new ProjectRepositoryImpl();
	MailService mailService = new MailServiceImpl();

	public String getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		return ((UserEntry) auth.getPrincipal()).getEmail();
	}

	public WeekEntry getUserWeekEntry(String email, int weekNumber,
			int yearNumber) {
		return weekRepository.getWeekEntryByEmailAndWeekAndYear(email,
				weekNumber, yearNumber);
	}

	public List<WeekEntry> getCurrentUserWeekEntries() {

		String currentUser = getCurrentUser();
		List<WeekEntry> userWeekEntries = new ArrayList<WeekEntry>();

		for (WeekEntry weekEntry : new WeekEntryRepositoryImpl()
				.getAllWeekEntries()) {
			if (weekEntry.getEmail().equals(currentUser))
				userWeekEntries.add(weekEntry);
		}

		return userWeekEntries;
	}

	// Week Entry Repository

	@Bean
	public ArrayList<WeekEntry> getAllWeekEntries() {
		return new WeekEntryRepositoryImpl().getAllWeekEntries();
	}

	public void createWeekEntry(String email, int weekNumber, int year) {
		WeekEntry weekEntry = new WeekEntry();
		weekEntry.setEmail(email);
		weekEntry.setWeekNumber(weekNumber);
		weekEntry.setYearNumber(year);
		weekRepository.addWeekEntry(weekEntry);
	}

	public ArrayList<WeekEntry> getWeekEntryByEmail(String email) {
		return new WeekEntryRepositoryImpl().getWeekEntryByEmail(email);
	}

	public void assignHoursToEmployeeWeek(String email, int weekNumber,
			int yearNumber, List<Double> hours) {
		WeekEntry weekEntry = weekRepository.getWeekEntryByEmailAndWeekAndYear(
				getCurrentUser(), weekNumber, yearNumber);
		EmployeeEntry user = (EmployeeEntry) userRepository
				.getUserByEmail(getCurrentUser());
		weekEntry = user.enterWorkHours(weekEntry, hours);
		updateWeekEntry(weekEntry);
	}

	public void assignKilometersToEmployeeWeek(String email, int weekNumber,
			int yearNumber, List<Integer> kilometers) {
		WeekEntry weekEntry = weekRepository.getWeekEntryByEmailAndWeekAndYear(
				getCurrentUser(), weekNumber, yearNumber);
		EmployeeEntry user = (EmployeeEntry) userRepository
				.getUserByEmail(getCurrentUser());
		weekEntry = user.enterKilometers(weekEntry, kilometers);
		updateWeekEntry(weekEntry);
	}

	public void assignExpensesToEmployeeWeek(String email, int weekNumber,
			int yearNumber, List<Double> expenses) {
		WeekEntry weekEntry = weekRepository.getWeekEntryByEmailAndWeekAndYear(
				getCurrentUser(), weekNumber, yearNumber);
		EmployeeEntry user = (EmployeeEntry) userRepository
				.getUserByEmail(getCurrentUser());
		weekEntry = user.enterExpenses(weekEntry, expenses);
		updateWeekEntry(weekEntry);
	}

	// Project
	@Bean
	public ArrayList<ProjectEntry> getAllProjects() {
		return projectRepository.getAllProjects();
	}

	public ProjectEntry getProjectById(Integer id) {
		return projectRepository.getProjectById(id);
	}

	public TaskEntry getTaskById(Integer id) {
		return projectRepository.getTaskById(id);
	}

	public void assignedTaskToEmployee(String email, List<String> assignTasks) {
		EmployeeEntry employee = (EmployeeEntry) getUserByEmail(email);

		List<TaskEntry> tasksList = new ArrayList<TaskEntry>();

		for (String taskId : assignTasks) {
			TaskEntry task = projectRepository.getTaskById(Integer
					.parseInt(taskId));
			tasksList.add(task);
		}
		userRepository.setTasksToUser(tasksList, employee);
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

	// Employee - User
	@Bean
	public ArrayList<UserEntry> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Bean
	public ArrayList<EmployeeEntry> getAllEmployees() {
		return userRepository.getAllEmployees();
	}

	public UserEntry getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	public void addUser(UserEntry user) {
		userRepository.addUser(user);
	}

	public List<TaskEntry> getTasksEmployee(String email) {
		EmployeeEntry employee = (EmployeeEntry) getUserByEmail(email);

		return employee.getTasks();
	}

	public void deleteUser(UserEntry user) {
		new UserRepositoryImpl().deleteUser(user);
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

	public void submitWeekEntry(String email, Integer week, Integer year) {
		WeekEntry weekEntry = weekRepository.getWeekEntryByEmailAndWeekAndYear(
				email, week, year);
		weekEntry.setState(StateWeekEntry.SUBMITTED);
		updateWeekEntry(weekEntry);
	}

	public void approvedWeekEntry(String email, Integer week, Integer year) {
		WeekEntry weekEntry = weekRepository.getWeekEntryByEmailAndWeekAndYear(
				email, week, year);
		weekEntry.setState(StateWeekEntry.APPROVED);
		updateWeekEntry(weekEntry);
	}

	public void deniedWeekEntry(String email, Integer week, Integer year) {
		WeekEntry weekEntry = weekRepository.getWeekEntryByEmailAndWeekAndYear(
				email, week, year);
		weekEntry.setState(StateWeekEntry.REFUSED);
		updateWeekEntry(weekEntry);

	}
	
	public void createWeekEntry(CreatedWeekNumber createdWeekNumber){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = ((UserEntry) auth.getPrincipal()).getEmail();
		int year = getCurrentYear();
		int currentWeek = getCurrentWeekInYear();
		if (createdWeekNumber.getWeekNumber() == null) {
//			return "redirect:/weekEntriesList";
		}
		int weekNumber = createdWeekNumber.getWeekNumber();

		String intervalleString = ca.ulaval.glo4003.architecture_logicielle.util.Configuration.getConfig("FURTHER_WK_CREATION");
		int intervalle = 0;
		try {
			intervalle = Integer.parseInt(intervalleString);
		} catch (Exception e) {

		}
		if (weekNumber < currentWeek - intervalle) {
			year++;
		} else if (weekNumber > currentWeek + intervalle) {
			year--;
		}
		createWeekEntry(email, weekNumber, year);
	}
	
	
	public Map<String, Integer> getNoCreatedWeeks(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = ((UserEntry) auth.getPrincipal()).getEmail();

		Map<String, Integer> noCreatedWeeks = new LinkedHashMap<>();
		

		String intervalleString = ca.ulaval.glo4003.architecture_logicielle.util.Configuration.getConfig("FURTHER_WK_CREATION");
		int intervalle = 0;
		try {
			intervalle = Integer.parseInt(intervalleString);
		} catch (Exception e) {

		}

		if (intervalle > 25) {
			intervalle = 25;
		}
		int weekNb = getCurrentWeekInYear() - intervalle;
		int year = getCurrentYear();
		int weeksInCurrentYear = getNumberWeeksInYear(year);
		if (weekNb <= 0) {
			year--;
			weeksInCurrentYear = getNumberWeeksInYear(year);
			weekNb += weeksInCurrentYear;
		}
		for (int i = 0; i <= intervalle * 2; i++) {
			if (getUserWeekEntry(email, weekNb, year) == null) {
				noCreatedWeeks.put("" + weekNb, weekNb);
			}
			weekNb++;
			if (weekNb > weeksInCurrentYear) {
				year++;
				weekNb = 1;
				weeksInCurrentYear = getNumberWeeksInYear(year);
			}
		}
		
		return noCreatedWeeks;
	}
	
	public int getCurrentWeekInYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}
	
	public int getNumberWeeksInYear(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.set(year, Calendar.DECEMBER, 31);
		int weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
		while (weekNumber <= 1) {
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
		}
		return weekNumber;
	}
	
	public void aprovedSend(String to) throws Exception{
		mailService.aprovedSend(to);
	}
	
	public void refusedSend(String to) throws Exception{
		mailService.refusedSend(to);
	}
}
