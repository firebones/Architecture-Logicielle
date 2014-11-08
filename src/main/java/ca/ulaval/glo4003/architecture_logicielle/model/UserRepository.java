package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository {
	public ArrayList<UserEntry> getAllUsers();
	public ArrayList<EmployeeEntry> getAllEmployees();
	public UserEntry getUserByEmail(String email);
	public void addUser(UserEntry user);
	public void updateUser(UserEntry user);
	public void deleteUser(UserEntry user);
	public void addTaskToUser(TaskEntry task, UserEntry user);
	public void setTasksToUser(List<TaskEntry> tasks, UserEntry user);
}
