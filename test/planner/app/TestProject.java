package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

public class TestProject extends SampleData {

	@Test
	public void testActivities() {
		Project project = new Project("Project");
		//Initial values
		assertTrue(project.getActivities().isEmpty());
		//Adding activity
		Activity activity = sampleActivity();
		try {
			project.addActivity(activity);
			assertTrue(project.getActivities().contains(activity));
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		//Duplicate activity error
		try {
			project.addActivity(activity);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertTrue(1 == project.getActivities().size());
			assertEquals(Operation.PROJ_ADD_ACT, e.getOperation());
			assertEquals(Project.MSG_DUPE_ACT, e.getMessage());
		}
		//Removing activity
		project.removeActivity(activity);
		assertFalse(project.getActivities().contains(activity));
		//Null activity error
		try {
			project.addActivity(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PROJ_ADD_ACT, e.getOperation());
			assertEquals(Project.MSG_NULL_ACT, e.getMessage());
			assertTrue(project.getActivities().isEmpty());
		}
	}

	@Test
	public void testDates() {
		Project project = new Project("Project");
		//Initial values
		assertTrue(project.getStartDate() == null);
		assertTrue(project.getEndDate() == null);
		//Date change
		Date startDate = new Date(1);
		try {
			project.setStartDate(startDate);
			assertEquals(startDate, project.getStartDate());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		Date endDate = new Date(2);
		try {
			project.setEndDate(endDate);
			assertEquals(endDate, project.getEndDate());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		try {
			project.setStartDate(null);
			assertTrue(project.getStartDate() == null);
			project.setEndDate(null);
			assertTrue(project.getEndDate() == null);
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		try {
			project.setEndDate(endDate);
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		try {
			project.setStartDate(startDate);
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		//Incompatible date errors
		try {
			project.setStartDate(new Date(3));
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PROJ_ASSIGN_START_DATE, e.getOperation());
			assertEquals(Project.MSG_END_BEFORE_START, e.getMessage());
			assertEquals(startDate, project.getStartDate());
		}
		try {
			project.setEndDate(new Date(0));
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PROJ_ASSIGN_END_DATE, e.getOperation());
			assertEquals(Project.MSG_END_BEFORE_START, e.getMessage());
			assertEquals(endDate, project.getEndDate());
		}
	}

	@Test
	public void testName() {
		String name = "Project";
		Project project = new Project(name);
		//Initial values
		assertEquals(name, project.getName());
		//Name change
		String newName = "New name";
		try {
			project.setName(newName);
			assertEquals(newName, project.getName());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		//Null name error
		name = project.getName();
		try {
			project.setName(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PROJ_SET_NAME, e.getOperation());
			assertEquals(Project.MSG_NULL_NAME, e.getMessage());
			assertEquals(name, project.getName());
		}
		//Null name constructor error correction
		project = new Project(null);
		assertEquals(Project.DEFAULT_NAME, project.getName());
	}

	@Test
	public void testProjectLeader() {
		Project project = new Project("Project");
		//Initial values
		assertTrue(project.getProjectLeader() == null);
		//Project leader change
		User projectLeader = sampleUser();
		project.setProjectLeader(projectLeader);
		assertEquals(projectLeader, project.getProjectLeader());
		//Project leader remove
		project.removeProjectLeader();
		assertTrue(project.getProjectLeader() == null);
		//HasProjectLeader test
		assertFalse(project.hasProjectLeader());
		project.setProjectLeader(projectLeader);
		assertTrue(project.hasProjectLeader());
	}

}