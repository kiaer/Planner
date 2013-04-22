package planner.app;

public class OperationNotAllowedException extends Exception {

	private String operation;

	public OperationNotAllowedException(String operation, String message) {
		super(message);
		this.setOperation(operation);

	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
