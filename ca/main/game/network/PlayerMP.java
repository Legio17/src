package ca.main.game.network;

import java.net.InetAddress;

import ca.main.game.Game;
import ca.main.game.gfx.Player;


public class PlayerMP extends Player{

	public InetAddress ipAddress;
	public int port;
	
	public PlayerMP(double x, double y, Game game, int model_nr) {
		super(x, y, game, model_nr);

	}

	
}
