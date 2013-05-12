package planner.app;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class TestDateServer extends SampleData {

	@Test
	public void testDateServer() {
		Calendar expected = GregorianCalendar.getInstance();
		Calendar result = new DateServer().getDate();
		assertEquals(expected.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals(expected.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals(expected.get(Calendar.DAY_OF_MONTH), result.get(Calendar.DAY_OF_MONTH));
	}

}
