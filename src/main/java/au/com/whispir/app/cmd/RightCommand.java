package au.com.whispir.app.cmd;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import au.com.whispir.app.model.UserMove;

@Component
@Qualifier("rightCommand")
public class RightCommand extends BaseCommand {
	
	
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
			case "NORTH":  refactoredDirection = "EAST";
		    	break;
		    
			case "SOUTH":  refactoredDirection = "WEST";
			   break;
			   
			case "EAST":  refactoredDirection = "SOUTH";
			   break;
			   
			case "WEST":  refactoredDirection = "NORTH";
			   break;
		}
		
		
		robot.move(refactoredX, refactoredY, refactoredDirection);
		
	}

	


}
