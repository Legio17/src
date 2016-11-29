package ca.main.game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import ca.main.game.Game;

public class Client extends Thread {

	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
	private int port;
	private String name;

	public Client(Game game, String ipAddress, int port) {
		this.game = game;
		this.port = port;
		name = "Unknown";
		
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void setClientName(){
		name = game.getPlayer().getName();
	}
	
	public void run(){
		
		while(true){
			byte[] data = new byte[4];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String message = new String(packet.getData());
			String[] array = message.split(":");
			double coordinate = Double.parseDouble(array[1]);
			if (array[0].equalsIgnoreCase("x"))
			{
				
				game.getOtherPlayer().setX(coordinate);	
			}
			else if(array[0].equalsIgnoreCase("y"))
			{
				game.getOtherPlayer().setY(coordinate);
			}
		}
	}
	
	public void sendData(byte[] data){
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		
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
