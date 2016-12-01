package ca.main.game.network.TCPServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import database.connection.connect;
import database.methods.database_methods;
import database.network.chatFromClass.Message;


public class ServerConnection {

	private Socket clientSocket;
	private ObjectOutputStream outToClient;
	private ObjectInputStream inFromClient;
	private dbClientList dbClientList;
	private Connection con = null;
	private String name;
	

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
	return name;
	}
	
	public void setPlayerName(String name)
	{
		
			this.name = name;	
	}

	public void send() {
		//while (true) {
			String name=null;
			String info=null;
			try {
				
				name = (String) inFromClient.readObject();
				setPlayerName(name);
			
				info = database_methods.getInfoByName(con, name);
				
				//System.out.println(info);
				
				// Send reply to client.
			
				for(int i=0; i<dbClientList.size();i++){
					
					if(dbClientList.getPlayerName(i).equalsIgnoreCase(name))
					{
					dbClientList.getConn(i).outToClient.writeObject(info);
					break;
					}
				}
				//System.out.println("Server reply: " + replyMessage);
				//outToClient.writeObject(replyMessage);
			} catch (IOException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
//	}

}
