package au.com.whispir.app.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import au.com.whispir.app.cmd.BaseCommand;
import au.com.whispir.app.cmd.LeftCommand;
import au.com.whispir.app.cmd.MoveCommand;
import au.com.whispir.app.cmd.PlaceCommand;
import au.com.whispir.app.cmd.ReportCommand;
import au.com.whispir.app.cmd.RightCommand;
import au.com.whispir.app.exception.RobotException;
import au.com.whispir.app.model.Position;
import au.com.whispir.app.model.Robot;

@RunWith(SpringRunner.class)
public class MakeRobotMoveServiceTest {
	
	MakeRobotMoveService makeRobotMoveService;
	BaseCommand placeCommand;
	BaseCommand moveCommand;
	BaseCommand leftCommand;
	BaseCommand rightCommand;
	BaseCommand reportCommand;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@Before
	public void setUp() {
		makeRobotMoveService = new MakeRobotMoveService();
		//PlaceCommand placeCommand = mock(PlaceCommand.class);
		//MoveCommand moveCommand = mock(MoveCommand.class);
		//ReportCommand reportCommand = mock(ReportCommand.class);
		Robot robot = new Robot();
		Position position = new Position();
		robot.setPosition(position);
		placeCommand = new PlaceCommand();
		moveCommand = new MoveCommand();
		reportCommand = new ReportCommand();
		leftCommand = new LeftCommand();
		rightCommand = new RightCommand();
		
		placeCommand.setRobot(robot);
		moveCommand.setRobot(robot);
		reportCommand.setRobot(robot);
		leftCommand.setRobot(robot);
		rightCommand.setRobot(robot);
		
		makeRobotMoveService.setPlaceCommand(placeCommand); 
		makeRobotMoveService.setMoveCommand(moveCommand); 
		makeRobotMoveService.setReportCommand(reportCommand);
		makeRobotMoveService.setLeftCommand(leftCommand);
		makeRobotMoveService.setRightCommand(rightCommand);
		
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	/**
	 *  commands_place_and_move_test.txt
	 *  
	 *  PLACE 0,0,NORTH
		MOVE
		REPORT
	 * 
	 */
	@Test
	public void whenEnteredPlaceAndMoveCommandsInFile_thenReturnFinalPosition() throws RobotException {
	  
		String[] arguments= new String[] { "commands_place_and_move_test.txt"};
		
	    makeRobotMoveService.makeAllTheMoves(arguments);
	 	   
	    assertEquals("0,1,NORTH".concat("\n"), outContent.toString().replace("\r\n", "\n"));
	}
	
	/**
	 *  commands_place_and_left_test.txt
	 *  
	 *  PLACE 0,0,NORTH
		LEFT
		REPORT
	 * 
	 */
	@Test
	public void whenEnteredPlaceAndLeftCommandsInFile_thenReturnFinalPosition() throws RobotException {
	    String[] arguments= new String[] {"commands_place_and_left_test.txt"} ;
	    
		makeRobotMoveService.makeAllTheMoves(arguments);
		
	 	assertEquals("0,0,WEST".concat("\n"), outContent.toString().replace("\r\n", "\n"));
	    
	}
	
	/**
	 *  commands_place_and_move_and_left_test.txt
	 *  
	 *  PLACE 1,2,EAST
		MOVE
		MOVE
		LEFT
		MOVE
		REPORT
	 * 
	 */
	@Test
	public void whenEnteredPlaceMoveAndMoveAndLeftAndMoveCommandsInFile_thenReturnFinalPosition() throws RobotException {
	  
		String[] arguments= new String[] {"commands_place_and_move_and_left_test.txt"} ;
				
	    makeRobotMoveService.makeAllTheMoves(arguments);
	 	
	    assertEquals("3,3,NORTH".concat("\n"), outContent.toString().replace("\r\n", "\n"));
	    
	}
	
	/**
	 *  PLACE 1,2,EAST
		MOVE
		MOVE
		RIGHT
		MOVE
		REPORT
	 * 
	 */
	@Test
	public void whenEnteredPlaceMoveAndRightCommandsInFile_thenReturnFinalPosition() throws RobotException {
	  
		String[] arguments= new String[] {"commands_place_and_move_and_right_test.txt"} ;
				
	    makeRobotMoveService.makeAllTheMoves(arguments);
	 	
	    assertEquals("3,1,SOUTH".concat("\n"), outContent.toString().replace("\r\n", "\n"));
	    
	}
	
	
	/** commands_left_and_place_and_move_test.txt
	 *  LEFT
		MOVE
		PLACE 0,1,NORTH
		MOVE
		REPORT
	 */
	@Test
	public void whenEnteredLeftCommandOnFirstLine_thenIgnoreTheLeftCommand() throws RobotException {
		String[] arguments= new String[] {"commands_left_and_place_and_move_test.txt"} ;
		
	    makeRobotMoveService.makeAllTheMoves(arguments);
	 	
	    assertEquals("0,2,NORTH".concat("\n"), outContent.toString().replace("\r\n", "\n"));
	}
	
	/**
	 *  commands_that_can_make_robot_fall_test.txt
	 *  
	 *  PLACE 4,1,EAST
		MOVE
		MOVE
		MOVE
		REPORT
	 * 
	 */
	@Test
	public void whenEnteredACommandThatCanMakeRobotFall_thenIgnoreTheCommand() throws RobotException {
		String[] arguments= new String[] {"commands_that_can_make_robot_fall_test.txt"} ;
		
	    makeRobotMoveService.makeAllTheMoves(arguments);
	 	
	    assertEquals("5,1,EAST".concat("\n"), outContent.toString().replace("\r\n", "\n"));
	}
	
	
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

}
