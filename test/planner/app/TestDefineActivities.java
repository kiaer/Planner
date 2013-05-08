package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class TestDefineActivities extends SampleData{

	@Test
	public void testAddActivities() throws OperationNotAllowedException{
		planner.getProjects().isEmpty();
		
		planner.adminLogIn("admin");
		assertTrue(planner.adminLoggedIn());
		
		String projectName = "Software 1 projekt";
		
		User projectLeader = new User("Karl", "1234", "fk@mail.dk");

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
	public void testAssignDeveloperActivity() throws OperationNotAllowedException{
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
		
		assertEquals(project.getActivities().get(0), developer.getActivities().get(0));
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
	public void testAssignStartEndDate() {
		//TODO: Add start and end date to activity

	}

}
