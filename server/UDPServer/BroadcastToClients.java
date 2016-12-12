package server.UDPServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * A class for broadcasting the data a server receives to all the clients that
 * are connected to the server.
 * 
 * @author Filip Hudec, Signe Rasmussen, Peter Miodrag Varanic, Adam Szekely,
 *         Ana Iulia Chifor
 *
 */
public class BroadcastToClients extends Thread {
	String position;
	InetAddress ipAddress;
	int port = 0;
	ConnectionList connections;
	private DatagramPacket sendPacket;
	private DatagramSocket serverSocket;
	private ServerMain server;

	/**
	 * @param server
	 *            the server that the data that has to be broadcast comes from
	 */
	public BroadcastToClients(ServerMain server) {
		serverSocket = server.getServerSocket();
		this.server = server;
		connections = server.getConnectionList();
	}

	/**
	 * Each time the server receives new data it is being broadcast to each client which information has been saved in the connection list.
	 */
	public void run() {
		while (true) {
			if (server.isNewData()) {
				for (int i = 0; i < connections.size(); i++) {

					if (server.getData() != null) {
						sendPacket = new DatagramPacket(server.getData(), server.getData().length, connections.getIP(i),connections.getPort(i));
						try {
							serverSocket.send(sendPacket);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			}
		}
	}
}
