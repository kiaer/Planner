package planner.app;

public class Project {
	
	private String projectName;
	private String projectLeader;

	public Project(String projectName, String projectLeader) {
	
		this.projectName = projectName;
		this.projectLeader = projectLeader;
	
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}

}
