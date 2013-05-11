package planner.app;

public enum ConstantActivities {

	OTHER("Other"), SICKNESS("Sickness"), VACATION("Vacation"), NONE("Undefined");

	private Activity activity;

	private ConstantActivities(String name) {
		activity = new Activity(name);
	}

	public Activity getActivity() {
		return activity; 
	}

}
