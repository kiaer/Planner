package planner.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;

public class SampleData {

	GregorianCalendar calendar = new GregorianCalendar();
	Planner planner = new Planner();

	@Before
	public void setUp() throws Exception {
		
		planner.adminLogIn("admin"); 
		
		List<Project> projects = new ArrayList<Project>();
		User user = new User("Karl", "1234", "fk@mail.dk"); 
		planner.register(user);
		
		projects.add(new Project("Planner", user));

		for (int i = 1; i <= 10; i++) {
			projects.add(new Project("Project " + i, new User("User " + i, "1234", "fk@mail.dk")));
		}

		// planner.setProjects(projects);

	}

	public Activity createTempAct() {
		return new Activity("Activity");
	}

	public User createTempUser() {
		return new User("Adam", "1234", "mail@Mail.dk");
	}

	public Work createTempWork() {
		Date fromDate = calendar.getTime();
		Date toDate = new Date();
		toDate.setTime(calendar.getTime().getTime() + 1);
		return new Work(fromDate, toDate, ConstantActivities.NONE.getActivity());
	}

}
