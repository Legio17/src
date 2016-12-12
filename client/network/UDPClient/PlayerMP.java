package client.network.UDPClient;

import java.net.InetAddress;

import client.Game;
import client.gfx.Player;


public class PlayerMP extends Player{

	private String ipAddress;
	private double x, y;
	
	public PlayerMP(Game game, String ipAddress) {
		super(100, 100, game, "applejack");
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}	
}
