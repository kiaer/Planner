package planner.app;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class TestDateServer extends SampleData {

	@Test
	public void testDateServer() {
		Calendar calendar = new DateServer().getDate();
		// Initial values
		Calendar expected = GregorianCalendar.getInstance();
		assertEquals(expected.get(Calendar.YEAR), calendar.get(Calendar.YEAR));
		assertEquals(expected.get(Calendar.MONTH), calendar.get(Calendar.MONTH));
		assertEquals(expected.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.DAY_OF_MONTH));
	}

}
