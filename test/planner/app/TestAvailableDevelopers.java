package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.sun.accessibility.internal.resources.accessibility;

import java.util.List;

public class TestAvailableDevelopers extends SampleData {

	@Test
	public void testRegisterWork() throws OperationNotAllowedException {

		GregorianCalendar cal = new GregorianCalendar();
		Date toDate = new Date();
		toDate.setTime(cal.getTime().getTime() + 1);

		assertTrue(planner.adminLoggedIn());

		Activity activity = createTempAct();
		User user = createTempUser();

		Work work = new Work(cal.getTime(), toDate, activity);
		user.registerWork(work);

		assertTrue(user.getWorkSet().contains(work));

	}

	@Test
	public void testRegisterWorkSameDate() throws OperationNotAllowedException {
		GregorianCalendar cal = new GregorianCalendar();
		Date toDate = new Date();
		toDate.setTime(cal.getTime().getTime() + 100);
		Activity activity = createTempAct();
		Activity activity2 = new Activity("Testes");
		User user = createTempUser();
		Work work1 = new Work(cal.getTime(), toDate, activity);
		Work work2 = new Work(cal.getTime(), toDate, activity2);
		user.registerWork(work1);
		assertTrue(user.getWorkSet().contains(work1));
		try {
			user.registerWork(work2);
			fail("OperationNotAllowedException should've been thrown");
		} catch (OperationNotAllowedException e) {
			assertEquals(User.MSG_WORK_OVERLAP, e.getMessage());
			System.out.println(user.getWorkSet().size());
			assertTrue(user.getWorkSet().contains(work1));
			assertFalse(user.getWorkSet().contains(work2));
		}

	}

	@Test
	public void testFindAvailableDevelopers()
			throws OperationNotAllowedException {

		GregorianCalendar cal = new GregorianCalendar();
		Date toDate = new Date();
		toDate.setTime(cal.getTime().getTime() + 1);

		Planner planner = new Planner();

		planner.adminLogIn("admin");

		assertTrue(planner.adminLoggedIn());

		User developer1 = new User("JOBE", "1234", "Jobe@plannerteam.dk");
		User developer2 = new User("cki", "1234", "cki@plannerteam.dk");
		User developer3 = new User("cmrn", "1234", "Carsten@plannerteam.dk");

		Activity activity = new Activity("Software 1");

		planner.register(developer1);
		planner.register(developer2);
		planner.register(developer3);

		Work work1 = new Work(cal.getTime(), toDate, activity);
		Work work2 = new Work(cal.getTime(), toDate, activity);

		developer1.registerWork(work1);
		developer2.registerWork(work2);

		List<User> availableUsers = planner.getAvailableUsers(cal.getTime(),
				toDate);

		assertEquals(1, availableUsers.size());
		assertTrue(availableUsers.get(0) == developer3);

	}
}
