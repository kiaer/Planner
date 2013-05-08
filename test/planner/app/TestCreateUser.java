package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;


public class TestCreateUser {

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
	 * 	<li>Check that the administrator is not logged in
	 * 	<li>Create a user with an address and register the user
	 * 	<li>Check that an OperationNotAllowedException is thrown with the correct error message
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
			assertEquals("Cannot register new user if not admin.", e
					.getMessage());
			assertEquals("Register user", e.getOperation());
		}
	}
	
}