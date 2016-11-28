package test2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection extends Thread{

	private Socket clientSocket;
	public ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;
    private MessageBroadcast mb;
	
	public ServerConnection(Socket connectionSocket){
		clientSocket=connectionSocket;
		try {
			outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
			inFromClient = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
	public ServerConnection(Socket connectionSocket, MessageBroadcast mb)
	{
		this(connectionSocket);
		
		this.mb=mb;
	}
	
	@Override
	public void run() {
		  
	while (true){
		try {
			
			Message message = (Message) inFromClient.readObject();
			

		
		     System.out.println("Message from Client: " + message);

		    
		     Message replyMessage = new Message(message.getId(), 
		                              message.getBody().toUpperCase());
		     System.out.println("Server reply: " + replyMessage);
		    	 
		  
				outToClient.writeObject(replyMessage);
				
				for(int i=0; i<mb.size(); i++)
			    {
					 mb.element(i).outToClient.writeObject(replyMessage);
			    }
		 
			} 

		 catch (IOException | ClassNotFoundException e) {
				
				e.printStackTrace();
			}

	}
	
}
}
