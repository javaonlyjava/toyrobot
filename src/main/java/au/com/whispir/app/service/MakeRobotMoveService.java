package au.com.whispir.app.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import au.com.whispir.app.cmd.Command;
import au.com.whispir.app.exception.RobotException;
import au.com.whispir.app.model.UserMove;

@Service
public class MakeRobotMoveService  {
	
	@Autowired
	@Qualifier("placeCommand")
	Command placeCommand;
	
	@Autowired
	@Qualifier("reportCommand")
	Command reportCommand;
	
	@Autowired
	@Qualifier("moveCommand")
	Command moveCommand;
	
	public Command getPlaceCommand() {
		return placeCommand;
	}

	public void setPlaceCommand(Command placeCommand) {
		this.placeCommand = placeCommand;
	}

	public Command getReportCommand() {
		return reportCommand;
	}

	public void setReportCommand(Command reportCommand) {
		this.reportCommand = reportCommand;
	}

	public Command getMoveCommand() {
		return moveCommand;
	}

	public void setMoveCommand(Command moveCommand) {
		this.moveCommand = moveCommand;
	}

	public Command getLeftCommand() {
		return leftCommand;
	}

	public void setLeftCommand(Command leftCommand) {
		this.leftCommand = leftCommand;
	}

	public Command getRightCommand() {
		return rightCommand;
	}

	public void setRightCommand(Command rightCommand) {
		this.rightCommand = rightCommand;
	}

	@Autowired
	@Qualifier("leftCommand")
	Command leftCommand;
	
	@Autowired
	@Qualifier("rightCommand")
	Command rightCommand;
	
	private static final String PLACE = "PLACE";
	private static final String MOVE = "MOVE";
	private static final String REPORT = "REPORT";
	private static final String LEFT = "LEFT";
	private static final String RIGHT = "RIGHT";

	public void makeAllTheMoves(String[] args) throws RobotException {
		if (args==null) {
			throw new RobotException("Please enter an input file with commands");
		} else {
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(args[0]);
			readFromInputStream(inputStream);
		}
		
	}

	private void move() {
		moveCommand.execute();
		
	}
	
	private void left() {
		leftCommand.execute();
		
	}

	private void right() {
		rightCommand.execute();
		
	}


	private void report() {
		reportCommand.execute();
		
	}

	private void place(int x, int y, String direction) {
		placeCommand.setUserMove(new UserMove(x, y, direction));
		placeCommand.execute();
		
	}
	
	private void readFromInputStream(InputStream inputStream)  throws RobotException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			 int lineNumber = 1;	 
		     String line;
		     while ((line = br.readLine()) != null) {
		    	   if (lineNumber ==1 && !line.startsWith(PLACE)) {
		    		   // discard command
		    	   } else {
		    		   lineNumber++;
		    		   readandExecuteCommand(line);
		    		   if (line.startsWith(REPORT)) {
		    			   // reseting line number to 1 to execute other sequence of commands beginning with PLACE
		    			   // so that we can group few set of commands together.
		    			   lineNumber=1;
		    		   }
		    	   }
		    	   
		     }
		 } catch (Exception ex) {
			    	throw new RobotException (ex);
		}
		
	}

	private void readandExecuteCommand(String line) throws RobotException {
		if (line!=null) {
			String[] lineArgs = convertLinetoLineArgs(line);
			String command= lineArgs[0];
			String x="";
			String y=""; 
			String direction="";
			if (lineArgs.length ==4 && command.equalsIgnoreCase(PLACE)) {
				x=lineArgs[1];
				y=lineArgs[2];
				direction=lineArgs[3];
				
			} else if (lineArgs.length ==4 && !command.equalsIgnoreCase(PLACE)) {
				throw new RobotException("Command entered is incorrect");
			}
			switch (command) {
				case PLACE : place(Integer.parseInt(x),Integer.parseInt(y), direction);
					break;
				case MOVE: move();
					break;
				case REPORT: report();
					break;
				case LEFT: left();
					break;
				case RIGHT: right();
					break;
				
			}
		} else {
			throw new RobotException("Please enter an input file with commands");
		}
		
	}

	private String[] convertLinetoLineArgs(String line) {
		List<String> argsList = new ArrayList<String>();
		if (line.contains(" ")) {
			String[] spaceSplit = line.split(" ");
			argsList.add(spaceSplit[0]);
			if (spaceSplit.length > 1 && (spaceSplit[1] !=null || spaceSplit[1] !="" || spaceSplit[1] !=" ")) {
				String[] commaSplit = spaceSplit[1].split(",");
				argsList.add(commaSplit[0]);
				argsList.add(commaSplit[1]);
				argsList.add(commaSplit[2]);
				
			}
		} else {
			argsList.add(line);
		}
		 
		
		String[] lineArgs = new String[argsList.size()];
		return argsList.toArray(lineArgs);
	}
	
	

}
