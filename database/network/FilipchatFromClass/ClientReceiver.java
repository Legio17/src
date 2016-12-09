package database.network.FilipchatFromClass;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientReceiver implements Runnable{
	
	private ObjectInputStream inFromServer;
	private ChatView view;
	private String name;
	
	public ClientReceiver(ObjectInputStream inFromServer,ChatView view, String name)
	{
		System.out.println("Created client receiver");
		this.inFromServer=inFromServer;
		this.view=view;
		this.name = name;
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
