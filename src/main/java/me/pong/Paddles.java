package me.pong;

public class Paddles {
	final int p1x = 15, p2x = 760;
	int p1y = 0, p2y = 0;
	int score1, score2;

	Paddles () {
		setPosP1 (200);
		setScore1 (0);
		setPosP2 (200);
		setScore2 (0);
	}

	//Start Pos
	void setPosP1 (int y) {
		p1y = y;

		if (p1y > 340) {
			setPosP1 (340);
		}
		else if (p1y < 0) {
			setPosP1 (0);
		}
	}

	//Start Pos
	void setPosP2 (int y) {
		p2y = y;

		if (p2y > 340) {
			setPosP2 (340);
		}
		else if (p2y < 0) {
			setPosP2 (0);
		}
	}

	void setScore1 (int score1) {
		this.score1 = score1;
	}

	int getScore1 () {
		return score1;
	}

	void setScore2 (int score2) {
		this.score2 = score2;
	}

	int getScore2 () {
		return score2;
	}


	int getPosP1 () {
		return p1y;
	}

	int getPosP2 () {
		return p2y;
	}
}
