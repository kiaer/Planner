package planner.app;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestWork extends SampleData {

	@Test
	public void testActivity() {
		Activity activity = sampleActivity();
		Work work = new Work(new Date(0), new Date(1), activity);
		// Initial values
		assertEquals(activity, work.getActivity());
		// Change activity
		Activity newActivity = new Activity("Activity");
		try {
			work.setActivity(newActivity);
			assertEquals(newActivity, work.getActivity());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		// Null activity error
		activity = work.getActivity();
		try {
			work.setActivity(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.WORK_SET_ACT, e.getOperation());
			assertEquals(Work.MSG_NULL_ACT, e.getMessage());
			assertEquals(activity, work.getActivity());
		}
	}

	@Test
	public void testDates() {
		Date fromDate = new Date(0);
		Date toDate = new Date(2);
		Work work = new Work(fromDate, toDate, sampleActivity());
		// Initial values
		assertTrue(fromDate == work.getFromDate());
		assertTrue(toDate == work.getToDate());
		// Date change
		Date newFromDate = new Date(1);
		try {
			work.setFromDate(newFromDate);
			assertEquals(newFromDate, work.getFromDate());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		Date newToDate = new Date(3);
		try {
			work.setToDate(newToDate);
			assertEquals(newToDate, work.getToDate());
		} catch (OperationNotAllowedException e) {
			fail(WRONG_EXCEPTION);
		}
		// Null date errors
		fromDate = work.getFromDate();
		try {
			work.setFromDate(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.WORK_SET_FROM_DATE, e.getOperation());
			assertEquals(Work.MSG_NULL_DATE, e.getMessage());
			assertEquals(fromDate, work.getFromDate());
		}
		toDate = work.getToDate();
		try {
			work.setToDate(null);
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.WORK_SET_TO_DATE, e.getOperation());
			assertEquals(Work.MSG_NULL_DATE, e.getMessage());
			assertEquals(toDate, work.getToDate());
		}
		// Incompatible date errors
		try {
			work.setFromDate(new Date(4));
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.WORK_SET_FROM_DATE, e.getOperation());
			assertEquals(Work.MSG_DATE_MISMATCH, e.getMessage());
			assertEquals(fromDate, work.getFromDate());
		}
		try {
			work.setToDate(new Date(0));
			fail(NO_EXCEPTION);
		} catch (OperationNotAllowedException e) {
			assertEquals(Operation.WORK_SET_TO_DATE, e.getOperation());
			assertEquals(Work.MSG_DATE_MISMATCH, e.getMessage());
			assertEquals(toDate, work.getToDate());
		}
		// Constructor null from error correction
		toDate = new Date(Work.DAY_TO_MILISECONDS);
		work = new Work(null, toDate, sampleActivity());
		assertEquals(new Date(0), work.getFromDate());
		// Constructor null to error correction
		fromDate = new Date(0);
		work = new Work(fromDate, null, sampleActivity());
		assertEquals(new Date(Work.DAY_TO_MILISECONDS), work.getToDate());
		// Constructor null from and to error correction
		GregorianCalendar calendar = new GregorianCalendar();
		work = new Work(null, null, sampleActivity());
		assertEquals(calendar.getTime().getTime(),
				work.getFromDate().getTime(), 100);
		assertEquals(calendar.getTime().getTime() + Work.DAY_TO_MILISECONDS,
				work.getToDate().getTime(), 100);
		// Constructor date mismatch correction;
		fromDate = new Date(1);
		toDate = new Date(0);
		work = new Work(fromDate, toDate, sampleActivity());
		assertEquals(fromDate, work.getFromDate());
		assertEquals(fromDate.getTime() + Work.DAY_TO_MILISECONDS, work
				.getToDate().getTime());
		// Constructor null activity error correction
		work = new Work(new Date(0), new Date(1), null);
		assertEquals(ConstantActivities.NONE.getActivity(), work.getActivity());
	}

	@Test
	public void testCompare() {
		Date fromDate = new Date(0), toDate = new Date(1);
		Activity activity = new Activity("a");
		Work work = new Work(fromDate, toDate, activity);
		// Not a work equal
		assertFalse(work.equals(sampleActivity()));
		assertFalse(work.equals(null));
		// Unequal from date
		Work other = new Work(new Date(1), new Date(2), sampleActivity());
		assertFalse(work.equals(other));
		assertTrue(-1 == work.compareTo(other));
		// Unequal to date
		other = new Work(fromDate, new Date(2), sampleActivity());
		assertFalse(work.equals(other));
		assertTrue(-1 == work.compareTo(other));
		// Unequal activity
		other = new Work(fromDate, toDate, new Activity("b"));
		assertFalse(work.equals(other));
		assertTrue(-1 == work.compareTo(other));
		// Equal
		assertTrue(work.equals(work));
	}

	@Test
	public void testHours() {
		Date fromDate = new Date(0), toDate = new Date(Work.HOURS_TO_MILISECONDS);
		Work work = new Work(fromDate, toDate, sampleActivity());
		// Initial values
		assertTrue(1 == work.getHours());
	}

}
