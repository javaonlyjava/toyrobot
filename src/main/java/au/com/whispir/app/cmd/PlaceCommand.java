package au.com.whispir.app.cmd;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import au.com.whispir.app.model.UserMove;

@Component
@Qualifier("placeCommand")
public class PlaceCommand extends BaseCommand {
	
	
	@Override
	public void executeImpl(UserMove userMove) {
		setNewPositionDynamics(userMove.getX(), userMove.getY(), userMove.getDirection());
			
	}

	private void setNewPositionDynamics(int newX, int newY, String newDirection) {
		
		int refactoredX = newX;
		int refactoredY = newY;
		String refactoredDirection = newDirection;
		
		robot.move(refactoredX, refactoredY, refactoredDirection);
	
		
	}

	


}
