package planner.app;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class SampleData {

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

}
