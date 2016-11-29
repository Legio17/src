package ca.main.game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import test.NewServer;
import test2.MessageBroadcast;
import test2.ServerConnection;
import ca.main.game.Game;

public class ServerMain extends Thread{

	private DatagramSocket serverSocket;
	private byte[] receiveData, sendData;
	private DatagramPacket receivePacket;
	private InetAddress ipAddress;
	private int port;
	private ConnectionList cl = new ConnectionList();

	public ServerMain() {

		try {
			serverSocket = new DatagramSocket(1099);
			System.out.println("Server socket created.");
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		while (true) {
			receiveData = new byte[15];
			
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			
			try {
				serverSocket.receive(receivePacket);
				System.out.println("receiving data"+new String(receivePacket.getData()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			sendData = receivePacket.getData();
			ipAddress = receivePacket.getAddress();
			port = receivePacket.getPort();

			createConn(sendData, ipAddress, port);	
			
		}
	}
	
	public InetAddress getIP()
	{
		return ipAddress;
	}
	
	public byte[] getData()
	{
		return sendData;
	}
	
	public int getPort()
	{
		return port;
	}
	
	public DatagramSocket getServerSocket()
	{
		return serverSocket;
	}
	
	public ConnectionList getList()
	{
		return cl;
	}

	private void createConn(byte[] sendData, InetAddress ipAddress, int port) {

		boolean found = false;
		
		for (int i = 0; i < cl.size(); i++) {
			if (ipAddress.equals(cl.getIP(i))) {
				found = true;
				break;
			}
		}
		
		if (!found) {
			Connection newCon = new Connection(sendData, ipAddress, port);
			cl.addConnection(newCon);
		}
		
	}

	public static void main(String[] args) {
		ServerMain server = new ServerMain();
		BroadcastToClients btc = new BroadcastToClients(server);
		btc.start();
		server.start();
	}
}
