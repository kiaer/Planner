package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

public class TestActivity extends SampleData {

	@Test
	public void testAllWorkHours() {
		double allHours = 0;
		Activity activity = new Activity("Activity", allHours);
		// Initial values
		assertTrue(allHours == activity.getWorkHours());
		// Allocated work hours change
		double newAllHours = 5;
		try {
			activity.setAllocatedWorkHours(newAllHours);
			assertTrue(newAllHours == activity.getAllocatedWorkHours());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		// Negative allocated work hours error
		allHours = activity.getAllocatedWorkHours();
		try {
			activity.setAllocatedWorkHours(-1);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.ACT_SET_ALL_HOURS, e.getOperation());
			assertEquals(Activity.MSG_NEG_ALL_HOURS, e.getMessage());
			assertTrue(allHours == activity.getAllocatedWorkHours());
		}
		// Constructor negative allocated work hours error correction
		activity = new Activity("Activity", -1);
		assertTrue(Activity.DEFAULT_ALL_WORK_HOURS == activity.getAllocatedWorkHours());
		// Constructor default value
		activity = new Activity("Activtiy");
		assertTrue(Activity.DEFAULT_ALL_WORK_HOURS == activity.getAllocatedWorkHours());
	}

	@Test
	public void testDates() {
		Activity activity = new Activity("Activity");
		// Initial values
		assertTrue(null == activity.getStartDate());
		assertTrue(null == activity.getEndDate());
		// Date change
		Date startDate = new Date(1);
		try {
			activity.setStartDate(startDate);
			assertEquals(startDate, activity.getStartDate());
			assertTrue(activity.hasStartDate());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		Date endDate = new Date(2);
		try {
			activity.setEndDate(endDate);
			assertEquals(endDate, activity.getEndDate());
			assertTrue(activity.hasEndDate());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		try {
			activity.setStartDate(null);
			assertTrue(null == activity.getStartDate());
			assertFalse(activity.hasStartDate());
			activity.setEndDate(null);
			assertTrue(null == activity.getEndDate());
			assertFalse(activity.hasEndDate());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		try {
			activity.setEndDate(endDate);
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		try {
			activity.setStartDate(startDate);
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		// Incompatible date errors
		try {
			activity.setStartDate(new Date(3));
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.ACT_SET_START_DATE, e.getOperation());
			assertEquals(Activity.MSG_DATE_MISMATCH, e.getMessage());
			assertEquals(startDate, activity.getStartDate());
		}
		try {
			activity.setEndDate(new Date(0));
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.ACT_SET_END_DATE, e.getOperation());
			assertEquals(Activity.MSG_DATE_MISMATCH, e.getMessage());
			assertEquals(endDate, activity.getEndDate());
		}
	}

	@Test
	public void testDescription() {
		Activity activity = new Activity("Activity");
		// Initial values
		assertTrue(activity.getDescription() == null);
		// Description change
		String description = "This is an activity";
		activity.setDescription(description);
		assertEquals(description, activity.getDescription());
		// Constructor default value
		activity = new Activity("Activity", description);
		assertEquals(description, activity.getDescription());
	}

	@Test
	public void testName() {
		String name = "Activity";
		Activity activity = new Activity(name);
		// Initial values
		assertEquals(name, activity.getName());
		// Name change
		String newName = "New name";
		try {
			activity.setName(newName);
			assertEquals(newName, activity.getName());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		// Null name error
		name = activity.getName();
		try {
			activity.setName(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.ACT_SET_NAME, e.getOperation());
			assertEquals(Activity.MSG_NULL_NAME, e.getMessage());
			assertEquals(name, activity.getName());
		}
		// Constructor null name error correction
		activity = new Activity(null);
		assertEquals(Activity.DEFAULT_NAME, activity.getName());
	}

	@Test
	public void testUsers() {
		Activity activity = new Activity("Activity");
		// Initial values
		assertTrue(activity.getUsers().isEmpty());
		// Assigning user
		User user = sampleUser();
		try {
			activity.assignUser(user);
			assertTrue(activity.getUsers().contains(user));
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		// Duplicate user error
		try {
			activity.assignUser(user);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.ACT_ASSIGN_USER, e.getOperation());
			assertEquals(Activity.MSG_DUPE_USER, e.getMessage());
			assertTrue(1 == activity.getUsers().size());
		}
		// Removing user
		activity.removeUser(user);
		assertFalse(activity.getUsers().contains(user));
		// Null user error
		try {
			activity.assignUser(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.ACT_ASSIGN_USER, e.getOperation());
			assertEquals(Activity.MSG_NULL_USER, e.getMessage());
			assertTrue(activity.getUsers().isEmpty());
		}
	}

	@Test
	public void testWorkHours() {
		Activity activity = new Activity("Activity");
		// Initial values
		assertTrue(Activity.DEFAULT_WORK_HOURS == activity.getWorkHours());
		// Change work hours
		double workHours = 1.0;
		try {
			activity.setWorkHours(workHours);
			assertTrue(workHours == activity.getWorkHours());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		// Negative work hours error
		try {
			activity.setWorkHours(-1);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.ACT_SET_WORK_HOURS, e.getOperation());
			assertEquals(Activity.MSG_NEG_WORK_HOURS, e.getMessage());
			assertTrue(workHours == activity.getWorkHours());
		}
		// Register work hours
		double hours = 1;
		try {
			activity.registerWorkHours(hours);
			assertTrue(workHours + hours == activity.getWorkHours());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		// Register negative work hours error
		workHours = activity.getWorkHours();
		try {
			activity.registerWorkHours(-1);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.ACT_REG_HOURS, e.getOperation());
			assertEquals(Activity.MSG_NON_POS_HOURS, e.getMessage());
			assertTrue(workHours == activity.getWorkHours());
		}
	}

}

