package ca.main.game.network.TCPServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import database.network.chatFromClass.Message;


public class ServerConnection implements Runnable {

	private Socket clientSocket;
	private ObjectOutputStream outToClient;
	private ObjectInputStream inFromClient;
	private dbClientList dbClientList;

	public ServerConnection(Socket connectionSocket) {
		try{
		clientSocket = connectionSocket;
		outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
		inFromClient = new ObjectInputStream(connectionSocket.getInputStream());}
		catch(IOException e){}
	}

	public ServerConnection(Socket connectionSocket, dbClientList dbClientList) {
		try{
		clientSocket = connectionSocket;
		this.dbClientList= dbClientList;
		outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
		inFromClient = new ObjectInputStream(connectionSocket.getInputStream());}
		catch(IOException e){}
	}
	
	public String getPlayerName()
	{
		String temp="a";
		try {
			temp = (String) inFromClient.readObject();
		} 
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
			return temp;
		
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
				for(int i=0; i<dbClientList.size();i++){
					dbClientList.getConn(i).outToClient.writeObject(replyMessage);;
				}
				//System.out.println("Server reply: " + replyMessage);
				//outToClient.writeObject(replyMessage);
			} catch (IOException | ClassNotFoundException e) {
			}
		}
	}

}
