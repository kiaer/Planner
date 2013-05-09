package planner.app;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String username, password, email;
	private List<Activity> activities = new ArrayList<Activity>();
	private List<Work> workList = new ArrayList<Work>();

	public User(String userName, String password, String email) {
		this.username = userName;
		this.password = password;
		this.email = email;
	}

	public void assignActivity(Activity activity) {
		activities.add(activity);
	}

	public boolean contains(String cprNumber) {
		return username.contains(cprNumber);
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

}