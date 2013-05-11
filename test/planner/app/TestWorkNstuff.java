package planner.app;

import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestWorkNstuff {

	@Test
	public void testWork() {
		Planner planner = new Planner();
		User user = new User("Sten", "123", "j@j.dk");
		Activity act = new Activity("test", "testDis");
		Date toDate = new Date();
		Date fromDate = new Date();
		toDate.setDate(10);
		fromDate.setDate(5);
		Work work = new Work(fromDate, toDate, act);

		assertEquals(toDate, work.getToDate());
		assertEquals(fromDate, work.getFromDate());
		assertEquals(act, work.getActivity());
		double hours = ((toDate.getTime() - fromDate.getTime()) / Work.HOURS_TO_MILISECONDS);
		assertTrue(hours == work.getHours());

	}

	@Test
	public void testWorkActivity() {
		Planner planner = new Planner();
		User user = new User("Sten", "123", "j@j.dk");
		Activity act = new Activity("test", "testDis");
		Work work3 = new Work(null, null, null);

		// Set activity test
		try {
			work3.setActivity(act);
		} catch (OperationNotAllowedException e) {
		}

		// Set activity test null
		try {
			work3.setActivity(null);
		} catch (OperationNotAllowedException e) {
			assertEquals(Work.MSG_NULL_ACT, e.getMessage());
			assertEquals(Operation.WORK_SET_ACT, e.getOperation());
		}

	}

	@Test
	public void testWorkFromDate() {
		Planner planner = new Planner();
		User user = new User("Sten", "123", "j@j.dk");
		Date toDate = new Date();
		Date fromDat = new Date();
		Activity act = new Activity("test", "testDis");
		Work work1 = new Work(null, toDate, act);

		// Set
		try {
			work1.setFromDate(fromDat);
		} catch (OperationNotAllowedException e) {
		}

		try {
			work1.setFromDate(null);
		} catch (OperationNotAllowedException e) {
			assertEquals(Work.MSG_NULL_DATE, e.getMessage());
			assertEquals(Operation.WORK_SET_NULL_FROM_DATE, e.getOperation());

		}

	}

	@Test
	public void testWorkToDate() {
		Planner planner = new Planner();
		User user = new User("Sten", "123", "j@j.dk");
		Date toDate = new Date();
		Date fromDate = new Date();
		Activity act = new Activity("test", "testDis");
		Work work1 = new Work(fromDate, null, act);

		// Set
		try {
			work1.setToDate(toDate);
		} catch (OperationNotAllowedException e) {
		}

		toDate.setDate(10);
		fromDate.setDate(15);
		try {
			work1.setToDate(toDate);
		} catch (OperationNotAllowedException e) {
			// System.out.println(Work.MSG_DATE_MISMATCH);
			assertEquals(Work.MSG_DATE_MISMATCH, e.getMessage());
			assertEquals(Operation.WORK_DATE_MISMATCH, e.getOperation());

		}
		try {
			work1.setToDate(null);
		} catch (OperationNotAllowedException e) {
			// System.out.println(Work.MSG_DATE_MISMATCH);
			assertEquals(Work.MSG_NULL_DATE, e.getMessage());
			assertEquals(Operation.WORK_SET_NULL_TO_DATE, e.getOperation());
		}

	}

	@Test
	public void testWorkEqual() {
		Planner planner = new Planner();
		User user = new User("Sten", "123", "j@j.dk");
		Date toDate1 = new Date();
		Date toDate2 = new Date();
		Date toDate3 = new Date();
		Date fromDat1 = new Date();
		Date fromDat2 = new Date();
		Date fromDat3 = new Date();

		Activity act = new Activity("test", "testDis");
		Activity act1 = new Activity("test1", "testDis1");
		toDate1.setTime(10);
		toDate2.setTime(10);
		toDate3.setTime(15);
		fromDat1.setTime(1);
		fromDat2.setTime(1);
		fromDat3.setTime(4);
		Work work1 = new Work(fromDat1, toDate1, act);
		Work work2 = new Work(fromDat2, toDate2, act);
		Work work3 = new Work(fromDat3, toDate3, act1);
		Work work4 = new Work(fromDat3, toDate2, act);
		assertTrue(work1.equals(work2));
		assertFalse(work1.equals(work3));
		assertFalse(work1.equals(act));
		work3.equals(work1);
		assertEquals(1,work3.compareTo(work4));
		assertEquals(0, work1.compareTo(work2));
		assertEquals(1,work3.compareTo(work1));

	}

}
