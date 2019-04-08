package me.pong;

public class Paddles {
	private static final int p1x = 15, p2x = 150;
	int p1y = 0, p2y = 0;

	void setPosP1 (int y) {
		p1y = y;

		if (p1y > 500) {
			setPosP1 (500);
		}
		else if (p1y < 0) {
			setPosP1 (0);
		}
	}

	void setPosP2 (int y) {
		p2y = y;
		if (p2y > 500) {
			setPosP2 (500);
		}
		else if (p2y < 0) {
			setPosP2 (0);
		}
	}

	int getPosP1 () {
		return p1y;
	}

	int getPosP2 () {
		return p2y;
	}
}
