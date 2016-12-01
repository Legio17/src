package ca.main.game.network.TCPClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

import ca.main.game.Game;
import ca.main.game.network.TCPServer.dbServer;

public class dbClient extends Thread {

	final int PORT = 1098;
	final String HOST = "10.52.236.31";
	Socket clientSocket;
	ObjectOutputStream outToServer;
	ObjectInputStream inFromServer;
	String name;
	private Game game;

	public dbClient(String name, Game game) {
		this.name = name;
		this.game = game;
	}

	public void run() {
		try {
			// create client socket, connect to server.
			clientSocket = new Socket(HOST, PORT);

			// create output stream attached to the socket.
			outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			// create input stream attached to the socket.
			inFromServer = new ObjectInputStream(clientSocket.getInputStream());

		} catch (IOException e) {
			System.out.println("error!");
			e.printStackTrace();
		}
	}

	public void sendName(String name) {
		try {
			outToServer.writeBytes(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
