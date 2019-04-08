package me.pong;

import javax.swing.*;
import java.awt.*;

public class PongGameMain extends Canvas{
	private static final PongGameMain game = new PongGameMain ();
	final JPanel gamePanel = new JPanel ();
	Menu menu;

	public PongGameMain () {
		menu = new Menu ();
	}

	public static void main (String[] args) {
		Menu menu = new Menu ();
		menu.createMenu ();
		game.game ();

	}

	void game () {
		gamePanel.add (game);
	}

}
