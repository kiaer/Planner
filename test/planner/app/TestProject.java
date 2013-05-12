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
		Project project = sampleProject();
		//Initial values
		assertTrue(project.getActivities().isEmpty());
		//Adding activities
		Activity activity = sampleActivity();
		try {
			project.addActivity(activity);
			assertTrue(project.getActivities().contains(activity));
		} catch (OperationNotAllowedException e) {
			fail("An OperationNotAllowedException should not have been thrown.");
		}
		//Removing activities
		project.removeActivity(activity);
		assertFalse(project.getActivities().contains(activity));
		//Null activity error
		try {
			project.addActivity(null);
			fail("An OperationNotAllowedException should have been thrown.");
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
			fail("An OperationNotAllowedException should not have been thrown.");
		}
		Date endDate = new Date(2);
		try {
			project.setEndDate(endDate);
			assertEquals(endDate, project.getEndDate());
		} catch (OperationNotAllowedException e) {
			fail("An OperationNotAllowedException should not have been thrown.");
		}
		try {
			project.setStartDate(null);
			assertTrue(project.getStartDate() == null);
			project.setEndDate(null);
			assertTrue(project.getEndDate() == null);
		} catch (OperationNotAllowedException e) {
			fail("An OperationNotAllowedException should not have been thrown.");
		}
		try {
			project.setEndDate(endDate);
			assertEquals(endDate, project.getEndDate());
		} catch (OperationNotAllowedException e) {
			fail("An OperationNotAllowedException should not have been thrown.");
		}
		try {
			project.setStartDate(startDate);
			assertEquals(startDate, project.getStartDate());
		} catch (OperationNotAllowedException e) {
			fail("An OperationNotAllowedException should not have been thrown.");
		}
		//Incompatible date errors
		try {
			project.setStartDate(new Date(3));
			fail("An OperationNotAllowedException should not have been thrown.");
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PROJ_ASSIGN_START_DATE, e.getOperation());
			assertEquals(Project.MSG_END_BEFORE_START, e.getMessage());
			assertEquals(startDate, project.getStartDate());
		}
		try {
			project.setEndDate(new Date(0));
			fail("An OperationNotAllowedException should have been thrown.");
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PROJ_ASSIGN_END_DATE, e.getOperation());
			assertEquals(Project.MSG_END_BEFORE_START, e.getMessage());
			assertEquals(endDate, project.getEndDate());
		}
	}

	@Test
	public void testName() {
		String projectName = "Project";
		Project project = new Project(projectName);
		//Initial values
		assertEquals(projectName, project.getName());
		//Name change
		String newProjectName = "New name";
		try {
			project.setName(newProjectName);
			assertEquals(newProjectName, project.getName());
		} catch (OperationNotAllowedException e) {
			fail("An OperationNotAllowedException should not have been thrown.");
		}
		//Null name error
		projectName = project.getName();
		try {
			project.setName(null);
			fail("An OperationNotAllowedException should have been thrown.");
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.PROJ_SET_NAME, e.getOperation());
			assertEquals(Project.MSG_NULL_NAME, e.getMessage());
			assertEquals(projectName, project.getName());
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