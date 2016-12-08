package ca.main.game.network.TCPClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

import ca.main.game.Game;
import ca.main.game.network.TCPServer.dbServer;

public class DbClient extends Thread {

	private int PORT;
	private String HOST;
	Socket clientSocket;
	ObjectOutputStream outToServer;
	ObjectInputStream inFromServer;
	String name;
	private Game game;
	private String[] allInfo;
	private boolean isThreadStarted;

	public DbClient(String name, Game game, String ipAddress, int port) {
		this.name = name;
		this.game = game;
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

	public void run() {
		setIsThreadStarted();

		try {
			System.out.println("INfROMSERVER");

			// create input stream attached to the socket.
			inFromServer = new ObjectInputStream(clientSocket.getInputStream());
			String temp = (String) inFromServer.readObject();
			allInfo = temp.split(",");
			// System.out.println((String) inFromServer.readObject());
		} catch (IOException e) {
			System.out.println("error!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getAllInfo() {
		return allInfo;
	}

	public void sendName(String name) {

		try {
			System.out.println("name sent");
			// create output stream attached to the socket.
			outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			outToServer.writeObject(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setIsThreadStarted() {

		isThreadStarted = true;
	}

	public boolean isThreadStarted() {

		return isThreadStarted;
	}
}
