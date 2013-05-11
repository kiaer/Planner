package planner.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity implements Comparable<Activity> {

	public static final double DEFAULT_ALL_WORK_HOURS = 0;
	public static final String
			DEFAULT_NAME = "Unnamed",
			MSG_EARLY_END_DATE = "End date must be after start date.",
			MSG_LATE_START_DATE = "Start date must be before end date.",
			MSG_NEG_ALL_HOURS = "Allocated work hours must be non-negative.",
			MSG_NEG_WORK_HOURS = "Work hours must be non-negative.",
			MSG_NON_POS_HOURS = "Registered work hours must be positive.",
			MSG_NULL_NAME = "Name must not be null",
			MSG_USER_DUPLICATE = "The list already contains this user.";

	private double allocatedWorkHours, workHours = 0;
	private String name, description;
	private Date startDate, endDate; 
	private List<User> users = new ArrayList<User>();

	public Activity(String name, String description, double allocatedWorkHours) {
		if(name != null)
			this.name = name;
		else
			this.name = DEFAULT_NAME;
		this.description = description;
		this.allocatedWorkHours = allocatedWorkHours;
	}

	public Activity(String name, String description) {
		this(name, description, DEFAULT_ALL_WORK_HOURS);
	}

	public Activity(String name, double allocatedWorkHours) {
		this(name, null, allocatedWorkHours);
	}

	public Activity(String name) {
		this(name, null, DEFAULT_ALL_WORK_HOURS);
	}

	public void assignUser(User user) throws OperationNotAllowedException {
		if(!containsUser(user))
			users.add(user);
		else
			throw new OperationNotAllowedException(Operation.ACT_ASSIGN_USER, MSG_USER_DUPLICATE);
	}

	@Override
	public int compareTo(Activity activity) {
		return name.compareTo(activity.getName());
	}

	public boolean containsUser(User user) {
		return users.contains(user);
	}

	//Untested
	public double getAllocatedWorkHours() {
		return allocatedWorkHours;
	}

	public String getDescription() {
		return description;
	} 

	public Date getEndDate() {
		return endDate;
	}

	public String getName() {
		return name; 
	}

	public Date getStartDate() {
		return startDate;
	}
 
	public List<User> getUsers() {
		return users;
	}

	public double getWorkHours() {
		return workHours;
	}

	public boolean hasEndDate() {
		return endDate != null;
	} 

	public boolean hasStartDate() {
		return startDate != null;
	}

//	public void registerWorkHours(double hours) throws OperationNotAllowedException {
//		if(hours > 0)
//			workHours += hours;
//		else
//			throw new OperationNotAllowedException(Operation.ACT_REG_HOURS, MSG_NON_POS_HOURS);
//	}
 
	public void removeUser(User user) { 
		users.remove(user);
	}

	public void setAllocatedWorkHours(double allocatedWorkHours) throws OperationNotAllowedException {
		if(allocatedWorkHours >= 0)
			this.allocatedWorkHours = allocatedWorkHours;
		else
			throw new OperationNotAllowedException(Operation.ACT_SET_ALL_HOURS, MSG_NEG_ALL_HOURS);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEndDate(Date endDate) throws OperationNotAllowedException {
		if(hasStartDate()) {
			if(getStartDate().before(endDate))
				this.endDate = endDate;
			else
				throw new OperationNotAllowedException(Operation.ACT_SET_END_DATE, MSG_EARLY_END_DATE);
		} else 
			this.endDate = endDate; 
	}

	//Should this throw an exception?
	public void setName(String name) throws OperationNotAllowedException {
		if(name != null)
			this.name = name;
		else
			throw new OperationNotAllowedException(Operation.ACT_SET_NAME, MSG_NULL_NAME);
	}
  
	public void setStartDate(Date startDate) throws OperationNotAllowedException {
		if(hasEndDate()) {
			if (getEndDate().after(startDate))
				this.startDate = startDate;
			else
				throw new OperationNotAllowedException(Operation.ACT_SET_START_DATE, MSG_LATE_START_DATE);
		} else
			this.startDate = startDate; 
	}

	//Untested
	public void setWorkHours(double workHours) throws OperationNotAllowedException {
		if(workHours > 0)
			this.workHours += workHours;
		else
			throw new OperationNotAllowedException(Operation.ACT_SET_WORK_HOURS, MSG_NEG_WORK_HOURS);
	}

}