package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestPlanner extends SampleData{

	@Test
	public void testLogin() {
		Planner planner = new Planner();
		//Initial values
		assertFalse(planner.adminLoggedIn());
		//Login
		try {
			planner.adminLogin(Planner.ADMIN_PASSWORD);
			assertTrue(planner.adminLoggedIn());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		//Logout
		planner.adminLogout();
		assertFalse(planner.adminLoggedIn());
		//Wrong login error
		try {
			planner.adminLogin("Wrong password");
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PLANNER_LOGIN, e.getOperation());
			assertEquals(Planner.MSG_INVALID_LOGIN, e.getMessage());
			assertFalse(planner.adminLoggedIn());
		}
		//Null password login error
		try {
			planner.adminLogin(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PLANNER_LOGIN, e.getOperation());
			assertEquals(Planner.MSG_INVALID_LOGIN, e.getMessage());
			assertFalse(planner.adminLoggedIn());
		}
	}

	@Test
	public void testProjects() {
		Planner planner = new Planner();
		//Initial values
		assertTrue(planner.getProjects().isEmpty());
		//Create project without login error
		Project project = sampleProject();
		try {
			planner.createProject(project);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PLANNER_CREATE_PROJECT, e.getOperation());
			assertEquals(Planner.MSG_CREATE_PROJECT_AUTH, e.getMessage());
			assertTrue(planner.getProjects().isEmpty());
		}
		//Create project
		try {
			planner.adminLogin(Planner.ADMIN_PASSWORD);
			planner.createProject(project);
			assertTrue(planner.getProjects().contains(project));
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		//Duplicate project error
		try {
			planner.createProject(project);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PLANNER_CREATE_PROJECT, e.getOperation());
			assertEquals(Planner.MSG_DUPE_PROJECT, e.getMessage());
			assertTrue(1 == planner.getProjects().size());
		}
		//Null project error
		try {
			planner.createProject(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PLANNER_CREATE_PROJECT, e.getOperation());
			assertEquals(Planner.MSG_NULL_PROJECT, e.getMessage());
			assertTrue(1 == planner.getProjects().size());
		}
		//Remove projects without login error
		planner.adminLogout();
		try {
			planner.removeProject(project);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PLANNER_REMOVE_PROJ, e.getOperation());
			assertEquals(Planner.MSG_REMOVE_PROJ_AUTH, e.getMessage());
			assertTrue(1 == planner.getProjects().size());
		}
		//Remove projects
		try {
			planner.adminLogin(Planner.ADMIN_PASSWORD);
			planner.removeProject(project);
			assertFalse(planner.getProjects().contains(project));
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
	}

	@Test
	public void testUsers() {
		Planner planner = new Planner();
		//Initial values
		assertTrue(planner.getUsers().isEmpty());
		//Register user without login error
		User user = sampleUser();
		try {
			planner.registerUser(user);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PLANNER_REGISTER_USER, e.getOperation());
			assertEquals(Planner.MSG_REGISTER_USER_AUTH, e.getMessage());
			assertTrue(planner.getUsers().isEmpty());
		}
		//Register user
		try {
			planner.adminLogin(Planner.ADMIN_PASSWORD);
			planner.registerUser(user);
			assertTrue(planner.getUsers().contains(user));
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		//Duplicate user error
		try {
			planner.registerUser(user);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PLANNER_REGISTER_USER, e.getOperation());
			assertEquals(Planner.MSG_DUPE_USER, e.getMessage());
			assertTrue(1 == planner.getUsers().size());
		}
		//Null user error
		try {
			planner.registerUser(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PLANNER_REGISTER_USER, e.getOperation());
			assertEquals(Planner.MSG_NULL_USER, e.getMessage());
			assertTrue(1 == planner.getUsers().size());
		}
		//Remove users without login error
		planner.adminLogout();
		try {
			planner.removeUser(user);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PLANNER_REMOVE_USER, e.getOperation());
			assertEquals(Planner.MSG_REMOVE_USER_AUTH, e.getMessage());
			assertTrue(1 == planner.getUsers().size());
		}
		//Remove users
		try {
			planner.adminLogin(Planner.ADMIN_PASSWORD);
			planner.removeUser(user);
			assertFalse(planner.getUsers().contains(user));
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
	}

}