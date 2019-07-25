package au.com.whispir.app.exception;

public class RobotException extends Exception {
	
	public RobotException(String errorMessage) {
		super(errorMessage);
	}

	public RobotException(Exception ex) {
		super(ex.getMessage());
	}

}
