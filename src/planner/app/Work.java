package planner.app;

import java.util.Date;

public class Work {

	private static final long
			HOURS_TO_MILISECONDS = 1000 * 60 * 60,
			DAY_TO_MILISECONDS = 1000 * 60 * 60 * 24;

	public static final String
			MSG_NULL_ACT = "Activity can't be null.",
			MSG_NULL_DATE = "Date can't be null.",
			MSG_DATE_MISMATCH = "To date must be after from date.";

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
					if(toDate != null) {
						fromDate = new Date();
						fromDate.setTime(fromDate.getTime() - DAY_TO_MILISECONDS);
					} else
						fromDate = Planner.getDate().getTime();
					break;
				case WORK_SET_NULL_TO_DATE:
				case WORK_DATE_MISMATCH:
					toDate = new Date();
					toDate.setTime(fromDate.getTime() + DAY_TO_MILISECONDS);
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
			this.fromDate = fromDate;
		else
			throw new OperationNotAllowedException(Operation.WORK_SET_NULL_FROM_DATE, MSG_NULL_DATE);
	}

	public void setToDate(Date toDate) throws OperationNotAllowedException {
		if(toDate != null)
			if(fromDate != null && fromDate.after(toDate))
				throw new OperationNotAllowedException(Operation.WORK_DATE_MISMATCH, MSG_DATE_MISMATCH);
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
		return (double) (toDate.getTime() - fromDate.getTime()) / HOURS_TO_MILISECONDS;
	}

	public int compareTo(Work work) {
		return fromDate.compareTo(work.getFromDate());
	}

}