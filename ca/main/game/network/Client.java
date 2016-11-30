package ca.main.game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import ca.main.game.Game;

public class Client extends Thread {

	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
	private int port;
	private String name;
	private ArrayList<String> ipList;

	public Client(Game game, String ipAddress, int port) {
		this.game = game;
		this.port = port;
		
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		
		while(true){
			byte[] data = new byte[25];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			
			try {
				socket.receive(packet);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			String message = new String(packet.getData());
			
			//System.out.println(message.substring(0, 2));
			System.out.println(message);
			String[] array = message.split(":");
			double coordinate = Double.parseDouble(array[2]);

			boolean found=false;
			int index = 0;
			for(int i=0; i<game.getOtherPlayers().size();i++)
			{
				if(array[0].equalsIgnoreCase(game.getOtherPlayers().get(i).getIpAddress()))
				{
					found=true;
					index=i;
					break;
				}
			}
			if(!found)
			{
				game.getOtherPlayers().addOtherPlayer(new PlayerMP(game, array[0]));
			}
			
			if (array[1].equalsIgnoreCase("x"))
			{
				game.getOtherPlayers().get(index).setX(coordinate);	
			}
			else if(array[1].equalsIgnoreCase("y"))
			{
				game.getOtherPlayers().get(index).setY(coordinate);
			}
			
		}
	}
	
	public void sendData(String data){
		//String markedData = "03:" + data;
		DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, ipAddress, port);
		
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getClientName()
	{
		return name;
	}
	
	
}
