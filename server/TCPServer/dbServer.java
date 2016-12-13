package server.TCPServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A class representing a server that receives data from client(s).
 * 
 * @author Adam Szekely, Peter Miodrag Varanic, Filip Hudec, Signe Rasmussen,
 *         Ana Iulia Chifor
 *
 */
public class dbServer extends Thread {

	private ServerSocket welcomeSocket;
	private int port;
	private dbClientList<ServerConnection> dbClientList;
	private static ServerConnection c;

	/**
	 * Empty constructor initializing port, welcomeSocket and dbClientList.
	 * 
	 * @param port
	 *            the port used to receive data through it
	 * @param welcomeSocket
	 *            is a Socket which waits for requests to come in over the
	 *            network
	 * @param dbClientList
	 *            is a list of connection to the server
	 */
	public dbServer() {
		port = 1098;
		try {
			welcomeSocket = new ServerSocket(port);
			dbClientList = new dbClientList<ServerConnection>();
			System.out.println("TCP server started");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts the thread and waits for requests to come in over the network.
	 * After the connection is made the it is added to the client list.
	 * Afterwards a check method starts from the class ServerConnection.
	 */
	public void run() {

		while (true) {
			Socket connectionSocket;
			try {
				System.out.println("Waiting for a client");
				connectionSocket = welcomeSocket.accept();

				c = new ServerConnection(connectionSocket, dbClientList);
				System.out.println("Result1: " + c);
				dbClientList.add(c);
				System.out.println(dbClientList.size());
				c.check();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
/**
 * Returns a ServerConnection.
 * @return a ServerConnection.
 */
	public static ServerConnection getC() {
		return c;
	}

}