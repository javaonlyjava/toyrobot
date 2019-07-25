package au.com.whispir.app.model;

public class UserMove {
	
	int x;
	
	int y;
	
	String direction;

	public UserMove(int x, int y, String direction) {
		this.x = x;
		this.y=y;
		this.direction = direction;
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
