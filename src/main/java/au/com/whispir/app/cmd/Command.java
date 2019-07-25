package au.com.whispir.app.cmd;

import org.springframework.stereotype.Component;

import au.com.whispir.app.model.UserMove;

@Component
public interface Command {
	
	
	public void setUserMove(UserMove userMove);
	
	public void execute();

}
