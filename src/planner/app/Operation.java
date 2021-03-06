package planner.app;

public enum Operation {

	ACT_ASSIGN_USER, ACT_REG_HOURS, ACT_SET_ALL_HOURS, ACT_SET_END_DATE, ACT_SET_NAME, ACT_SET_START_DATE, ACT_SET_WORK_HOURS,
	PLANNER_CREATE_PROJECT, PLANNER_LOGIN, PLANNER_REGISTER_USER, PLANNER_REMOVE_PROJ, PLANNER_REMOVE_USER,
	PROJ_ADD_ACT, PROJ_SET_NAME, PROJ_ASSIGN_START_DATE, PROJ_ASSIGN_END_DATE,
	USER_ASSIGN_ACT, USER_REGISTER_WORK, USER_SET_USERNAME,
	WORK_SET_ACT, WORK_SET_FROM_DATE, WORK_SET_TO_DATE;

}