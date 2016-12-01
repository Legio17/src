package ca.main.game.network.TCPServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class dbServer extends Thread {

	private ServerSocket welcomeSocket;
	private int port;
	private dbClientList dbClientList;

	public dbServer() {
		port = 1098;
		try {
			welcomeSocket = new ServerSocket(port);
			dbClientList = new dbClientList();
			System.out.println("Server started");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		while (true) {
			Socket connectionSocket;
			try {
				System.out.println("Waiting for a client");
				connectionSocket = welcomeSocket.accept();
							
				ServerConnection c = new ServerConnection(connectionSocket,
						dbClientList);
				dbClientList.addConnection(c);
				new Thread(c, "Thread").start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws AlreadyBoundException,
			NotBoundException, IOException {
		dbServer dbServer = new dbServer();
		dbServer.start();
	}
}