package ca.main.game.network;

import java.net.InetAddress;

import ca.main.game.Game;
import ca.main.game.gfx.Player;


public class PlayerMP extends Player{

	private InetAddress ipAddress;
	
	public PlayerMP(double x, double y, Game game, int model_nr) {
		super(x, y, game, model_nr);
	}

	public InetAddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(InetAddress ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
