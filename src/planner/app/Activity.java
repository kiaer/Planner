package planner.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity {

	public static final int DEFAULT_ALL_WORK_HOURS = 0;

	private int allocatedWorkHours;
	private String name, description;
	private Date startDate, endDate;
	private List<User> users = new ArrayList<User>();

	public Activity(String name, String description, int allocatedWorkHours) {
		setDescription(description);
		setName(name);
		setAllocatedWorkHours(allocatedWorkHours);
	}

	public Activity(String name, String description) {
		this(name, description, DEFAULT_ALL_WORK_HOURS);
	}

	public Activity(String name, int allocatedWorkHours) {
		this(name, null, allocatedWorkHours);
	}

	public Activity(String name) {
		this(name, null, DEFAULT_ALL_WORK_HOURS);
	}

	public void assignUser(User user) {
		if(!containsUser(user))
			users.add(user);
	}

	public boolean containsUser(User user) {
		return users.contains(user);
	}

	//Untested
	public int getAllocatedWorkHours() {
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

	//Untested
	public boolean hasEndDate() {
		return endDate != null;
	}

	//Untested
	public boolean hasStartDate() {
		return startDate != null;
	}

	public void removeUser(User user) {
		users.remove(user);
	}

	//Should this throw an exception?
	public void setAllocatedWorkHours(int allocatedWorkHours) {
		if(allocatedWorkHours >= 0)
			this.allocatedWorkHours = allocatedWorkHours;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//Should this throw an exception?
	public void setEndDate(Date endDate) {
		if(hasStartDate() && getStartDate().before(endDate))
			this.endDate = endDate;
	}

	//Should this throw an exception?
	public void setName(String name) {
		if(name != null)
			this.name = name;
	}

	//Should this throw an exception?
	public void setStartDate(Date startDate) {
		if(hasEndDate() && getEndDate().after(startDate))
			this.startDate = startDate;
	}

}