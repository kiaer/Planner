package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;


public class testActivity {
 
	  
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

		}

//		@Test
//		public void testAssignWork() throws Exception {
//			Planner planner = new Planner();
//			planner.adminLogIn("admin");
//			assertTrue(planner.adminLoggedIn());
	//
//			// step 1
//			User user = new User("Karl", "1234", "fk@mail.dk");
//			assertEquals(user.getWorkList().size(), 0);
	//
//			// step 2
//			Date start = new Date();
//			Date end = new Date();
//			start.setDate(5);
//			end.setDate(10);
//			Activity activity1 = new Activity("sten", "venstre ben");
//			Work work1 = new Work(start, end, activity1);
//			user.registerWork(start, end, activity1);
//			assertEquals(user.getWorkList().size(), 1);
//			Date startT = new Date();
//			Date endT = new Date();
//			startT.setDate(5);
//			endT.setDate(10);
//			assertEquals(activity1 ,user.getWorkList().get(0).getActivity());
//			
	//
//		}

	}

