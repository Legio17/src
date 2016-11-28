package test2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ClientReceiver implements Runnable {

	private ObjectInputStream inFromServer;
	private ChatView view;
	public ArrayList<Message> list;
	
	public ClientReceiver(ObjectInputStream inFromServer, ChatView _view) {
		System.out.println("Created client receiver");
		this.inFromServer=inFromServer;
		view=_view;
	}
	
	@Override
	public void run() {
		
		while(true)
		{
			
			try {
				Message message = (Message) inFromServer.readObject();
				
			
			System.out.println("Receiver: "+message);
		//	for(int i=0; i<list.size();i++)
			view.updateMessage(message.getBody());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(inFromServer==null)
			{
				System.out.println("inFromServer was null");
				break;
			}
			
		}
		
	}

}
