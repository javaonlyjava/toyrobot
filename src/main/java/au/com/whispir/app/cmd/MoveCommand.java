package au.com.whispir.app.cmd;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import au.com.whispir.app.model.UserMove;

@Component
@Qualifier("moveCommand")
public class MoveCommand extends BaseCommand {
	
	
	private static final int ONE_MOVE = 1;

	@Override
	public void executeImpl(UserMove userMove) {
		
		setNewPositionDynamics(robot.getCurrentPosition().getDirection());
		
		
	}

	private void setNewPositionDynamics(String originalDirection) {
		int originalPositionX = robot.getCurrentPosition().getX();
		int originalPositionY =  robot.getCurrentPosition().getY();
			
		int refactoredX = 0;
		int refactoredY =0;
		String refactoredDirection = "";
		
		switch(originalDirection) {
			case "NORTH":  refactoredX = originalPositionX;
						   refactoredY = originalPositionY + ONE_MOVE;
		    	break;
		    
			case "SOUTH":  refactoredX = originalPositionX;
			   			   refactoredY = originalPositionY - ONE_MOVE;
			   break;
			   
			case "EAST":  refactoredX = originalPositionX +  ONE_MOVE;
						  refactoredY = originalPositionY;
			   break;
			   
			case "WEST":  refactoredX = originalPositionX - ONE_MOVE;
			   			  refactoredY = originalPositionY;
			   break;
		}
		
		refactoredDirection = originalDirection;
		robot.move(refactoredX, refactoredY, refactoredDirection);
		
	}

	


}
