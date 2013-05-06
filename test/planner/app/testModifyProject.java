package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class testModifyProject extends SampleData {
	
	@Test
	public void testChangeProjectLeader(){
		
		planner.adminLogIn("admin");
		assertTrue(planner.adminLoggedIn());
		
		User projectLeader = new User("Leder", "gammel", "gammel@gammel.dk");
		User newProjectLeader = new User("Den nye", "ny", "ny@ny.dk");
		
		String projectName = "Software Eng 1";
		
		Project project = new Project(projectName, projectLeader);
		
		assertEquals(projectLeader, project.getProjectLeader());
		
		project.setNewProjectLeader(newProjectLeader);
		
		assertFalse(projectLeader == project.getProjectLeader());
		assertEquals(newProjectLeader, project.getProjectLeader());
		
	}
	
	@Test
	public void testChangeStartDate(){
		//TODO: make it possible to change startdate
		
	}
	
}
