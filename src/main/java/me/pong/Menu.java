package me.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel implements MouseMotionListener, MouseListener{
	public static final int WIDTH = 800, HEIGHT = WIDTH / 16 * 9;
	static JFrame frame;
	int widthS, widthE;
	Font font;
	Color startB = Color.white, exitB = Color.white;


	public Menu () {
		this.addMouseMotionListener (this);
		this.addMouseListener (this);
	}

	void createMenu () {
		frame = new JFrame ("PONG");
		frame.setSize (WIDTH, HEIGHT);
		frame.addWindowListener (new WindowAdapter () {
			@Override
			public void windowClosing (WindowEvent e) {
				System.err.println ("Exiting Game...");
				System.exit (0);
			}
		});
		frame.setResizable (false);
		frame.setFocusable (true);
		frame.add (new Menu ());
		frame.setLocationRelativeTo (null);
		frame.setVisible (true);
	}

	private boolean mouseOver (int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}
			else return false;
		}
		else return false;
	}

	public void menuDraw (Graphics g) {
		//Draw Title
		Font newFont = new Font ("Monospaced", Font.BOLD, 60);
		FontMetrics metrics = g.getFontMetrics (newFont);
		g.setFont (newFont);
		int width = metrics.stringWidth ("WELCOME PONG");
		g.setColor (Color.orange);
		g.drawString ("WELCOME PONG", (WIDTH - width) / 2, 50);
		//Draw Start
		font = new Font ("Monospaced", Font.BOLD, 50);
		FontMetrics fontMetrics = g.getFontMetrics (font);
		widthS = fontMetrics.stringWidth ("START");
		g.setFont (font);
		g.setColor (startB);
		g.drawString ("START", (WIDTH - widthS) / 2, 200);
		g.drawRect (320, 145, 160, 80);
		//Draw Exit
		font = font.deriveFont (Font.BOLD, 50);
		g.setFont (font);
		fontMetrics = g.getFontMetrics (font);
		widthE = fontMetrics.stringWidth ("EXIT");
		g.setColor (exitB);
		g.drawString ("EXIT", (WIDTH - widthE) / 2, 300);
		g.drawRect (320, 245, 160, 80);
	}


	@Override
	protected void paintComponent (Graphics g) {
		super.paintComponent (g);
		g.setColor (Color.black);
		g.fillRect (0, 0, WIDTH, HEIGHT);
		menuDraw (g);
	}

	public void mouseClicked (MouseEvent e) {
		if (mouseOver (e.getX (), e.getY (), 315, 140, 170, 90)) {
			this.removeMouseMotionListener (this);
			this.removeMouseListener (this);
			frame.invalidate ();
			new PongGameMain ();
			frame.getContentPane ().add(PongGameMain.gamePanel);
			PongGameMain.game.start ();
			frame.validate ();
			System.out.println ("Click");
		}

		if (mouseOver (e.getX (), e.getY (), 315, 240, 170, 90)) {
			System.err.println ("Exiting Game...");
			System.exit (0);
		}
	}

	public void mousePressed (MouseEvent e) {

	}

	public void mouseReleased (MouseEvent e) {

	}

	public void mouseEntered (MouseEvent e) {

	}

	public void mouseExited (MouseEvent e) {

	}

	public void mouseDragged (MouseEvent e) {

	}

	public void mouseMoved (MouseEvent e) {
		if (mouseOver (e.getX (), e.getY (), 315, 140, 170, 90)) {
			startB = Color.green;
			repaint (320, 145, 170, 90);
			System.out.println ("Start");
		}
		else {
			startB = Color.white;
			repaint (320, 145, 170, 90);
		}

		if (mouseOver (e.getX (), e.getY (), 315, 240, 170, 90)) {
			exitB = Color.red;
			repaint (320, 245, 170, 90);
			System.out.println ("Exit");
		}
		else {
			exitB = Color.white;
			repaint (320, 245, 170, 90);
		}
	}
}
