package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class TestDefineActivities extends SampleData{
	
	
	@Test
	public void testAddActivities(){
		
		planner.getProjects().isEmpty();
		assertFalse(planner.adminLoggedIn());
		
		planner.adminLogIn("admin");
		assertTrue(planner.adminLoggedIn());
		
		String projectName = "Software 1 projekt";
		String projectLeader = "Kevin den foerste";
		
		Project project = new Project(projectName, projectLeader);

		
		String activityName = "Create users";
		String activityDescription = "Allow creation of users";
		
		Activity activity = new Activity(activityName, activityDescription);
		
		project.addActivity(activity);
		
		List<Activity> activities = project.getActivities();	
		
		assertEquals(1, activities.size());
		assertEquals(activityName, activities.get(0).getActivityName());
		assertEquals(activityDescription, activities.get(0).getActivityDescription());
		
		
		
	}
	

}
