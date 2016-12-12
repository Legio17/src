package server.TCPServer;

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
	private dbClientList<ServerConnection> dbClientList;
	private static ServerConnection c;

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

	public void run() {

		while (true) {
			Socket connectionSocket;
			try {
				System.out.println("Waiting for a client");
				connectionSocket = welcomeSocket.accept();
							
				c = new ServerConnection(connectionSocket, dbClientList);
				System.out.println("Result1: "+c);
				dbClientList.add(c);
				System.out.println(dbClientList.size());
				//new Thread(c, "Thread").start();
				c.check();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
public static ServerConnection getC()
{
	return c;
}
	
}