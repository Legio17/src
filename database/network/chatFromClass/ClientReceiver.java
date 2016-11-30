package database.network.chatFromClass;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientReceiver implements Runnable{
	
	private ObjectInputStream inFromServer;
	private ChatView view;
	
	public ClientReceiver(ObjectInputStream inFromServer,ChatView view)
	{
		System.out.println("Created client receiver");
		this.inFromServer=inFromServer;
		this.view=view;
	}

	@Override
	public void run() {
		while(true){
			try{
			Message message= (Message) inFromServer.readObject();
			System.out.println("Recceiver: "+ message);
			view.updateMessages(message.getBody()+"\n");
			if(inFromServer==null){
				System.out.println("inFromServer was null");
				break;
			}
			}
			
			catch(IOException | ClassNotFoundException e){}
		}
		
	}

}
