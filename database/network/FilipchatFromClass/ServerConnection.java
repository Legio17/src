package database.network.FilipchatFromClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection implements Runnable {

	private Socket clientSocket;
	private ObjectOutputStream outToClient;
	private ObjectInputStream inFromClient;
	private MessageBroadcast mb;

	public ServerConnection(Socket connectionSocket) {
		try {
			clientSocket = connectionSocket;
			outToClient = new ObjectOutputStream(
					connectionSocket.getOutputStream());
			inFromClient = new ObjectInputStream(
					connectionSocket.getInputStream());
		} catch (IOException e) {
		}
	}

	public ServerConnection(Socket connectionSocket, MessageBroadcast mb) {
		try {
			
			clientSocket = connectionSocket;
			mb.setName(name);
			this.mb = mb;
			outToClient = new ObjectOutputStream(
					connectionSocket.getOutputStream());
			inFromClient = new ObjectInputStream(
					connectionSocket.getInputStream());
		} catch (IOException e) {
		}
	}

	@Override
	public void run() {
		while (true) {
			try {

				// read message from client.
				Message message = (Message) inFromClient.readObject();
				System.out.println("Message from Client: " + message);

				// Send reply to client.
				Message replyMessage = new Message(message.getId(), message
						.getBody().toUpperCase());

				mb.getByName(name).outToClient.writeObject(replyMessage);

				// System.out.println("Server reply: " + replyMessage);
				// outToClient.writeObject(replyMessage);
			} catch (IOException | ClassNotFoundException e) {
			}
		}

	}

	public String getName() {
		return name;
	}

}
