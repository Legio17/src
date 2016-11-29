package ca.main.game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadcastToClients extends Thread {
	String position;
	InetAddress ipAddress;
	int port = 0;
	ConnectionList cl;
	private DatagramPacket sendPacket;
	private byte[] sendData;
	private DatagramSocket serverSocket;

	public BroadcastToClients(ServerMain server) {
		this.ipAddress = server.getIP();
		this.port = server.getPort();
		sendData = server.getData();
		this.cl=server.getList();
	}

	public void run() {
		while (true) {
			for (int i = 0; i < cl.size(); i++) {
				sendPacket = new DatagramPacket(sendData, sendData.length,
						ipAddress, port);
				try {
					serverSocket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
