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
		Activity act= new Activity("test", "testDis");
		Date toDate = new Date();
		Date fromDate = new Date();
		toDate.setDate(10);
		fromDate.setDate(5);
		Work work = new Work(fromDate, toDate, act);
		
		assertEquals(toDate,work.getToDate());
		assertEquals(fromDate,work.getFromDate());
		assertEquals(act,work.getActivity());
		double hours=((toDate.getTime()-fromDate.getTime())/Work.HOURS_TO_MILISECONDS);
		assertTrue(hours == work.getHours());
		
	}
	@Test
	public void testWorkActivity() {
		Planner planner = new Planner();
		User user = new User("Sten", "123", "j@j.dk");
		Activity act= new Activity("test", "testDis");
		Work work3 = new Work(null, null, null);
		
		
		//Set activity test
		try {
			work3.setActivity(act);
		} catch (OperationNotAllowedException e) {}
		
		
		//Set activity test null
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
		Activity act= new Activity("test", "testDis");
		Work work1 = new Work(null, toDate, act);

		
		//Set 
		try {
			work1.setFromDate(fromDat);
		} catch (OperationNotAllowedException e) {}
		
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
		Activity act= new Activity("test", "testDis");
		Work work1 = new Work(fromDate, null, act);
		
		
		//Set 
		try {
			work1.setToDate(toDate);
		} catch (OperationNotAllowedException e) {}
		
		toDate.setDate(10);
		fromDate.setDate(15);
		try {
			work1.setToDate(toDate);
		} catch (OperationNotAllowedException e) {
			//System.out.println(Work.MSG_DATE_MISMATCH);
			assertEquals(Work.MSG_DATE_MISMATCH, e.getMessage());
			assertEquals(Operation.WORK_DATE_MISMATCH, e.getOperation());
			
		}
		try {
			work1.setToDate(null);
		} catch (OperationNotAllowedException e) {
			//System.out.println(Work.MSG_DATE_MISMATCH);
			assertEquals(Work.MSG_NULL_DATE, e.getMessage());
			assertEquals(Operation.WORK_SET_NULL_TO_DATE, e.getOperation());
		}
		
	}
	
}
