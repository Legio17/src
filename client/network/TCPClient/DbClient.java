package client.network.TCPClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import client.Game;

/**
 * A class representing a client that receives data from a database server or
 * send data to it.
 * 
 * @author Adam Szekely, Peter Miodrag Varanic, Filip Hudec, Signe Rasmussen,
 *         Ana Iulia Chifor
 *
 */
public class DbClient extends Thread {

	private int PORT;
	private String HOST;
	Socket clientSocket;
	ObjectOutputStream outToServer;
	ObjectInputStream inFromServer;
	String name;
	private String[] allInfo;
	private boolean isThreadStarted;

	/**
	 * Constructor initializing name, game, IP address and port.
	 * 
	 * @param name
	 *            the name of the player
	 * @param game
	 *            the game the data is sent from and received to
	 * @param ipAddress
	 *            the IP address used to send and receive data
	 * @param port
	 *            the port used to send and receive data through
	 */
	public DbClient(String name, Game game, String ipAddress, int port) {
		this.name = name;
		this.HOST = ipAddress;
		this.PORT = port;
		try {
			// create client socket, connect to server.
			clientSocket = new Socket(HOST, PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts the thread where data is received from the database server.
	 */
	public void run() {
		setIsThreadStarted();

		try {

			// create input stream attached to the socket.
			inFromServer = new ObjectInputStream(clientSocket.getInputStream());
			String temp = (String) inFromServer.readObject();
			allInfo = temp.split(",");
		} catch (IOException e) {
			System.out.println("error!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns information received from the database server.
	 * 
	 * @return all the information received from server
	 */
	public String[] getAllInfo() {
		return allInfo;
	}

	/**
	 * Send player's name to the database server.
	 * 
	 * @param name
	 *            the name of the player
	 */
	public void sendInfo(String info) {

		try {
			outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			outToServer.writeObject(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets isThreadStarted variable to true.
	 */
	public void setIsThreadStarted() {

		isThreadStarted = true;
	}

	/**
	 * Returns isThreadStarted value.
	 * 
	 * @return true if isThreadStarted is true, else false
	 */
	public boolean isThreadStarted() {

		return isThreadStarted;
	}
}
