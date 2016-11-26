package ca.main.game.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ca.main.game.Game;

/**
 * @author Legio-PC
 * extended keyboard adapter for game
 */
public class KeyInput extends KeyAdapter{

	Game game;
	
	public KeyInput(Game game){
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e){
		game.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		game.keyReleased(e);
	}
}
