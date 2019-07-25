package au.com.whispir.app.model;

import org.springframework.stereotype.Component;

@Component
public class Position {
	
	int x;
	
	int y;
	
	String direction;
	
	public Position() {
		this.x  = 0;
		this.y=0;
		this.direction="SOUTH-WEST";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	

}
