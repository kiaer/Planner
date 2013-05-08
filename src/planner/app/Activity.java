package planner.app;

public class Activity {

	private String activityName, activityDescription;

	public Activity(String activityName, String activityDescription) {
		this.setActivityDescription(activityDescription);
		this.setActivityName(activityName);	
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

}