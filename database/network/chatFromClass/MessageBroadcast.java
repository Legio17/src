package database.network.chatFromClass;

import java.util.ArrayList;
import java.util.List;

public class MessageBroadcast {
	private List<ServerConnection> conns= new ArrayList<>();
	public MessageBroadcast(){}
	public void addConnection(ServerConnection conn){
		conns.add(conn);
	}
	
	public ServerConnection getConn (int index){
		return conns.get(index);
	}
	public int getsize(){
		return conns.size();
	}
}
