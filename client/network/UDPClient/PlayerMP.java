package client.network.UDPClient;

import client.Game;
import client.gfx.Player;


public class PlayerMP extends Player{

	private String ipAddress;
	private String name;
	
	public PlayerMP(Game game, String ipAddress, String name) {
		super(100, 100, game, "applejack");
		this.ipAddress = ipAddress;
		this.name = name;
	}

	public String getIpAddress() {
		return ipAddress;
	}
	
	public String getName(){
		return name;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}	
}
