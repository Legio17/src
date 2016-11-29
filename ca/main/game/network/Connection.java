package ca.main.game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import ca.main.game.Game;

public class Connection {

	private InetAddress ipAddress;
	private DatagramSocket socket;
	private int port;
	private byte[] data;

	public Connection(byte[] data,InetAddress ipAddress, int port) {
		
		this.port = port;
		
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = ipAddress;
		} catch (SocketException e) {
			e.printStackTrace();
		} 
	}
	
	public int getPort()
	{
		return port;
	}
	
	public InetAddress getIP()
	{
		return ipAddress;
	}
}
