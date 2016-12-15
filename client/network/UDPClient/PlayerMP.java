package client.network.UDPClient;

import java.awt.Graphics;

import client.Game;
import client.gfx.FontLoader;
import client.gfx.Player;


public class PlayerMP extends Player{

	private String ipAddress;
	private String name;
	private FontLoader fontUserName;
	
	public PlayerMP(Game game, String ipAddress, String name) {
		super(100, 100, game, "applejack");
		this.ipAddress = ipAddress;
		this.name = name;
		fontUserName = new FontLoader(game);
		fontUserName.setNickName(name);
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
	
	public void renderNick(Graphics g){
		fontUserName.renderNick(g, 3, (int)getX(), (int)getY(), 13);
	}
}
