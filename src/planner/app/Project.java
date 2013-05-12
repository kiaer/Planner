package planner.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {

	public static final String
			DEFAULT_NAME = "Unnamed",
			MSG_NULL_ACT = "Activities must not be null.",
			MSG_NULL_DATE = "Date must not be null.",
			MSG_NULL_NAME = "Project name must not be null.",
			MSG_END_BEFORE_START = "Project end date must be after start date.";

	private String name;
	private User projectLeader;
	private List<Activity> activities = new ArrayList<Activity>();
	private Date startDate;
	private Date endDate;

	public Project(String name, User projectLeader) {
		if (name != null)
			this.name = name;
		else
			this.name = DEFAULT_NAME;
		this.projectLeader = projectLeader;
	}

	public Project(String name) {
		this(name, null);
	}

	public void addActivity(Activity activity) throws OperationNotAllowedException {
		if(activity != null)
			activities.add(activity);
		else
			throw new OperationNotAllowedException(Operation.PROJ_ADD_ACT, MSG_NULL_ACT);
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
		if (endDate == null || startDate == null || endDate.after(startDate)) {
			this.endDate = endDate;
		} else
			throw new OperationNotAllowedException(Operation.PROJ_ASSIGN_END_DATE, MSG_END_BEFORE_START);
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
		if (startDate == null || endDate == null || startDate.before(endDate)) {
			this.startDate = startDate;
		} else
			throw new OperationNotAllowedException(Operation.PROJ_ASSIGN_START_DATE, MSG_END_BEFORE_START);
	}

}