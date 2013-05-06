package planner.app;

import java.util.ArrayList;
import java.util.List;

public class Planner {

	private boolean adminLoggedIn = false;
	private List<Project> projects = new ArrayList<Project>();

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

	public void addActivity(String activity, ) {
		// TODO Auto-generated method stub
		
	}

}
