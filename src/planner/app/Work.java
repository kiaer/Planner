package planner.app;

import java.util.Date;

public class Work {

	private static final double MILISECONDS_TO_HOURS = 1./(1000 * 60 * 60);

	public static final String
			MSG_NULL_ACT = "Activity can't be null.",
			MSG_NULL_DATE = "Date can't be null.",
			MSG_EARLY_TO_DATE = "To date must be after from date.",
			MSG_LATE_FROM_DATE = "from date must be before to date.";

	private Date fromDate, toDate;
	private Activity activity;

	public Work(Date fromDate, Date toDate, Activity activity) {
		try {
			setFromDate(fromDate);
			setToDate(toDate);
			setActivity(activity);
		} catch (OperationNotAllowedException e) {
			e.printStackTrace(); 
			switch(e.getOperation()) {
				case WORK_SET_ACT:
					activity = ConstantActivities.NONE.getActivity();
					break;
				case WORK_SET_NULL_FROM_DATE:
					//Set date to todays date
					break;
				case WORK_SET_LATE_FROM_DATE:
					//Set date to before to date
					break;
				case WORK_SET_NULL_TO_DATE:
					//set date to tomorrow date
					break;
				case WORK_SET_EARLY_TO_DATE:
					//Set date to after from date
					break;
				default:
					break;
			}
		}
	}

	public void setActivity(Activity activity) throws OperationNotAllowedException {
		if(activity != null)
			this.activity = activity;
		else
			throw new OperationNotAllowedException(Operation.WORK_SET_ACT, MSG_NULL_ACT);
	}

	public void setFromDate(Date fromDate) throws OperationNotAllowedException {
		if(fromDate != null)
			if(toDate != null && toDate.before(fromDate))
				throw new OperationNotAllowedException(Operation.WORK_SET_LATE_FROM_DATE, MSG_LATE_FROM_DATE);
			else
				this.fromDate = fromDate;
		else
			throw new OperationNotAllowedException(Operation.WORK_SET_NULL_FROM_DATE, MSG_NULL_DATE);
	}

	public void setToDate(Date toDate) throws OperationNotAllowedException {
		if(toDate != null)
			if(fromDate != null && fromDate.after(toDate))
				throw new OperationNotAllowedException(Operation.WORK_SET_EARLY_TO_DATE, MSG_EARLY_TO_DATE);
			else
				this.toDate = toDate;
		else
			throw new OperationNotAllowedException(Operation.WORK_SET_NULL_TO_DATE, MSG_NULL_DATE);
	}

	public Activity getActivity() {
		return activity; 
	}

	public Date getFromDate() {
		return fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public double getHours() {
		return (toDate.getTime() - fromDate.getTime()) * MILISECONDS_TO_HOURS;
	}

	public int compareTo(Work work) {
		return fromDate.compareTo(work.getFromDate());
	}

}
