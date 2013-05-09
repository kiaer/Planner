package planner.app;

import java.util.Date;

public class Work {

	public static final String
			OPERATION_SET_ACT = "Set activity",
			OPERATION_SET_DATE = "Set date",
			OPERATION_SET_HOURS = "Set hours",
			MSG_NEG_HOURS = "Hours must be positive.",
			MSG_NULL_ACT = "Activity can't be null.",
			MSG_NULL_DATE = "Date can't be null.";

	private Date date;
	private double hours;
	private Activity activity;

	public Work(Date date, double hours, Activity activity) {
		try {
			setDate(date);
			setHours(hours);
			setActivity(activity);
		} catch (OperationNotAllowedException e) {
			e.printStackTrace();
			switch(e.getOperation()) {
				case(OPERATION_SET_ACT):
					//Set activity to one of the constant activities
				case(OPERATION_SET_DATE):
					//Set date to todays date (need dateserver)
				case(OPERATION_SET_HOURS):
					this.hours = 0;
			}
		}
	}

	public void setActivity(Activity activity) throws OperationNotAllowedException {
		if(activity != null)
			this.activity = activity;
		else
			throw new OperationNotAllowedException(OPERATION_SET_ACT, MSG_NULL_ACT);
	}

	public void setDate(Date date) throws OperationNotAllowedException {
		if(date != null)
			this.date = date;
		else
			throw new OperationNotAllowedException(OPERATION_SET_DATE, MSG_NULL_DATE);
	}

	public void setHours(double hours) throws OperationNotAllowedException {
		if(hours >= 0)
			this.hours = hours;
		else
			throw new OperationNotAllowedException(OPERATION_SET_HOURS, MSG_NEG_HOURS);
	}

	public Activity getActivity() {
		return activity;
	}

	public Date getDate() {
		return date;
	}

	public double getHours() {
		return hours;
	}

	public int compareTo(Work work) {
		int compare = date.compareTo(work.getDate());
		if(compare == 0) {
			if(hours > work.getHours())
				return 1;
			else if(hours < work.getHours())
				return -1;
			else
				return 0;
		} else
			return compare;
	}

}
