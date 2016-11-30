package ca.main.game.network;

import java.net.InetAddress;

import ca.main.game.Game;
import ca.main.game.gfx.Player;


public class PlayerMP extends Player{

	private String ipAddress;
	private double x, y;
	
	public PlayerMP(Game game, String ipAddress) {
		super(-5, -5, game, "applejack");
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
