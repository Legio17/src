package ca.main.game.network;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import test2.ServerConnection;

public class ConnectionList {
	private ArrayList<Connection> conns = new ArrayList<>();

	public ConnectionList() {
	};

	public void addConnection(Connection c) {
		conns.add(c);
	}
	
	public int size()
	{
		return conns.size();
	}
	
	public ArrayList<Connection> getAll()
	{
		return conns;
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
