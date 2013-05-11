package planner.app;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class TestDateServer {

	@Test
	public void testDateServer() {
		Calendar expected = GregorianCalendar.getInstance();
		Calendar result = new DateServer().getDate();
		assertEquals(expected.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals(expected.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals(expected.get(Calendar.DAY_OF_MONTH),
				result.get(Calendar.DAY_OF_MONTH));
	}
 
	/***
	 * Tests that the getDate function of the library application returns the
	 * current date.
	 */
	@Test
	public void testPlannerGetDate() {
		Planner planner = new Planner();
		Calendar expected = GregorianCalendar.getInstance();
		Calendar result = planner.getDate();

		assertEquals(expected.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals(expected.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals(expected.get(Calendar.DAY_OF_MONTH),
				result.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testUserActivityDate() {
		
		Planner planner = new Planner();
		Calendar expected = GregorianCalendar.getInstance();
		User user = new User("sten", "2", "j@g.dk");

		user.setStartWork(planner.getDate());

		Calendar c = user.getStartWork();
		Calendar d = user.DaysWorking(10);
		//Check at work start
		assertEquals(expected.get(Calendar.YEAR), c.get(Calendar.YEAR));
		assertEquals(expected.get(Calendar.MONTH), c.get(Calendar.MONTH));
		assertEquals(expected.get(Calendar.DAY_OF_MONTH),c.get(Calendar.DAY_OF_MONTH));
		
		
		//Check at work end
		expected.add(Calendar.DAY_OF_YEAR, 10);
		assertEquals(expected.get(Calendar.YEAR), d.get(Calendar.YEAR));
		assertEquals(expected.get(Calendar.MONTH), d.get(Calendar.MONTH));
		assertEquals(expected.get(Calendar.DAY_OF_MONTH),d.get(Calendar.DAY_OF_MONTH));
		
		System.out.println(c.getTime());
		System.out.println(d.getTime());
	}
}
