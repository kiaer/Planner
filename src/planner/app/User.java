package planner.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;



public class User {

	private String username, password, email;
	private List<Activity> activities = new ArrayList<Activity>();
	private List<Work> workList = new ArrayList<Work>();
	private Calendar startWork;
	private Calendar endWork;

	public User(String userName, String password, String email) {
		this.username = userName;
		this.password = password;
		this.email = email;
	}

	public void assignActivity(Activity activity) {
		activities.add(activity);
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

	private boolean isWorking() {

		return startWork != null;
	}

	void setStartWork(Calendar date) {
		startWork = date;

	}

	public Calendar getStartWork() {
		return startWork;
	}

	public Calendar DaysWorking(int days) {
		if (!isWorking()) {
			return null;
		}
		Calendar date = getStartWork();
		endWork = new GregorianCalendar();
		endWork.setTime(date.getTime());
		endWork.add(Calendar.DAY_OF_YEAR, days);
		return endWork;
	}

}