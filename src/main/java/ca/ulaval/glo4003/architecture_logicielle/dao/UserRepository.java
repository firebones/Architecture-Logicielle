package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.ArrayList;

import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;

public interface UserRepository {
	public ArrayList<UserEntry> getAllUsers();
	public UserEntry getUserByEmail(String email);
	public void addUser(UserEntry user);
	public void updateUser(UserEntry user);
	public void deleteUser(UserEntry user);
	public void addTaskToUser(TaskEntry task, UserEntry user);
}
