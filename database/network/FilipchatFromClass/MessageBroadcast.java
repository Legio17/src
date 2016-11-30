package database.network.FilipchatFromClass;

import java.util.ArrayList;
import java.util.List;

public class MessageBroadcast {
	private List<ServerConnection> conns= new ArrayList<>();
	public MessageBroadcast(){}
	public String name;
	
	public void addConnection(ServerConnection conn){
		conns.add(conn);
	}
	
	public ServerConnection getConn (int index){
		return conns.get(index);
	}
	public int getsize(){
		return conns.size();
	}
	
	public ServerConnection getByName(String name){
		for (int i = 0; i < conns.size(); i++){
			if (conns.get(i).getName().equals(name)){
				return conns.get(i);
			}
		}
		return null;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

}
