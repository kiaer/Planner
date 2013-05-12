package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Test;

public class testModifyProject extends SampleData {

	@Test
	public void testChangeProjectLeader() throws OperationNotAllowedException {
		planner.adminLogIn("admin");
		assertTrue(planner.adminLoggedIn());

		User projectLeader = new User("Leder", "gammel", "gammel@gammel.dk");
		User newProjectLeader = new User("Den nye", "ny", "ny@ny.dk");

		Activity act = new Activity("test");
		String projectName = "Software Eng 1";

		Project project = new Project(projectName, projectLeader);

		assertTrue(project.hasProjectLeader());
		assertEquals(projectLeader, project.getProjectLeader());

		project.addActivity(act);
		assertTrue(project.getActivities().size() == 1);
		project.removeActivity(act);
		assertTrue(project.getActivities().size() == 0);

		project.setProjectLeader(newProjectLeader);

		assertFalse(projectLeader == project.getProjectLeader());
		assertEquals(newProjectLeader, project.getProjectLeader());
		project.removeProjectLeader();
		assertFalse(project.hasProjectLeader());
		

	}

	@Test
	public void testChangeStartDate() {
		// TODO: make it possible to change startdate
		User projectLeader = new User("Leder", "gammel", "gammel@gammel.dk");
		User newProjectLeader = new User("Den nye", "ny", "ny@ny.dk");
		Date start = new Date();
		Date newStart = new Date();
		Date end = new Date();
		Activity act = new Activity("test");
		String projectName = "Software Eng 1";
		end.setDate(10);
		start.setDate(1);
		Project project = new Project(projectName, newProjectLeader, start, end);
		newStart.setDate(2);
		project.setStartDate(newStart);
		assertEquals(newStart,project.getStartDate());

	}

	@Test
	public void testChangeEndDate() {
		// TODO: make it possible to change startdate
		User projectLeader = new User("Leder", "gammel", "gammel@gammel.dk");
		User newProjectLeader = new User("Den nye", "ny", "ny@ny.dk");
		Date start = new Date();
		Date newEnd= new Date();
		Date end = new Date();
		Activity act = new Activity("test");
		String projectName = "Software Eng 1";
		end.setDate(10);
		start.setDate(1);
		Project project = new Project(projectName, newProjectLeader, start, end);
		newEnd.setDate(2);
		try {
			project.setEndDate(newEnd);
		} catch (OperationNotAllowedException e) {
			assertEquals(Project.MSG_END_BEFORE_START, e.getMessage());
			assertEquals(Operation.PROJECT_ASSIGN_END_DATE, e.getOperation());
		}
		assertEquals(newEnd,project.getEndDate());
	}

	@Test
	public void testChangeEndDateBeforeStart()
			throws OperationNotAllowedException {
		// TODO: make it possible to change startdate
		User projectLeader = new User("Leder", "gammel", "gammel@gammel.dk");
		User newProjectLeader = new User("Den nye", "ny", "ny@ny.dk");
		Date start = new Date();
		Date newEnd= new Date();
		Date end = new Date();
		Activity act = new Activity("test");
		String projectName = "Software Eng 1";
		end.setDate(10);
		start.setDate(3);
		Project project = new Project(projectName, newProjectLeader, start, end);
		newEnd.setDate(2);
		try {
			project.setEndDate(newEnd);
		} catch (OperationNotAllowedException e) {
			assertEquals(Project.MSG_END_BEFORE_START, e.getMessage());
			assertEquals(Operation.PROJECT_ASSIGN_END_DATE, e.getOperation());
		}
		
		
		

	}
}
