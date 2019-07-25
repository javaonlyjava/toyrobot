package au.com.whispir.app.cmd;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import au.com.whispir.app.model.UserMove;

@Component
@Qualifier("leftCommand")
public class LeftCommand extends BaseCommand {
	
	
	@Override
	public void executeImpl(UserMove userMove) {
		
		setNewPositionDynamics(robot.getCurrentPosition().getDirection());
		
		
	}

	private void setNewPositionDynamics(String originalDirection) {
		int originalPositionX = robot.getCurrentPosition().getX();
		int originalPositionY =  robot.getCurrentPosition().getY();
			
		int refactoredX = originalPositionX;
		int refactoredY = originalPositionY;
		String refactoredDirection = "";
		
		switch(originalDirection) {
			case "NORTH":  refactoredDirection = "WEST";
		    	break;
		    
			case "SOUTH":  refactoredDirection = "EAST";
			   break;
			   
			case "EAST":  refactoredDirection = "NORTH";
			   break;
			   
			case "WEST":  refactoredDirection = "SOUTH";
			   break;
		}
		
		
		robot.move(refactoredX, refactoredY, refactoredDirection);
		
	}

	


}
