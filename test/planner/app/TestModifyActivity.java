package planner.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class TestModifyActivity {

	@Test
	public void testChangeDescription() {

		String descript1 = "Description";
		String descript2 = "Description2";

		Activity activity = new Activity("Activity", descript1);

		assertEquals("Activity", activity.getName());
		assertEquals(descript1, activity.getDescription());

		activity.setDescription(descript2);

		assertEquals(descript2, activity.getDescription());
		assertFalse(descript1 == activity.getDescription());

	}

	@Test
	public void testChangeName() throws OperationNotAllowedException {

		String name = "Activity";
		String name2 = "Activity2";

		Activity activity = new Activity(name, "Description");

		assertEquals(name, activity.getName());

		activity.setName(name2);

		assertEquals(name2, activity.getName());
		assertFalse(name == activity.getName());

	}

	@Test
	public void testChangeNameNull() throws OperationNotAllowedException {

		String name = "Red Kevin";

		Activity activity = new Activity(name);

		assertEquals(name, activity.getName());

		try {
			activity.setName(null);
			fail("Should throw OperationNotAllowedExceptions");
		} catch (OperationNotAllowedException e) {
			assertEquals(activity.MSG_NULL_NAME, e.getMessage());
			assertEquals(name, activity.getName());
		}

	}

	@Test
	public void testSetActualWorkHours() throws OperationNotAllowedException {

		String name = "Red Kevin";
		String description = "Kevin skal reddes";
		double AllocatedWorkHours = 10.0;

		Activity activity = new Activity(name, description, AllocatedWorkHours);

		assertEquals(name, activity.getName());
		assertEquals(description, activity.getDescription());
		assertEquals(AllocatedWorkHours, activity.getAllocatedWorkHours(),
				1e-15);

		double actualWorkHours = 100.0;

		activity.setWorkHours(actualWorkHours);

		assertEquals(actualWorkHours, activity.getWorkHours(), 1e-15);

	}

	@Test
	public void testSetActualWorkHoursNeg() throws OperationNotAllowedException {

		String name = "Red Kevin";
		String description = "Kevin skal reddes";
		double allocatedWorkHours = 10.0;

		Activity activity = new Activity(name, description, allocatedWorkHours);

		assertEquals(name, activity.getName());
		assertEquals(description, activity.getDescription());
		assertEquals(allocatedWorkHours, activity.getAllocatedWorkHours(),
				1e-15);

		double actualWorkHours = -10.0;

		try {
			activity.setWorkHours(actualWorkHours);
			fail("OperationNotAllowedException should've been thrown");
		} catch (OperationNotAllowedException e) {
			assertEquals(activity.MSG_NEG_WORK_HOURS, e.getMessage());
			assertEquals(0, activity.getWorkHours(), 1e-15);
		}

	}

	@Test
	public void testAddActualWorkHours() throws OperationNotAllowedException {

		String name = "Red Kevin";
		String description = "Kevin skal reddes";
		double AllocatedWorkHours = 10.0;

		Activity activity = new Activity(name, description, AllocatedWorkHours);

		assertEquals(name, activity.getName());
		assertEquals(description, activity.getDescription());
		assertEquals(AllocatedWorkHours, activity.getAllocatedWorkHours(),
				1e-15);

		double addActualWorkHours = 100.0;

		activity.registerWorkHours(addActualWorkHours);

		assertEquals(addActualWorkHours, activity.getWorkHours(), 1e-15);

	}

	@Test
	public void testAddActualWorkHoursNeg() throws OperationNotAllowedException {

		String name = "Red Kevin";
		String description = "Kevin skal reddes";
		double allocatedWorkHours = 10.0;

		Activity activity = new Activity(name, description, allocatedWorkHours);

		assertEquals(name, activity.getName());
		assertEquals(description, activity.getDescription());
		assertEquals(allocatedWorkHours, activity.getAllocatedWorkHours(),
				1e-15);

		double addActualWorkHours = -10.0;

		try {
			activity.registerWorkHours(addActualWorkHours);
			fail("OperationNotAllowedException should've been thrown");
		} catch (OperationNotAllowedException e) {
			assertEquals(activity.MSG_NON_POS_HOURS, e.getMessage());
			assertEquals(0, activity.getWorkHours(), 1e-15);
		}

	}
}
