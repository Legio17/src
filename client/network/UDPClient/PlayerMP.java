package client.network.UDPClient;

import client.Game;
import client.gfx.Player;


public class PlayerMP extends Player{

	private String ipAddress;
	
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
