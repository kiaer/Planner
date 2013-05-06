package planner.app;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class SampleData {
	
	Planner planner = new Planner();
	
	@Before
	public void setUp() throws Exception{
		List<Project> projects = new ArrayList<Project>();
		projects.add(new Project("Planner", "Kevin"));
		for(int i = 1; i <= 10; i++){
			projects.add(new Project("Project " + i, "Leader" + i));
		}
		
		planner.setProjects(projects);
	
	}

	
	
}
