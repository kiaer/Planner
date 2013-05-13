package planner.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Planner {

	public static final String
			ADMIN_PASSWORD = "Admin",
			MSG_CREATE_PROJECT_AUTH = "Only admin can create projects.",
			MSG_DUPE_PROJECT = "Project is already contained in projects.",
			MSG_DUPE_USER = "User is already contained in users.",
			MSG_INVALID_LOGIN = "Invalid admin password.",
			MSG_NULL_PROJECT = "Projects must not be null.",
			MSG_NULL_USER = "Users must not be null.",
			MSG_REGISTER_USER_AUTH = "Only admin can register users.",
			MSG_REMOVE_PROJ_AUTH = "Only admin can remove projects.",
			MSG_REMOVE_USER_AUTH = "Only admin can remove users.";

	public static DateServer dateServer = new DateServer();

	private boolean adminLoggedIn = false;
	private List<Project> projects = new ArrayList<Project>();
	private List<User> users = new ArrayList<User>(); 

	public boolean adminLoggedIn() {
		return adminLoggedIn;
	}

	public void adminLogin(String password) throws OperationNotAllowedException {
		if(password != null && password.equals(ADMIN_PASSWORD))
			adminLoggedIn = true;
		else
			throw new OperationNotAllowedException(Operation.PLANNER_LOGIN, MSG_INVALID_LOGIN);
	}

	public void adminLogout() {
		adminLoggedIn = false;
	}

	public void createProject(Project project) throws OperationNotAllowedException {
		if (adminLoggedIn) {
			if(project != null) {
				if(!projects.contains(project))
					projects.add(project);	
				else
					throw new OperationNotAllowedException(Operation.PLANNER_CREATE_PROJECT, MSG_DUPE_PROJECT);
			} else
				throw new OperationNotAllowedException(Operation.PLANNER_CREATE_PROJECT, MSG_NULL_PROJECT);
		} else
			throw new OperationNotAllowedException(Operation.PLANNER_CREATE_PROJECT, MSG_CREATE_PROJECT_AUTH);
	}

	public List<User> getAvailableUsers(Date fromDate, Date toDate) {
		List<User> availableUsers = new ArrayList<User>();
		for(User user : users) {
			if(!user.isWorking(fromDate, toDate))
				availableUsers.add(user);
		}
		return availableUsers; 
	}

	public static Calendar getCalendar() {
		return dateServer.getDate();
	}

	public static Date getDate() {
		return getCalendar().getTime();
	}

	public List<Project> getProjects() {
		return projects;
	}

	public List<User> getUsers() {
		return users;
	}

	public void registerUser(User user) throws OperationNotAllowedException {
		if (adminLoggedIn) {
			if(user != null) {
				if(!users.contains(user))
					users.add(user);	
				else
					throw new OperationNotAllowedException(Operation.PLANNER_REGISTER_USER, MSG_DUPE_USER);
			} else
				throw new OperationNotAllowedException(Operation.PLANNER_REGISTER_USER, MSG_NULL_USER);
		} else
			throw new OperationNotAllowedException(Operation.PLANNER_REGISTER_USER, MSG_REGISTER_USER_AUTH);
	}

	public void removeProject(Project project) throws OperationNotAllowedException {
		if(adminLoggedIn)
			projects.remove(project);
		else
			throw new OperationNotAllowedException(Operation.PLANNER_REMOVE_PROJ, MSG_REMOVE_PROJ_AUTH);
	}

	public void removeUser(User user) throws OperationNotAllowedException {
		if(adminLoggedIn)
			users.remove(user);
		else
			throw new OperationNotAllowedException(Operation.PLANNER_REMOVE_USER, MSG_REMOVE_USER_AUTH);
	}

}