package au.com.whispir.app.cmd;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import au.com.whispir.app.model.UserMove;

@Component
@Qualifier("reportCommand")
public class ReportCommand extends BaseCommand {

	@Override
	public void executeImpl(UserMove userMove) {
		System.out.println(robot.getCurrentPosition().getX() + "," + robot.getCurrentPosition().getY() + "," + robot.getCurrentPosition().getDirection());
		
	}

}
