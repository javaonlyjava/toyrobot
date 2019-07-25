package au.com.whispir.app.model;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Robot {
	
		
	@Autowired
	Position position;
		
	public void move(int x, int y, String direction) {
		setCurrentPosition(x, y, direction);
	}

	private void setCurrentPosition(int x, int y, String direction) {
		
		position.setX(x);
		position.setY(y);
		position.setDirection(direction);
		
		
	}

	public Position getCurrentPosition() {
		return position;
	}
	
	
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@PostConstruct
	public void init() {
		position.setX(0);
		position.setY(0);
		position.setDirection("SOUTH-WEST");
	}

}
