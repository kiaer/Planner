package planner.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class User {

	public static final String
			DEFAULT_USERNAME = "Unnamed",
			MSG_NULL_USERNAME = "Username must not be null.";

	private String username, password, email;
	private List<Activity> activities = new ArrayList<Activity>();
	private List<Work> workList = new ArrayList<Work>();
	private Calendar startWork;
	private Calendar endWork;

	public User(String username, String password, String email) {
		try {
			setUsername(username);
		} catch (OperationNotAllowedException e) {
			e.printStackTrace();
			this.username = DEFAULT_USERNAME;
		}
		this.password = password;
		this.email = email;
	}

	public void assignActivity(Activity activity) {
		activities.add(activity);
	}

	public boolean contains(String userName) {
		return username.contains(userName);
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

	public List<Work> getWorkList() {
		return workList;
	}

	public void registerWork(Date fromDate, Date toDate, Activity activity) {
		workList.add(new Work(fromDate, toDate, activity));
	}

	public void removeWork(Work work) {
		workList.remove(work);
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	public void setUsername(String username) throws OperationNotAllowedException {
		if(username != null)
			this.username = username;
		else
			throw new OperationNotAllowedException(Operation.USER_NULL_USERNAME, MSG_NULL_USERNAME);
	}

	//<<<<<<< HEAD
//		private boolean isWorking() {
	//
//			return startWork != null;
//		}
	//
//		void setStartWork(Calendar date) {
//			startWork = date;
	//
//		}
	//
//		public Calendar getStartWork() {
//			return startWork;
//		}
	//
//		public Calendar DaysWorking(int days) {
//			if (!isWorking()) {
//				return null;
//			}
//			Calendar date = getStartWork();
//			endWork = new GregorianCalendar();
//			endWork.setTime(date.getTime());
//			endWork.add(Calendar.DAY_OF_YEAR, days);
//			return endWork;
//		}
	//=======



}