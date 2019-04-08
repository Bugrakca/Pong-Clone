package me.pong;

public class Paddles {
	final int p1x = 15, p2x = 440;
	int p1y = 0, p2y = 0;
	int score1, score2;

	public Paddles(){
		setScore1 (0);
		setScore2 (0);
	}

	//Start Pos
	void setPosP1 (int y) {
		p1y = y;

		if (p1y > 800) {
			setPosP1 (800);
		}
		else if (p1y < 0) {
			setPosP1 (0);
		}
	}
//Start Pos
	void setPosP2 (int y) {
		p2y = y;

		if (p2y > 800) {
			setPosP2 (800);
		}
		else if (p2y < 0) {
			setPosP2 (0);
		}
	}

	public void setScore1 (int score1){
		this.score1 = score1;
	}

	public int getScore1(){
		return score1;
	}

	public void setScore2 (int score2){
		this.score2 = score2;
	}

	public int getScore2(){
		return score2;
	}


	int getPosP1 () {
		return p1y;
	}

	int getPosP2 () {
		return p2y;
	}
}
