package ca.main.game.network;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import test2.ServerConnection;

public class ClientList {
	private List<BroadcastToClients> conns = new ArrayList<>();

	public ClientList() {
	};

	public void addConnection(BroadcastToClients c) {
		conns.add(c);
	}
	
	public int size()
	{
		return conns.size();
	}
	
	public BroadcastToClients element(int i)
	{
		return conns.get(i);
	}
	
	public String getBody(int i)
	{
		return conns.get(i).getPosition();
	}
	
	public int getPort(int i)
	{
		return conns.get(i).getPort();
	}
	
	public InetAddress getIP(int i)
	{
		return conns.get(i).getIP();
	}
}
