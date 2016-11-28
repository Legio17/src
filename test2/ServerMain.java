package test2;

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
		MessageBroadcast mb = new MessageBroadcast();
		System.out.println("Starting Server...");

		// create welcoming socket at port 6789
		ServerSocket welcomeSocket = new ServerSocket(PORT);
		while (isConnected) {
			System.out.println("Waiting for a client...");

			// Wait, on welcoming socket for contact by client
			Socket connectionSocket = welcomeSocket.accept();

			ServerConnection c = new ServerConnection(connectionSocket, mb);
			mb.addConnection(c);
			new Thread(c, "Communication").start();

		}

		welcomeSocket.close();
	}
}
