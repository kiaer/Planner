package planner.app;

public class OperationNotAllowedException extends Exception {

	private String operation;

	public OperationNotAllowedException(String operation, String message) {
		super(message);
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

}
