package planner.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Planner {

	public static final String
			MSG_REGISTER_USER_AUTH = "Only admin can register users.",
			MSG_CREATE_PROJECT_AUTH = "Only admin can create projects.";

	public static DateServer dateServer = new DateServer();

	private boolean adminLoggedIn = false;
	private List<Project> projects = new ArrayList<Project>();
	private List<User> users = new ArrayList<User>();

	public boolean adminLoggedIn() {
		return adminLoggedIn;
	}

	public boolean adminLogIn(String password) {
		return adminLoggedIn = password.equals("admin");
	}

	public boolean adminLogOut() {
		return adminLoggedIn = false;
	}

	public void createProject(Project project) throws OperationNotAllowedException {
		if (adminLoggedIn)
			projects.add(project);
		else
			throw new OperationNotAllowedException(Operation.PLANNER_CREATE_PROJECT, MSG_CREATE_PROJECT_AUTH);
	}

	public List<Project> getProjects() {
		return projects;
	}

	public List<User> getUsers() {
		return users;
	}

	public void register(User user) throws OperationNotAllowedException {
		if (adminLoggedIn)
			users.add(user);
		else
			throw new OperationNotAllowedException(Operation.PLANNER_REGISTER_USER, MSG_REGISTER_USER_AUTH);
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void setUsers(List<User> user) {
		this.users = user;
	}

//	public static void setDateServer(DateServer dateServer) {
//		this.dateServer = dateServer;
//	}

	public static Calendar getCalendar() {
		return dateServer.getDate();
	}

	public static Date getDate() {
		return getCalendar().getTime();
	}

}