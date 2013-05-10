package planner.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Planner {

	private boolean adminLoggedIn = false;
	private List<Project> projects = new ArrayList<Project>();
	private List<User> users = new ArrayList<User>();
	private DateServer dateServer = new DateServer();
	
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
			throw new OperationNotAllowedException("Create project",
					"Admin not logged in.");
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
			throw new OperationNotAllowedException("Register user", 
					"Cannot register new user if not admin.");
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void setUsers(List<User> user) {
		this.users = user;
	}
	public void setDateServer(DateServer dateServer) {
		this.dateServer = dateServer;

	}
	
	public Calendar getDate(){
		return dateServer.getDate();
	}

}