package me.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class PongGameMain extends JPanel implements MouseMotionListener, ActionListener {
	private Timer time;
	private long currentTime;
	private Ball ball;
	private Paddles paddles;
	private BufferedImage offImage;

	PongGameMain () {
		addMouseMotionListener (this);
	}

	public static void main (String[] args) {
		Menu menu = new Menu ();
		menu.createMenu ();
		Menu.game.initialize ();
	}

	private void initialize () {
		ball = new Ball ();
		paddles = new Paddles ();
		offImage = new BufferedImage (Menu.WIDTH, Menu.HEIGHT, BufferedImage.TYPE_INT_RGB);
		offImage.createGraphics ();
	}

	void start () {
		currentTime = System.currentTimeMillis ();
		time = new Timer (15, this);
		time.start ();
	}

	private void checkCollision () {
		if (ball.getYpos () == 0 || ball.getYpos () > 400) {//Size 450
			ball.velocityY = (ball.velocityY * -1.03);
		}
		if (ball.getYpos () <= 0) {
			ball.ypos = 0;
			ball.velocityY = -ball.velocityY;
		}
		if ((ball.getXpos () == 20) && hitPaddle ()) {
			ball.velocityX = (ball.velocityX * -1.03);
			ball.xpos = paddles.p1x + 10;
		}
		if (ball.getXpos () < 20 && hitPaddle ()) {
			ball.xpos = 20;
			ball.velocityX = -ball.velocityX;
		}
		if ((ball.getXpos () == 755) && hitPaddle ()) {
			ball.xpos = paddles.p2x;
			ball.velocityX = (ball.velocityX * -1.03);
		}
		if (ball.getXpos () > 760 && hitPaddle ()) {
			ball.xpos = 755;
			ball.velocityX = -ball.velocityX;
		}
		if ((ball.getXpos () <= 0)) {
			paddles.setScore2 ((paddles.getScore2 () + 1));
			ball.resetPositionAndVelocity ();
		}
		if ((ball.getXpos () >= 800)) {
			paddles.setScore1 ((paddles.getScore1 () + 1));
			ball.resetPositionAndVelocity ();
		}

	}

	private boolean hitPaddle () {
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
		g.fillOval ((int) ball.getXpos (), (int) ball.getYpos (), 10, 10);
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
}