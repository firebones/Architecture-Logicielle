package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.model.AbstractFactory;
import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.PeriodPayBuilder;
import ca.ulaval.glo4003.architecture_logicielle.model.PeriodPayBuilderImpl;
import ca.ulaval.glo4003.architecture_logicielle.model.ProjectRepository;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserRepository;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry.Role;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntryRepository;

public class UserRepositoryImpl implements UserRepository
{
	XMLUserPersistance xmluserpersistance; 
	
	public UserRepositoryImpl(){
		
		xmluserpersistance = XMLUserPersistance.getInstance();
	}

	
	
	@Override
	public ArrayList<UserEntry> getAllUsers()
	{
		ArrayList<UserEntry> users = new ArrayList<UserEntry>();
		
		ArrayList<ArrayList<String>> userlist = xmluserpersistance.getAllUsers();
		
		for(ArrayList<String> tabuser: userlist){
			
			AbstractFactory factory = AbstractFactory.createFactory(tabuser.get(2));
			UserEntry user = factory.createUser();
			user.setName(tabuser.get(0));
			user.setEmail(tabuser.get(1));
			user.setHashedPassword(tabuser.get(3));
			
			if(tabuser.size() > 4){
				
				int j=4;
				do{
					ProjectRepository projects = new ProjectRepositoryImpl();
					TaskEntry task = projects.getTaskById(Integer.parseInt(tabuser.get(j)));
					((EmployeeEntry) user).assignTask(task);
					j++;
				}while(j<tabuser.size());
			}
			
			users.add(user);
		}

		return users;
	}

	@Override
	public ArrayList<EmployeeEntry> getAllEmployees()
	{
		ArrayList<EmployeeEntry> users = new ArrayList<EmployeeEntry>();
		
		ArrayList<ArrayList<String>> userlist = xmluserpersistance.getAllUsers();
		
		for(ArrayList<String> tabuser: userlist){
			
			AbstractFactory factory = AbstractFactory.createFactory(tabuser.get(2));
			UserEntry user = factory.createUser();
			user.setName(tabuser.get(0));
			user.setEmail(tabuser.get(1));
			user.setHashedPassword(tabuser.get(3));

			if(user.getRole() == Role.EMPLOYEE){
				if(tabuser.size() > 4){
					
					int j=4;
					
					do{
						
						ProjectRepository projects = new ProjectRepositoryImpl();
						TaskEntry task = projects.getTaskById(Integer.parseInt(tabuser.get(j)));
						((EmployeeEntry) user).assignTask(task);
						j++;
					}while(j<tabuser.size());
				}
			}
			
			users.add((EmployeeEntry)user);
			
		}

		return users;
	}

	@Override
	public UserEntry getUserByEmail(String email)
	{
		ArrayList<UserEntry> users = getAllUsers();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getEmail().equals(email)) {
				return users.get(i);
			}
		}
		return null;
	}

	@Override
	public void addUser(UserEntry user)
	{
		if (getUserByEmail(user.getEmail()) == null){
			
			ArrayList<String> userelement = getUserString(user);
			xmluserpersistance.addUser(userelement);
			
			WeekEntryRepository defaultPeriodPay = new WeekEntryRepositoryImpl();
			PeriodPayBuilder builder = new PeriodPayBuilderImpl();
			builder.setInformation(user.getEmail(), "41", "2014-10-05", "2014-10-11", false);
			List<Integer> kilometersEntries = new ArrayList<Integer>();
			//a gerer depuis le nombre de jour de la periode de paye donne par l'entreprise
			kilometersEntries.add(0);kilometersEntries.add(0);kilometersEntries.add(0);kilometersEntries.add(0);kilometersEntries.add(0);kilometersEntries.add(0);kilometersEntries.add(0);
			builder.setEmployeeKilometers(kilometersEntries);
			List<Double> employeeExpensesEntries = new ArrayList<Double>();
			//a gerer depuis le nombre de jour de la periode de paye donne par l'entreprise
			employeeExpensesEntries.add(0.0);employeeExpensesEntries.add(0.0);employeeExpensesEntries.add(0.0);employeeExpensesEntries.add(0.0);employeeExpensesEntries.add(0.0);employeeExpensesEntries.add(0.0);employeeExpensesEntries.add(0.0);
			builder.setEmployeeExpenses(employeeExpensesEntries);
			List<Double> hoursEntries = new ArrayList<Double>();
			//a gerer depuis le nombre de jour de la periode de paye donne par l'entreprise
			hoursEntries.add(0.0);hoursEntries.add(0.0);hoursEntries.add(0.0);hoursEntries.add(0.0);hoursEntries.add(0.0);hoursEntries.add(0.0);hoursEntries.add(0.0);
			builder.setEmployeeHours(hoursEntries);
			defaultPeriodPay.addEmployeePeriodPay(user, builder.getPeriodPayEntry());
		}
	}

	@Override
	public void updateUser(UserEntry user)
	{
		
		if (getUserByEmail(user.getEmail()) != null){
			
			ArrayList<String> userelement = getUserString(user);
			xmluserpersistance.updateUser(userelement);
		}

	}

	@Override
	public void deleteUser(UserEntry user)
	{
		
		if (getUserByEmail(user.getEmail()) != null){
		
			xmluserpersistance.deleteUser(user.getEmail());		
		}
	}

	@Override
	public void addTaskToUser(TaskEntry task, UserEntry user)
	{
		
		if (getUserByEmail(user.getEmail()) != null){

			ArrayList<String> userElement = getUserString(user);
			ArrayList<String> taskElement = getTaskString(task);
			xmluserpersistance.addTaskToUser(taskElement, userElement);
		}
		
	}

	@Override
	public void setTasksToUser(List<TaskEntry> tasks, UserEntry user)
	{
		ArrayList<ArrayList<String>> tasksElements = new ArrayList<ArrayList<String>>();
		
		if (getUserByEmail(user.getEmail()) != null){
		
			ArrayList<String> userElement = getUserString(user);
			
			for(TaskEntry task : tasks){
				ArrayList<String> taskElement = getTaskString(task);
				tasksElements.add(taskElement);
			}
			xmluserpersistance.setTasksToUser(tasksElements, userElement);
		}
	}


	private ArrayList<String> getUserString(UserEntry user) {
		ArrayList<String> userElement = new ArrayList<String>();
		
		userElement.add(0, user.getName());
		userElement.add(1, user.getEmail());
		userElement.add(2, user.getRole().toString());
		userElement.add(3, user.getHashedPassword());		
		
		if (user instanceof EmployeeEntry && ((EmployeeEntry) user).getTasks().size() > 0) {
			for (int i = 4; i < ((EmployeeEntry) user).getTasks().size(); i++) {
				TaskEntry task = ((EmployeeEntry) user).getTasks().get(i);
				userElement.add(i, task.getId().toString());
			}
		}
		
		return userElement;
	}
	
	private ArrayList<String> getTaskString(TaskEntry task){
		ArrayList<String> taskElement = new ArrayList<String>();
		
		taskElement.add(0, task.getId().toString());
		taskElement.add(1, task.getName());;
		
		return taskElement;
	}
}
