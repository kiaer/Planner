package planner.app;

import java.util.Date;
import java.util.GregorianCalendar;

public class SampleData {

	public static final String
		WRONG_EXCEPTION = "An OperationNotAllowedException should not have been thrown.",
		NO_EXCEPTION = "An OperationNotAllowedException should have been thrown.";

	GregorianCalendar calendar = new GregorianCalendar();

	public Activity sampleActivity() {
		return new Activity("Activity");
	}

	public Project sampleProject() {
		return new Project("Project");
	}

	public User sampleUser() {
		return new User("Kim", "1234", "kim@Mail.dk");
	}

	public Work sampleWork() {
		Date fromDate = calendar.getTime();
		Date toDate = new Date();
		toDate.setTime(calendar.getTime().getTime() + 1);
		return new Work(fromDate, toDate, ConstantActivities.NONE.getActivity());
	}

}
