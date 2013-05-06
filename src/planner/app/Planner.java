package planner.app;

import java.util.ArrayList;
import java.util.List;

public class Planner {

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
	
	public void setUsers(List<User> user) {
		this.users = user;

	}

	public List<User> getUsers() {

		return users;
	}

	public void register(User user) throws OperationNotAllowedException {

		if (!adminLoggedIn())
			throw new OperationNotAllowedException("Register user",
					"Cannot register new user if not admin.");
		else {
			users.add(user);
		}

	}

	public List<Project> getProjects() {
		return projects;
	}

	public void createProject(Project project1)
			throws OperationNotAllowedException {
		if (adminLoggedIn) {
			projects.add(project1);
		} else
			throw new OperationNotAllowedException("Create project",
					"Admin not logged in.");

	}

	public void setProjects(List<Project> projects2) {
		this.projects = projects2;
	}

}
