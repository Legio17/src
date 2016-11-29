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

public class ServerMain extends Thread {

	private DatagramSocket serverSocket;
	private byte[] receiveData, sendData;
	private DatagramPacket receivePacket, sendPacket;
	private String position;
	private InetAddress IPAddress;
	private int port;
	private String capitalizedSentence;
	private ClientList cl = new ClientList();

	public ServerMain() {
		try {
			serverSocket = new DatagramSocket(1099);
			System.out.println("Server socket created.");
		} catch (SocketException e) {
			e.printStackTrace();
		}
		receiveData = new byte[1024];

		while (true) {
			System.out.println("Ready to receive.");
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			try {
				serverSocket.receive(receivePacket);
				
			} catch (IOException e) {
				e.printStackTrace();
			}

			position = new String(receivePacket.getData());
			IPAddress = receivePacket.getAddress();
			port = receivePacket.getPort();
			
			BroadcastToClients btc = new BroadcastToClients(position,
					IPAddress, port,cl);
			//System.out.println(position+", "+IPAddress+":"+port);
			cl.addConnection(btc);
			
			new Thread(btc, "Communication").start();
		}
	}

	public static void main(String[] args) {
		ServerMain server = new ServerMain();

		server.start();
	}
}
