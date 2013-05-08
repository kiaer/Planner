package planner.app;

import java.util.ArrayList;
import java.util.List;

public class Project {
	
	private String name;
	private User projectLeader;
	private List<Activity> activities = new ArrayList<Activity>();

	public Project(String name, User projectLeader) {
		setName(name);
		setProjectLeader(projectLeader);
	}

	//Untested
	public Project(String name) {
		this(name, null);
	}

	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public User getProjectLeader() {
		return projectLeader;
	}

	public String getName() {
		return name;
	}

	public boolean hasProjectLeader() {
		return projectLeader != null;
	}

	public void removeProjectLeader() {
		setProjectLeader(null);
	}

	public void setProjectLeader(User projectLeader) {
		this.projectLeader = projectLeader;
	}

	public void setName(String name) {
		this.name = name;
	}

}