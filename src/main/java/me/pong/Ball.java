package me.pong;

public class Ball {
	double xpos, ypos;
	double velocityX = 5, velocityY = -5;

	Ball () {
		setPosition (250, 100);
	}

	void move () {
		setPosition (getXpos () + velocityX, getYpos () + velocityY);
	}

	void resetPositionandVelocity () {
		setPosition (400, 225);
		velocityX = 5;
		velocityY = -5;
	}


	void setPosition (double x, double y) {
		xpos = x;
		ypos = y;
	}

	double getXpos () {
		return xpos;
	}

	double getYpos () {
		return ypos;
	}
}
