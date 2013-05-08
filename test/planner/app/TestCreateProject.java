package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class TestCreateProject {

	@Test
	public void testLogin() {
		Planner planner = new Planner();
		assertFalse(planner.adminLoggedIn());

		planner.adminLogIn("admin");
		assertTrue(planner.adminLoggedIn());

		planner.adminLogOut();
		assertFalse(planner.adminLoggedIn());
	}
	
	@Test
	public void testFailLogin(){
		Planner planner = new Planner();
		
		boolean login = planner.adminLogIn("hej");

		assertFalse(login);
		assertFalse(planner.adminLoggedIn());
	}

	@Test
	public void createProject() throws Exception {
		Planner planner = new Planner();
		
		planner.getProjects().isEmpty();
		assertFalse(planner.adminLoggedIn());
		
		planner.adminLogIn("admin");
		assertTrue(planner.adminLoggedIn());
		
		String projectName = "Software 1 projekt";
		User projectLeader = new User("Karl", "1234", "fk@mail.dk");

		planner.register(projectLeader);
		
		Project project = new Project(projectName, projectLeader);
		
		planner.createProject(project);
		
		List<Project> projects = planner.getProjects();
		assertEquals(1, projects.size());
		assertEquals(projectLeader, projects.get(0).getProjectLeader());
		assertEquals(projectName, projects.get(0).getName());	
	}
	
	@Test
	public void createProjectNotLoggedIn() throws Exception {		
		Planner planner = new Planner();

		planner.adminLogIn("admin");

		assertTrue(planner.adminLoggedIn());

		String projectName = "Software 1 projekt";
		User projectLeader = new User("Karl", "1234", "fk@mail.dk");

		planner.register(projectLeader);

		Project project = new Project(projectName, projectLeader);
	
		planner.adminLogOut();
	
		assertFalse(planner.adminLoggedIn());

		try{
			planner.createProject(project);
			fail("OperationNotAllowedException exception should have been thrown");
		} catch (OperationNotAllowedException e){			
			assertEquals("Create project", e.getOperation());
			assertEquals("Admin not logged in.", e.getMessage());
		}
		assertEquals(0, planner.getProjects().size());
	}

	@Test
	public void assignStartDate(){
		//TODO: assign dates to project.
		
		
		
		
	}

}
