package me.pong;

public class Ball {
	private int xpos, ypos;
	private int velocityX, velocityY;

	void Ball () {
		setPosition (250, 250);
	}

	void move () {
		setPosition (getXpos () + getVelocityX (), getYpos () + getVelocityY ());
	}

	void resetPosition () {
		setPosition (250, 250);
	}

	void setDirectionX (int dx) {
		dx = (int) (Math.random () * 5);
		velocityX = dx;
	}

	void setDirectionY (int dy) {
		dy = (int) (Math.random () * -6);
		velocityY = dy;
	}

	int getVelocityX () {
		return velocityX;
	}

	int getVelocityY () {
		return velocityY;
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
