package planner.app;

public class OperationNotAllowedException extends Exception {

	private Operation operation;

	public OperationNotAllowedException(Operation operation, String message) {
		super(message);
		this.operation = operation;
	}

	public Operation getOperation() {
		return operation;
	}

}
