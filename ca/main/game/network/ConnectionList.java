package ca.main.game.network;

import java.net.InetAddress;
import java.util.ArrayList;



public class ConnectionList {
	private ArrayList<Connection> conns;

	public ConnectionList() {
		conns = new ArrayList<>();
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
