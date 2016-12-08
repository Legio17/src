package ca.main.game.network;

import java.util.ArrayList;

public class OtherPlayersList {

	private ArrayList<PlayerMP> otherPlayers;
	
	public OtherPlayersList()
	{
		otherPlayers = new ArrayList<PlayerMP>();
	}
	
	public void addOtherPlayer(PlayerMP otherPlayer)
	{
		otherPlayers.add(otherPlayer);
	}
	
	public int size()
	{
		return otherPlayers.size();
	}
	
	public PlayerMP get(int i)
	{
		return otherPlayers.get(i);
	}
	
	public String getName(int i)
	{
		return otherPlayers.get(i).getName();
	}
	
	public void removeOtherPlayer(int i)
	{
		otherPlayers.remove(i);
	}
}
