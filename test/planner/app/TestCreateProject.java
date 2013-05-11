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
			assertEquals(Operation.PLANNER_CREATE_PROJECT, e.getOperation());
			assertEquals(Planner.MSG_CREATE_PROJECT_AUTH, e.getMessage());
		}
		assertEquals(0, planner.getProjects().size());
	}

	@Test
	public void createProjectNoLeader() throws OperationNotAllowedException{
		
		Planner planner = new Planner();
		
		planner.adminLogIn("admin");
		
		assertTrue(planner.adminLoggedIn());
		
		String projectName = "Software 1 Projekt";
		
		Project project = new Project(projectName);
		planner.createProject(project);
		
		
		assertEquals(1, planner.getProjects().size());
		assertEquals(projectName, planner.getProjects().get(0).getName());
		
	}
	
	
	@Test
	public void testSetName() throws OperationNotAllowedException{
		Planner planner = new Planner();
		
		planner.getProjects().isEmpty();
		assertFalse(planner.adminLoggedIn());
		
		planner.adminLogIn("admin");
		assertTrue(planner.adminLoggedIn());
		
		String projectName = "Software 1 projekt";
		User projectLeader = new User("Karl", "1234", "fk@mail.dk");

		planner.register(projectLeader);
		
		Project project = new Project(null, projectLeader);
		
		planner.createProject(project);
		List<Project> projects = planner.getProjects();
		project.setName(projectName);
		
		try {
			project.setName(null);
		} catch (OperationNotAllowedException e) {
			assertEquals(Project.MSG_NULL_NAME, e.getMessage());
			assertEquals(Operation.PROJ_SET_NAME, e.getOperation());
		}
		
		
		
		
	}

}