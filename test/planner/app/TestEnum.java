package planner.app;

import org.junit.Test;

public class TestEnum extends SampleData {
	
	@Test
	public void testOperation() {
		for(Operation op : Operation.values()) {
			Operation.valueOf(op.toString());
		}
	}

	@Test
	public void testConstantActivities() {
		for(ConstantActivities cAct : ConstantActivities.values()) {
			ConstantActivities.valueOf(cAct.toString());
		}
	}

}
