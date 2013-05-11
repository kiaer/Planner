package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

public class TestDefineActivities extends SampleData {

	@Test
	public void testAddActivities() throws OperationNotAllowedException {
		planner.getProjects().isEmpty();

		planner.adminLogIn("admin");
		assertTrue(planner.adminLoggedIn());

		String projectName = "Software 1 projekt";

		User projectLeader = new User("Karl", "1234", "fk@mail.dk");

		assertTrue(projectLeader.getActivities().size() == 0);

		planner.register(projectLeader);

		Project project = new Project(projectName, projectLeader);

		String activityName = "Create users";
		String activityDescription = "Allow creation of users";

		Activity activity = new Activity(activityName, activityDescription);

		project.addActivity(activity);

		List<Activity> activities = project.getActivities();

		assertEquals(1, activities.size());
		assertEquals(activityName, activities.get(0).getName());
		assertEquals(activityDescription, activities.get(0).getDescription());
	}

	@Test
	public void testAssignDeveloperActivity()
			throws OperationNotAllowedException {
		assertTrue(planner.adminLoggedIn());

		planner.getUsers().clear();

		assertTrue(planner.getUsers().isEmpty());

		User developer = new User("User user", "1234", "fk@fk.dk");
		planner.register(developer);

		assertEquals(developer, planner.getUsers().get(0));

		Project project = new Project("Testproject", developer);

		String activityName = "Create users";
		String activityDescription = "Allow creation of users";

		Activity activity = new Activity(activityName, activityDescription);

		project.addActivity(activity);

		developer.assignActivity(activity);

		assertEquals(project.getActivities().get(0), developer.getActivities()
				.get(0));
		assertEquals(activity, developer.getActivities().get(0));
	}

	@Test
	public void testAssignUsers() {
		Activity activity = new Activity("Activity");
		assertTrue(activity.getUsers().isEmpty());
		User user = new User("Adam", "1234", "adam@mail.dk");
		activity.assignUser(user);
		assertTrue(activity.containsUser(user));
		assertFalse(activity.getUsers().isEmpty());
		activity.removeUser(user);
		assertFalse(activity.containsUser(user));
	}

	@Test
	public void testAssignStartEndDate() throws OperationNotAllowedException {

		Activity activity = new Activity("Activity");

		assertTrue(activity.getStartDate() == null);
		assertTrue(activity.getEndDate() == null);

		GregorianCalendar cal = new GregorianCalendar();

		activity.setStartDate(cal.getTime());

		GregorianCalendar newcal = new GregorianCalendar();
		newcal.setTime(cal.getTime());
		newcal.add(Calendar.DAY_OF_YEAR, 10);

		activity.setEndDate(newcal.getTime());

		assertEquals(cal.getTime(), activity.getStartDate());
		assertEquals(newcal.getTime(), activity.getEndDate());
		assertTrue(activity.hasStartDate());
		assertTrue(activity.hasEndDate());

	}

	@Test
	public void testAssignSameStartEnd() throws OperationNotAllowedException {

		Activity activity = new Activity("Activity");

		assertTrue(activity.getStartDate() == null);
		assertTrue(activity.getEndDate() == null);

		GregorianCalendar cal = new GregorianCalendar();

		activity.setStartDate(cal.getTime());

		assertEquals(cal.getTime(), activity.getStartDate());

		GregorianCalendar newCal = new GregorianCalendar();
		newCal.setTime(cal.getTime());

		try {
			activity.setEndDate(newCal.getTime());
			fail("Should throw OperationNotAllowedException");
		} catch (OperationNotAllowedException e) {
			assertEquals(activity.MSG_EARLY_END_DATE, e.getMessage());
			assertTrue(activity.getEndDate() == null);
		}
		assertTrue(activity.hasStartDate());
		assertFalse(activity.hasEndDate());

	}

	@Test
	public void testAssignStartWithoutEnd() throws OperationNotAllowedException {

		Activity activity = new Activity("Activity");

		assertTrue(activity.getStartDate() == null);

		GregorianCalendar cal = new GregorianCalendar();

		activity.setStartDate(cal.getTime());

		assertEquals(cal.getTime(), activity.getStartDate());

	}

	@Test
	public void testAssignEndWithoutStart() throws OperationNotAllowedException {

		Activity activity = new Activity("Activity");

		assertTrue(activity.getEndDate() == null);

		GregorianCalendar cal = new GregorianCalendar();

		activity.setEndDate(cal.getTime());

		assertEquals(cal.getTime(), activity.getEndDate());

	}

	@Test
	public void testAssignEndStartDate() throws OperationNotAllowedException {

		Activity activity = new Activity("Activity");

		assertTrue(activity.getStartDate() == null);
		assertTrue(activity.getEndDate() == null);

		GregorianCalendar cal = new GregorianCalendar();

		activity.setEndDate(cal.getTime());

		GregorianCalendar newcal = new GregorianCalendar();
		newcal.setTime(cal.getTime());
		newcal.add(Calendar.DAY_OF_YEAR, -10);

		activity.setStartDate(newcal.getTime());

		assertEquals(cal.getTime(), activity.getEndDate());
		assertEquals(newcal.getTime(), activity.getStartDate());
		assertTrue(activity.hasStartDate());
		assertTrue(activity.hasEndDate());

	}

	@Test
	public void testAssignSameEndStart() throws OperationNotAllowedException {

		Activity activity = new Activity("Activity");

		assertTrue(activity.getStartDate() == null);
		assertTrue(activity.getEndDate() == null);

		GregorianCalendar cal = new GregorianCalendar();

		activity.setEndDate(cal.getTime());

		assertEquals(cal.getTime(), activity.getEndDate());

		GregorianCalendar newCal = new GregorianCalendar();
		newCal.setTime(cal.getTime());

		try {
			activity.setStartDate(newCal.getTime());
			fail("Should throw OperationNotAllowedException");
		} catch (OperationNotAllowedException e) {
			assertEquals(activity.MSG_LATE_START_DATE, e.getMessage());
			assertTrue(activity.getStartDate() == null);
		}
		assertTrue(activity.hasEndDate());
		assertFalse(activity.hasStartDate());

	}

	@Test
	public void testSetAllocatedWorkHours() {
		
		Activity activity = new Activity("Activity");
		
		activity.setAllocatedWorkHours(10.0);
		
		assertEquals(10.0, activity.getAllocatedWorkHours(), 1e-15);
		
	}
}
