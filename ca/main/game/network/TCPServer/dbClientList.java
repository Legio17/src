package ca.main.game.network.TCPServer;

import java.util.ArrayList;
import java.util.List;

public class dbClientList {

	
	private List<ServerConnection> conns = new ArrayList<>();

	public dbClientList() {
	};

	public void addConnection(ServerConnection conn) {
		conns.add(conn);
	}
	
	public int size()
	{
		return conns.size();
	}
	
	public ServerConnection getConn(int index){
		return conns.get(index);
	}
	
	public String getPlayerName(int index)
	{
		return conns.get(index).getPlayerName();
	}
}
