package planner.app;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String password;
	private String userName;
	private String email;
	private List<Activity> activities = new ArrayList<Activity>();

	public User(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;

	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {

		return password;
	}

	public String getEmail() {

		return email;
	}

	public boolean contains(String cprNumber) {
		return userName.contains(cprNumber);

	}

	public void assignActivity(Activity activity) {
		activities.add(activity);
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

}
