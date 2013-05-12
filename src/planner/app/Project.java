package planner.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {

	public static final String DEFAULT_NAME = "Unnamed",
			MSG_NULL_NAME = "Project name must not be null",
			MSG_END_BEFORE_START = "Project enddate must be after startdate";

	private String name;
	private User projectLeader;
	private List<Activity> activities = new ArrayList<Activity>();
	private Date startDate;
	private Date endDate;
	

	// Untested catch block
	public Project(String name, User projectLeader, Date startDate, Date endDate) {
		if (name != null)
			this.name = name;
		else
			this.name = DEFAULT_NAME;
		if (startDate != null) {
			this.startDate = startDate;
		} else {
			this.startDate = Planner.getDate();
		}
		if (endDate != null && startDate.before(endDate)) {
			this.endDate = endDate;
		} else {
			endDate = new Date();
			endDate.setTime(startDate.getTime() + Work.DAY_TO_MILISECONDS);
		}
		this.projectLeader = projectLeader;
	}

	public Project(String name, User projectLeader, Date startDate) {
		if (name != null)
			this.name = name;
		else
			this.name = DEFAULT_NAME;
		this.projectLeader = projectLeader;

		if (startDate != null) {
			this.startDate = startDate;
		} else {
			this.startDate = Planner.getDate();
		}
	}

	public Project(String name, User projectLeader) {
		this(name, projectLeader, Planner.getDate(), null);
	}

	public Project(String name) throws OperationNotAllowedException {
		this(name, null, Planner.getDate(), null);
	}

	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	public List<Activity> getActivities() {
		return activities;
	}
	
	public void removeActivity(Activity activity){
		activities.remove(activity);
	}
	

	public User getProjectLeader() {
		return projectLeader;
	}

	public String getName() {
		return name;
	}

	public boolean hasProjectLeader() {
		return projectLeader != null;
	}

	public void removeProjectLeader() {
		setProjectLeader(null);
	}

	public void setProjectLeader(User projectLeader) {
		this.projectLeader = projectLeader;
	}

	public void setName(String name) throws OperationNotAllowedException {
		if (name != null)
			this.name = name;
		else
			throw new OperationNotAllowedException(Operation.PROJ_SET_NAME,
					MSG_NULL_NAME);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(Date endDate) throws OperationNotAllowedException {
		if (startDate.before(endDate)) {
			this.endDate = endDate;
		} else
			throw new OperationNotAllowedException(
					Operation.PROJECT_ASSIGN_END_DATE, MSG_END_BEFORE_START);
	}

	public Date getEndDate() {
		return endDate;
	}

}