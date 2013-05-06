package planner.app;

public class Activity {

	private String activityName;
	private String activityDescription;
	
	public Activity(String activityName, String activityDescription) {
	
		this.setActivityDescription(activityDescription);
		this.setActivityName(activityName);
		
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
	
}
