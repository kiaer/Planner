package planner.app;

import java.util.Date;

public class Work implements Comparable<Work> {

	public static final long
			HOURS_TO_MILISECONDS = 1000 * 60 * 60,
			DAY_TO_MILISECONDS = 1000 * 60 * 60 * 24;

	public static final String MSG_NULL_ACT = "Activity can't be null.",
			MSG_NULL_DATE = "Date can't be null.",
			MSG_DATE_MISMATCH = "To date must be after from date.";

	private Date fromDate, toDate;
	private Activity activity;

	public Work(Date fromDate, Date toDate, Activity activity) { 
		if (fromDate != null) {
			this.fromDate = fromDate;
			if(toDate != null && fromDate.before(toDate))
				this.toDate = toDate;
			else
				this.toDate = new Date(this.fromDate.getTime() + DAY_TO_MILISECONDS);
		} else {
			if (toDate != null) {
				this.toDate = toDate;
				this.fromDate = new Date(this.toDate.getTime() - DAY_TO_MILISECONDS);
			} else {
				this.fromDate = Planner.getDate();
				this.toDate = new Date(this.fromDate.getTime() + DAY_TO_MILISECONDS);
			}
		}
		if (activity != null)
			this.activity = activity;
		else
			this.activity = ConstantActivities.NONE.getActivity();
	}

	@Override
	public int compareTo(Work work) {
		int compare = fromDate.compareTo(work.getFromDate());
		if (compare == 0) {
			compare = toDate.compareTo(work.getToDate());
			if (compare == 0) {
				return activity.compareTo(work.getActivity());
			} else
				return compare;
		} else
			return compare;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && o.getClass() == Work.class) {
			Work work = (Work) o;
			return fromDate.equals(work.getFromDate())
					&& toDate.equals(work.getToDate())
					&& activity.equals(work.getActivity());
		} else
			return false;
	}

	public void setActivity(Activity activity) throws OperationNotAllowedException {
		if (activity != null)
			this.activity = activity;
		else
			throw new OperationNotAllowedException(Operation.WORK_SET_ACT, MSG_NULL_ACT);
	}

	public void setFromDate(Date fromDate) throws OperationNotAllowedException {
		if (fromDate != null) {
			if(fromDate.before(toDate))
				this.fromDate = fromDate;
			else
				throw new OperationNotAllowedException(Operation.WORK_SET_FROM_DATE, MSG_DATE_MISMATCH);
		} else
			throw new OperationNotAllowedException(Operation.WORK_SET_FROM_DATE, MSG_NULL_DATE);
	}

	public void setToDate(Date toDate) throws OperationNotAllowedException {
		if (toDate != null)
			if (toDate.after(fromDate))
				this.toDate = toDate;
			else
				throw new OperationNotAllowedException(Operation.WORK_SET_TO_DATE, MSG_DATE_MISMATCH);
		else
			throw new OperationNotAllowedException(Operation.WORK_SET_TO_DATE, MSG_NULL_DATE);
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
		return (double) (toDate.getTime() - fromDate.getTime())
				/ HOURS_TO_MILISECONDS;
	}

}