package planner.app;

import java.util.ArrayList;
import java.util.List;

public class Activity {

	public static final int DEFAULT_ALL_WORK_HOURS = 0;
	public static final String DEFAULT_DESCRIPTION = "";

	private int allocatedWorkHours;
	private String name, description;
	private List<User> users = new ArrayList<User>();

	public Activity(String name, String description, int allocatedWorkHours) {
		setDescription(description);
		setName(name);
		setAllocatedWorkHours(allocatedWorkHours);
	}

	public Activity(String name, String description) {
		this(name, description, DEFAULT_ALL_WORK_HOURS);
	}

	//Untested
	public Activity(String name, int allocatedWorkHours) {
		this(name, DEFAULT_DESCRIPTION, allocatedWorkHours);
	}

	//Untested
	public Activity(String name) {
		this(name, DEFAULT_DESCRIPTION, DEFAULT_ALL_WORK_HOURS);
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

	public List<User> getUsers() {
		return users;
	}

	public String getName() {
		return name;
	}

	public void removeUser(User user) {
		users.remove(user);
	}

	//Should this throw an exception?
	public void setDescription(String description) {
		if(description != null)
			this.description = description;
	}

	//Should this throw an exception?
	public void setName(String name) {
		if(name != null)
			this.name = name;
	}

	//Should this throw an exception?
	public void setAllocatedWorkHours(int allocatedWorkHours) {
		if(allocatedWorkHours >= 0)
			this.allocatedWorkHours = allocatedWorkHours;
	}

}