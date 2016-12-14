package server.UDPServer;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * A class representing a list of connection to clients that the server has. The
 * class purpose is to keep track of which clients to broadcast to.
 * 
 * @author Filip Hudec, Signe Rasmussen, Peter Miodrag Varanic, Adam Szekely,
 *         Ana Iulia Chifor
 */
public class ConnectionList {
	private ArrayList<UDPConnection> connections;
	
	/**
	 * Creates an arraylist of connections
	 */
	public ConnectionList() {
		connections = new ArrayList<>();
	};
	
	/**
	 * 
	 * @param connection to add
	 */
	public void addConnection(UDPConnection connection) {
		connections.add(connection);
	}

	public int size() {
		return connections.size();
	}

	public ArrayList<UDPConnection> getAll() {
		return connections;
	}
	
	public UDPConnection getAtIndex(int Index){
		return connections.get(Index);
	}
	
	/**
	 * 
	 * @param i
	 * @return conncection at index i
	 */
	public int getPort(int i) {
		return connections.get(i).getPort();
	}
	
	/**
	 * get 
	 * @param i
	 * @return connection at index i
	 */
	public InetAddress getIP(int i) {
		return connections.get(i).getIP();
	}
	
	/**
	 * delete connection at index i
	 * @param i 
	 */
	public void deleteConnection(int i) {
		connections.remove(i);

	}
}
