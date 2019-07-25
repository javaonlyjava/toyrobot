package au.com.whispir.app.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.whispir.app.model.Position;
import au.com.whispir.app.model.Robot;
import au.com.whispir.app.model.UserMove;

@Component
public abstract class BaseCommand implements Command {
	
	@Autowired
	public Robot robot;
	
	public UserMove userMove;

	
	@Override
	public void execute() {
		if (!(this instanceof PlaceCommand) && !(this instanceof ReportCommand) && !checkRobotOnTable()) {
			System.err.println("Ignoring the " + this + " command");
			return;
		}
		
		executeImpl(userMove);
		

	}
	
	private boolean checkRobotOnTable() {
		Position currentPosition = robot.getCurrentPosition();
		int x = currentPosition.getX();
		int y = currentPosition.getY();
		if (x >=0 && x<5 && y>=0 && y<5) {
			return true;
		}
		return false;
	}

	@Override
	public void setUserMove(UserMove userMove) {
		this.userMove = userMove;
	}

	
		
	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public abstract void executeImpl(UserMove userMove);
	
	

}

	