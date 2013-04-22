package planner.app;

import java.util.List;

public class Planner {

	private boolean adminLoggedIn = false;
	private List<Project> projects;

	public boolean adminLoggedIn() {
		return adminLoggedIn;

	}

	public boolean adminLogIn(String password) {

		return adminLoggedIn = password.equals("admin");

	}

	public boolean adminLogOut() {
		
		return adminLoggedIn = false;
		
	}

	public List<Project> getProjects() {
		// TODO Auto-generated method stub
		return projects;
	}
	
}
