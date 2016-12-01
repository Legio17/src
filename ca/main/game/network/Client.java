package ca.main.game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import ca.main.game.Game;
import ca.main.game.boardGames.TicTacToe15x15;

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
			byte[] data = new byte[35];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			
			try {
				socket.receive(packet);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			String message = new String(packet.getData());
			
			//System.out.println(message.substring(0, 2));
			//System.out.println(message);
			String[] array = message.split(":");
			
			if (array[0].equals("03")){
				movePlayer(array);
			}
			if(array[0].equals("04")){
				searchForPlayer(array);
			}
			if(array[0].equals("05")){
				matchPlayers(array);
			}
		}
	}
	
	public void sendPlayerPos(String data){
		data = "03:" + data;
		DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, ipAddress, port);
	
		
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendSearchingForPlayer(String data){
		data = "04:" + data;
		DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, ipAddress, port);
	
		
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMatchPlayers(String data){
		data = "05:" + data;
		DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, ipAddress, port);
	
		
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendLoginRequest(String data){
		data = "00:" + data;
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
	
	private void movePlayer(String[] array){
		
		double coordinateX = Double.parseDouble(array[2]);
		double coordinateY = Double.parseDouble(array[3]);
		boolean found=false;
		int index = 0;
		for(int i=0; i<game.getOtherPlayers().size();i++)
		{
			if(array[1].equalsIgnoreCase(game.getOtherPlayers().get(i).getIpAddress()))
			{
				found=true;
				index=i;
				break;
			}
		}
		if(!found)
		{
			game.getOtherPlayers().addOtherPlayer(new PlayerMP(game, array[1]));
		}
		
		game.getOtherPlayers().get(index).setCoord(coordinateX, coordinateY, array[4].substring(00, 02));
		
	}
	
	private void searchForPlayer(String[] array){
		String player1 = array[1];
		game.getTicTacToeGameList().add(new TicTacToe15x15(game, player1));
		System.out.println("TicTacToe" + game.getTicTacToeGameList().get(0) !=null);
	}

	private void matchPlayers(String[] array){
		String player2 = array[1];
		game.getTicTacToeGameList().get(0).setPlayer2(player2);
	}
	
}
