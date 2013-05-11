package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class TestCreateUser extends SampleData {

	@Test
	public void testRegisterUser() throws Exception {
		Planner planner = new Planner();

		// Step 1)
		List<User> users = planner.getUsers();
		assertEquals(0, users.size());

		// Step 2)
		planner.adminLogIn("admin");
		assertTrue(planner.adminLoggedIn());

		// Step 3)
		User user = new User("Karl", "1234", "fk@mail.dk");

		planner.register(user);
		users = planner.getUsers();

		// Step 4)
		assertEquals(1, users.size());

		User registeredUser = users.get(0);
		assertEquals("Karl", registeredUser.getUsername());
		assertEquals("1234", registeredUser.getPassword());
		assertEquals("fk@mail.dk", registeredUser.getEmail());
	}

	/**
	 * Tests that it is not allowed to register a user if not logged in as
	 * administrator.
	 * <ol>
	 * <li>Check that the administrator is not logged in
	 * <li>Create a user with an address and register the user
	 * <li>Check that an OperationNotAllowedException is thrown with the correct
	 * error message
	 * </ol>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegisterUserIfNotLoggedIn() throws Exception {
		Planner planner = new Planner();

		// Step 1)
		assertFalse(planner.adminLoggedIn());

		// Step 2)
		User user = new User("Karl", "1234", "fk@mail.dk");

		try {
			planner.register(user);
			fail("An OperationNotAllowedException should have been thrown");
		} catch (OperationNotAllowedException e) {
			// Step 3
			assertEquals(Planner.MSG_REGISTER_USER_AUTH, e.getMessage());
			assertEquals(Operation.PLANNER_REGISTER_USER, e.getOperation());
		}
	}

	@Test
	public void testNullUsername() {
		Planner planner = new Planner();
		planner.adminLogIn("admin");
		assertTrue(planner.adminLoggedIn());
		User user = new User("Karl", "1234", "fk@mail.dk");

		try {
			user.setUsername(null);
			fail("An OperationNotAllowedException should have been thrown");
		} catch (OperationNotAllowedException e) {
			// Step 3
			assertEquals(User.MSG_NULL_USERNAME, e.getMessage());
			assertEquals(Operation.USER_NULL_USERNAME, e.getOperation());
		}
	}

	@Test
	public void testDefaultUsername() throws OperationNotAllowedException {
		Planner planner = new Planner();
		planner.adminLogIn("admin");
		User user = new User(null, "1234", "fk@mail.dk");
		assertTrue(planner.adminLoggedIn());
		assertEquals(User.DEFAULT_USERNAME, user.getUsername());

	}

	@Test
	public void testAssignActivity() throws Exception {
		Planner planner = new Planner();
		planner.adminLogIn("admin");
		assertTrue(planner.adminLoggedIn());

		// Step 1
		User user = new User("Karl", "1234", "fk@mail.dk");
		assertEquals(user.getActivities().size(), 0);

		// Step 2
		Activity activity1 = new Activity("sten", "venstre ben");
		user.assignActivity(activity1);
		assertEquals(user.getActivities().size(), 1);
		Activity activity2 = user.getActivities().get(0);
		assertEquals("sten", activity2.getName());
		assertEquals("venstre ben", activity2.getDescription());
		double d = 10;

	}

	@Test
	public void testChangeUsername() throws OperationNotAllowedException {

		String name = "Carsten";
		String name2 = "Christian";

		assertTrue(planner.adminLoggedIn());

		User user = new User(name, "1234", "carsten@plannerteam.dk");
		assertEquals(name, user.getUsername());
		user.setUsername(name2);
		assertEquals(name2, user.getUsername());

	}

	@Test
	public void testChangeUsernameNull() throws OperationNotAllowedException {

		String name = "Carsten";

		assertTrue(planner.adminLoggedIn());

		User user = new User(name, "1234", "carsten@plannerteam.dk");
		assertEquals(name, user.getUsername());

		try {
			user.setUsername(null);
			fail("OperationNotAllowedException should've been thrown");
		} catch (OperationNotAllowedException e) {
			assertEquals(user.MSG_NULL_USERNAME, e.getMessage());
			assertTrue(user.getUsername() == name);
		}
	}
}