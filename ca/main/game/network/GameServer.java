package ca.main.game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import ca.main.game.Game;

public class GameServer extends Thread{

	private DatagramSocket socket;
	private Game game;
	private int port; 

	public GameServer(Game game, int port) {
		this.game = game;
		this.port = port;
		
		try {
			this.socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
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
			System.out.println("CLIENT +["+packet.getAddress().getHostAddress()+":"+packet.getPort()+"]"+ message);
			if (message.trim().equalsIgnoreCase("ping")){
				System.out.println("returning Pong");
				sendData("pong".getBytes(), packet.getAddress(), port); 
			}
			
		}
	}
	
	public void sendData(byte[] data, InetAddress ipAddress, int port){
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
