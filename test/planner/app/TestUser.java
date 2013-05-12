package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

public class TestUser extends SampleData {

	@Test
	public void testActivity() {
		User user = new User("Username", null, null);
		//Initial values
		assertTrue(user.getActivities().isEmpty());
		//Assign activity
		Activity activity = sampleActivity();
		try {
			user.assignActivity(activity);
			assertTrue(user.getActivities().contains(activity));
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		//Duplicate activity error
		try {
			user.assignActivity(activity);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.USER_ASSIGN_ACT, e.getOperation());
			assertEquals(User.MSG_DUPE_ACT, e.getMessage());
			assertTrue(1 == user.getActivities().size());
		}
		//Unassign activity
		user.unassignActivity(activity);
		assertFalse(user.getActivities().contains(activity));
		//Null activity error
		try {
			user.assignActivity(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.USER_ASSIGN_ACT, e.getOperation());
			assertEquals(User.MSG_NULL_ACT, e.getMessage());
			assertTrue(user.getActivities().isEmpty());
		}
	}

	@Test
	public void testEmail() {
		String email = "Email";
		User user = new User("Username", null, email);
		//Initial values
		assertEquals(email, user.getEmail());
		//Change password
		String newEmail = "New email";
		user.setEmail(newEmail);
		assertEquals(newEmail, user.getEmail());
	}

	@Test
	public void testIsWorking() {
		User user = new User("Username", null, null);
		Date to = new Date(2), from = new Date(4);
		Work work1 = new Work(to, from, sampleActivity());
		//Initial values
		assertFalse(user.isWorking(to, from));
		//Is working
		try {
			user.registerWork(work1);
			assertFalse(user.isWorking(new Date(0), new Date(1)));
			assertTrue(user.isWorking(new Date(1), new Date(3)));
			assertTrue(user.isWorking(new Date(3), new Date(5)));
			assertFalse(user.isWorking(new Date(5), new Date(6)));
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
	}

	@Test
	public void testPassword() {
		String password = "Password";
		User user = new User("Username", password, null);
		//Initial values
		assertEquals(password, user.getPassword());
		//Change password
		String newPassword = "New password";
		user.setPassword(newPassword);
		assertEquals(newPassword, user.getPassword());
	}

	@Test
	public void testUsername() {
		String username = "Username";
		User user = new User(username, null, null);
		//Initial values
		assertEquals(username, user.getUsername());
		//Change username
		String newUsername = "New name";
		try {
			user.setUsername(newUsername);
			assertEquals(newUsername, user.getUsername());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		//Null username error
		username = user.getUsername();
		try {
			user.setUsername(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.USER_SET_USERNAME, e.getOperation());
			assertEquals(User.MSG_NULL_USERNAME, e.getMessage());
			assertEquals(username, user.getUsername());
		}
		//Constructor null username error correction
		user = new User(null, null, null);
		assertEquals(User.DEFAULT_USERNAME, user.getUsername());
	}

	@Test
	public void testWork() {
		User user = new User("Username", null, null);
		//Initial values
		assertTrue(user.getWorkSet().isEmpty());
		//Register work
		Work work = sampleWork();
		try {
			user.registerWork(work);
			assertTrue(user.getWorkSet().contains(work));
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		//Duplicate work error
		try {
			user.registerWork(work); 
			fail(NO_EXCEPTION); 
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.USER_REGISTER_WORK, e.getOperation());
			assertEquals(User.MSG_WORK_OVERLAP, e.getMessage());
			assertTrue(1 == user.getWorkSet().size());
		}
		//Removing work
		user.removeWork(work);
		assertFalse(user.getWorkSet().contains(work));
		//Null work error
		try {
			user.registerWork(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.USER_REGISTER_WORK, e.getOperation());
			assertEquals(User.MSG_NULL_WORK, e.getMessage());
			assertTrue(user.getWorkSet().isEmpty());
		}
		//Overload method
		try {
			user.registerWork(work.getFromDate(), work.getToDate(), work.getActivity());
			assertTrue(user.getWorkSet().contains(work));
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
	}

}