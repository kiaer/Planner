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
	public void createProject() {
		
		Planner planner = new Planner();
		
		
		
		planner.adminLogIn("admin");
		assertTrue(planner.adminLoggedIn());
		
	}

}
