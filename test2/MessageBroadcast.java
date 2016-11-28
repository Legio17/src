package test2;

import java.util.ArrayList;
import java.util.List;

public class MessageBroadcast {

	private List<ServerConnection> conns = new ArrayList<>();

	public MessageBroadcast() {
	};

	public void addConnection(ServerConnection conn) {
		conns.add(conn);
	}
	
	public int size()
	{
		return conns.size();
	}
	
	public ServerConnection element(int i)
	{
		return conns.get(i);
	}
}
