package ca.main.game.network;

import java.util.ArrayList;

public class OtherPlayersList {

	private ArrayList<PlayerMP> otherPlayers;
	
	public OtherPlayersList()
	{
		otherPlayers = new ArrayList<PlayerMP>();
	}
	
	public void addOtherPlayers(PlayerMP otherPlayer)
	{
		otherPlayers.add(otherPlayer);
	}
	
	
}
