package planner.app;

import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestWorkNstuff {

	@Test
	public void testNullFromDate() {
		Planner planner = new Planner();
		User user = new User("Sten", "123", "j@j.dk");
		Date end = new Date();
		Date start = new Date();
		Activity act= new Activity("test", "testDis");
		Work work1 = new Work(null, end, act);
		Work work2 = new Work(start, null, act);
		Work work3 = new Work(null, null, null);
		
		try {
			work3.setActivity(act);
		} catch (OperationNotAllowedException e) {
			e.printStackTrace();
			assertEquals(Work.MSG_NULL_ACT, e.getMessage());
			assertEquals(Operation.WORK_SET_ACT, e.getOperation());
		}
		
		try {
				work2.setActivity(null);
		} catch (OperationNotAllowedException e) {
			e.printStackTrace();
			assertEquals(Work.MSG_NULL_ACT, e.getMessage());
			assertEquals(Operation.WORK_SET_ACT, e.getOperation());
		}
		
		
		
		
		
	}
}
