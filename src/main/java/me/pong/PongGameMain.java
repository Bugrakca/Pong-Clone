package me.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class PongGameMain extends JPanel implements MouseMotionListener, ActionListener {
	static final PongGameMain game = new PongGameMain ();
	Timer time;
	long currentTime;
	Ball ball;
	Paddles paddles;
	BufferedImage offImage;

	public PongGameMain(){
		addMouseMotionListener (this);
	}

	public static void main (String[] args) {
		Menu menu = new Menu ();
		menu.createMenu ();
		game.initialize ();
	}

	public void initialize () {
		ball = new Ball ();
		paddles = new Paddles ();
		offImage = new BufferedImage (Menu.WIDTH, Menu.HEIGHT, BufferedImage.TYPE_INT_RGB);
		offImage.createGraphics ();
	}

	public void start () {
		currentTime = System.currentTimeMillis ();
		time = new Timer (15, this);
		time.start ();
	}

	public void checkCollision () {
		if (ball.getYpos () == 0 || ball.getYpos () > 400) {//Size 450
			ball.velocityY = (ball.velocityY * -1);
		}
		if ((ball.getXpos () == 20) && hitPaddle ()) {
			ball.velocityX = (ball.velocityX * -1);
		}
		if ((ball.getXpos () == 750 && hitPaddle ())) {
			ball.velocityX = (ball.velocityX * -1);
		}
		if ((ball.getXpos () == 0)) {
			paddles.setScore2 ((paddles.getScore2 () + 1));
			ball.resetPosition ();
		}
		if ((ball.getXpos () == 800)) {
			paddles.setScore1 ((paddles.getScore1 () + 1));
			ball.resetPosition ();
		}

	}

	public boolean hitPaddle () {
		boolean didHit = false;
		if ((paddles.getPosP1 () - 10) <= ball.getYpos () && (paddles.getPosP1 () + 70) > ball.getYpos ()) {
			didHit = true;
		}
		if ((paddles.getPosP2 () - 10) >= ball.getYpos () && (paddles.getPosP2 () + 70) < ball.getYpos ()) {
			didHit = true;
		}
		return didHit;
	}

	@Override
	protected void paintComponent (Graphics g) {
		super.paintComponent (g);
		g.clearRect (0, 0, Menu.WIDTH, Menu.HEIGHT);
		g.drawImage (offImage, 0, 0, null);
		//Draw Paddles
		g.setColor (Color.white);
		g.fillRect (paddles.p1x, paddles.getPosP1 (), 10, 70);
		g.fillRect (paddles.p2x, paddles.getPosP2 (), 10, 70);
		//Draw PONG
		g.setColor (Color.white);
		Font font = new Font ("Monospaced", Font.BOLD, 45);
		FontMetrics metrics = g.getFontMetrics ();
		int fontMet = metrics.stringWidth ("PONG");
		g.setFont (font);
		g.drawString ("PONG", (Menu.WIDTH - fontMet) / 2 - 40, 30);
		//Draw Ball
		g.setColor (Color.white);
		g.fillOval (ball.getXpos (), ball.getYpos (), 10, 10);
		//Wins
		Font fontWin = new Font ("Monospaced", Font.BOLD, 20);
		g.setFont (fontWin);
		if (paddles.getScore1 () == 10) {
			g.drawString ("Player 1 Wins: " + (currentTime / 1000) + "sec.", (Menu.WIDTH - 200) / 2, 150);
		}
		if (paddles.getScore2 () == 10) {
			g.drawString ("Player 2 Wins: " + (currentTime / 1000) + "sec.", (Menu.WIDTH - 200) / 2, 150);
		}
		//Draw Score
		Font fontScore = new Font ("Helvetica", Font.BOLD, 40);
		g.setFont (fontScore);
		g.drawString ("" + paddles.getScore1 (), 100, 35);
		g.drawString ("" + paddles.getScore2 (), 650, 35);
		Toolkit.getDefaultToolkit ().sync ();
	}

	public void mouseDragged (MouseEvent e) {
	}

	public void mouseMoved (MouseEvent e) {
		paddles.setPosP1 (e.getY () - 35);
		paddles.setPosP2 (e.getY () - 35);
	}

	public void actionPerformed (ActionEvent e) {
		if (paddles.getScore1 () == 10 || paddles.getScore2 () == 10) {
			time.stop ();
			currentTime = (System.currentTimeMillis () - currentTime);
		}
		ball.move ();
		checkCollision ();
		repaint ();
	}

//	@Override
//	public void update (Graphics g) {
//		paint (g);
//	}
}