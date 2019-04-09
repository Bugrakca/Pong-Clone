package me.pong;

public class Ball {
	private int xpos, ypos;
	int velocityX = 5, velocityY = -5;

	public Ball () {
		setPosition (250, 100);
	}

	void move () {
		setPosition (getXpos () + velocityX, getYpos () + velocityY);
	}

	void resetPosition () {
		setPosition (400, 225);
	}


	void setPosition (int x, int y) {
		xpos = x;
		ypos = y;
	}

	int getXpos () {
		return xpos;
	}

	int getYpos () {
		return ypos;
	}
}
