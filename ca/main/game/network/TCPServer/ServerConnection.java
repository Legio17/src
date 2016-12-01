package ca.main.game.network.TCPServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import database.connection.connect;
import database.methods.database_methods;
import database.network.chatFromClass.Message;


public class ServerConnection implements Runnable {

	private Socket clientSocket;
	private ObjectOutputStream outToClient;
	private ObjectInputStream inFromClient;
	private dbClientList dbClientList;
	private Connection con = null;

	public ServerConnection(Socket connectionSocket) {
		try{
		clientSocket = connectionSocket;
		outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
		inFromClient = new ObjectInputStream(connectionSocket.getInputStream());}
		catch(IOException e){}
	}

	public ServerConnection(Socket connectionSocket, dbClientList dbClientList) {
		try{
		con = connect.PostgreSQLJDBC("SEP2_data", "peter28mio07");
		clientSocket = connectionSocket;
		this.dbClientList= dbClientList;
		outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
		inFromClient = new ObjectInputStream(connectionSocket.getInputStream());}
		catch(IOException e){} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			String name=null;
			String info=null;
			try {
				name = (String) inFromClient.readObject();
				info = database_methods.getInfoByName(con, name);
				System.out.println(info);
				/*// read message from client.
				Message message = (Message) inFromClient.readObject();
				System.out.println("Message from Client: " + message);
				
				// Send reply to client.
				Message replyMessage = new Message(message.getId(), message
						.getBody().toUpperCase());
				for(int i=0; i<dbClientList.size();i++){
					dbClientList.getConn(i).outToClient.writeObject(replyMessage);;
				}
				//System.out.println("Server reply: " + replyMessage);
				//outToClient.writeObject(replyMessage);*/
			} catch (IOException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
