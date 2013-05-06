package planner.app;

import java.util.ArrayList;
import java.util.List;

public class Project {
	
	private String projectName;
	private User projectLeader;
	private List<Activity> activities = new ArrayList<Activity>();

	public Project(String projectName, User projectLeader) {
	
		this.projectName = projectName;
		this.projectLeader = projectLeader;
	
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public User getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(User projectLeader) {
		this.projectLeader = projectLeader;
	}
	
	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	public List<Activity> getActivities() {
		return activities;
	}
	
	public void setNewProjectLeader(User newProjectLeader){
		this.projectLeader = newProjectLeader;
	}

}
