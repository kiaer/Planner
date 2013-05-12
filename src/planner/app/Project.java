package planner.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {

	public static final String
			DEFAULT_NAME = "Unnamed",
			MSG_NULL_DATE = "Date must not be null",
			MSG_NULL_NAME = "Project name must not be null",
			MSG_END_BEFORE_START = "Project enddate must be after startdate";

	private String name;
	private User projectLeader;
	private List<Activity> activities = new ArrayList<Activity>();
	private Date startDate;
	private Date endDate;

	public Project(String name, User projectLeader, Date startDate, Date endDate) {
		if (name != null)
			this.name = name;
		else
			this.name = DEFAULT_NAME;
		this.projectLeader = projectLeader;
		if(startDate != null && endDate != null && endDate.before(startDate)) {
			this.startDate = startDate;
			this.endDate = new Date();
			endDate.setTime(startDate.getTime() + Work.DAY_TO_MILISECONDS);
		} else {
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}

	public Project(String name, User projectLeader, Date startDate) {
		this(name, projectLeader, startDate, null);
	}

	public Project(String name, User projectLeader) {
		this(name, projectLeader, null);
	}

	public Project(String name) {
		this(name, null);
	}

	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getName() {
		return name;
	}

	public User getProjectLeader() {
		return projectLeader;
	}

	public Date getStartDate() {
		return startDate;
	}

	public boolean hasProjectLeader() {
		return projectLeader != null;
	}

	public void removeActivity(Activity activity) {
		activities.remove(activity);
	}

	public void removeProjectLeader() {
		setProjectLeader(null);
	}

	public void setEndDate(Date endDate) throws OperationNotAllowedException {
		if(endDate != null) {
			if (startDate == null || endDate.after(startDate)) {
				this.endDate = endDate;
			} else
				throw new OperationNotAllowedException(Operation.PROJECT_ASSIGN_END_DATE, MSG_END_BEFORE_START);
		} else
			throw new OperationNotAllowedException(Operation.PROJECT_ASSIGN_END_DATE, MSG_NULL_DATE);
	}

	public void setProjectLeader(User projectLeader) {
		this.projectLeader = projectLeader;
	}

	public void setName(String name) throws OperationNotAllowedException {
		if (name != null)
			this.name = name;
		else
			throw new OperationNotAllowedException(Operation.PROJ_SET_NAME, MSG_NULL_NAME);
	}

	public void setStartDate(Date startDate) throws OperationNotAllowedException {
		if(startDate != null) {
			if (endDate == null || startDate.before(endDate)) {
				this.startDate = startDate;
			} else
				throw new OperationNotAllowedException(Operation.PROJECT_ASSIGN_END_DATE, MSG_END_BEFORE_START);
		} else
			throw new OperationNotAllowedException(Operation.PROJECT_ASSIGN_END_DATE, MSG_NULL_DATE);
	}

}