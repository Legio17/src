package ca.main.game.network.TCPClient;

import java.io.IOException;
import java.io.ObjectInputStream;

import database.network.chatFromClass.ChatView;
import database.network.chatFromClass.Message;

public class ClientReceiver implements Runnable{
	
	private ObjectInputStream inFromServer;
	private dbClient dbClient;
	
	public ClientReceiver(ObjectInputStream inFromServer,dbClient dbClient)
	{
		System.out.println("Created client receiver");
		this.inFromServer=inFromServer;
		this.dbClient=dbClient;
	}

	@Override
	public void run() {
		while(true){
			try{
			Message message= (Message) inFromServer.readObject();
			System.out.println("Receiver: "+ message);
			if(inFromServer==null){
				System.out.println("inFromServer was null");
				break;
			}
			}
			
			catch(IOException | ClassNotFoundException e){}
		}
		
	}

}
