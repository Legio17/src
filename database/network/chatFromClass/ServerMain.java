package database.network.chatFromClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	public static void main(String args[]) throws IOException,
			ClassNotFoundException {
		boolean isConnected = true;
		final int PORT = 6789;
		Socket connectionSocket;
		System.out.println("Starting Server...");

		// create welcoming socket at port 6789
		ServerSocket welcomeSocket = new ServerSocket(PORT);
		MessageBroadcast messageBroadcast = new MessageBroadcast();
		while (isConnected) {
	
			connectionSocket = welcomeSocket.accept();

			ServerConnection c = new ServerConnection(connectionSocket,
					messageBroadcast);
			messageBroadcast.addConnection(c);
			new Thread(c, "Communication").start();
		}
		
		
	}
}
