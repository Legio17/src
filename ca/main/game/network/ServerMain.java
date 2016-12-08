package ca.main.game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import ca.main.game.utilities.UDPMethods;

/**
 * A class representing a server that receives data packets from a client or
 * send data packets to it
 * 
 * @author Filip Hudec, Signe Rasmussen, Peter Miodrag Varanic, Adam Szekely,
 *         Ana Iulia Chifor
 *
 */
public class ServerMain extends Thread {

	private DatagramSocket serverSocket;
	private byte[] receiveData, sendData;
	private DatagramPacket receivePacket;
	private InetAddress ipAddress;
	private int port;
	private boolean newData;
	private ConnectionList connections = new ConnectionList();

	/**
	 * No-arg constructor initializing the datagram socket for sending and
	 * receiving datagram packets.
	 */
	public ServerMain() {
		try {
			serverSocket = new DatagramSocket(1099);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is a method needed to start the thread. When the method start is
	 * called on an object of this class it will execute the code in the run
	 * method. The server socket receives new data continuously. When receiving
	 * from a new client it adds the client to the servers connection list. When
	 * receiving data concerning game actions it readies the data to be
	 * broadcasted.
	 */
	public void run() {
		while (true) {
			newData = false;
			receiveData = new byte[35];

			receivePacket = new DatagramPacket(receiveData, receiveData.length);

			try {
				serverSocket.receive(receivePacket);

				// System.out.println("receiving data"+new
				// String(receivePacket.getData()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			String identifier = UDPMethods.IndentifyDatagram(receivePacket);

			if (identifier.equals("03")) {
				sendData = receivePacket.getData();
				newData = true;

			} else if (identifier.equals("04") || identifier.equals("05")
					|| identifier.equals("06") || identifier.equals("07")) {
				sendData = receivePacket.getData();
				newData = true;
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else if (identifier.equals("00")) {
				ipAddress = receivePacket.getAddress();
				port = receivePacket.getPort();
				createConnection(ipAddress, port);
			}
			else if (identifier.equals("08")) {
				ipAddress = receivePacket.getAddress();
				port = receivePacket.getPort();
				deleteConnection();
				
				
			}

		}
	}

	private void deleteConnection() {
		for (int i = 0; i < connections.size(); i++) {

			if (ipAddress.equals(connections.getIP(i))) {
				connections.deleteConnection(i);
				
				break;
			}
		}
		
	}

	/**
	 * The server will receive data from a client. This data needs to be send to
	 * all clients connected.
	 * 
	 * @return sendData the data that needs to be send to all clients
	 */
	public byte[] getData() {
		return sendData;
	}

	/**
	 * @return serverSocket the datagram socket of the server
	 */
	public DatagramSocket getServerSocket() {
		return serverSocket;
	}

	/**
	 * @return connections the list of connection objects
	 */
	public ConnectionList getConnectionList() {
		return connections;
	}

	/**
	 * Create a new connection if the ipAddress is not already in the list
	 * 
	 * @param sendData
	 *            the data the client is sending to the server
	 * @param ipAddress
	 *            the ipAddress of the client communicating with the server
	 * @param port
	 *            the port of the client to connect to
	 */
	public void createConnection(InetAddress ipAddress, int port) {
		boolean found = false;

		for (int i = 0; i < connections.size(); i++) {

			if (ipAddress.equals(connections.getIP(i))) {
				found = true;
				break;
			}
		}
		if (!found) {
			Connection newCon = new Connection(ipAddress, port);
			connections.addConnection(newCon);

		}
	}

	/**
	 * Boolean to unsure that the BroadcastToClients only broadcasts when the
	 * server has received new data to not broadcast redundantly.
	 * 
	 * @return newData a boolean that should be true each time a new packet is
	 *         received and false the rest of the time
	 */
	public boolean isNewData() {
		return newData;
	}

	
}
