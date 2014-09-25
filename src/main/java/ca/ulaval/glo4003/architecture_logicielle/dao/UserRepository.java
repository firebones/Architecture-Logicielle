package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;

public interface UserRepository {
	public List<UserEntry> getAllUser();
	public UserEntry getUserByEmail(String email);
	public void addUser(UserEntry user);
	public void updateUser(UserEntry user);
	public void deleteUser(String email);
	public void addTaskToUser(TaskEntry task, UserEntry user);
}
