package ca.main.game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadcastToClients extends Thread {
	String position;
	InetAddress IPAddress;
	int port=0;
	ClientList cl = new ClientList();
	private DatagramPacket sendPacket;
	private byte[] sendData;
	private DatagramSocket serverSocket;

	public BroadcastToClients(String position, InetAddress iPAddress, int port,
			ClientList cl) {
		this.position = position;
		this.IPAddress = iPAddress;
		this.port = port;
		this.cl = cl;
		sendData = new byte[1024];

	}

	public String getPosition() {
		return position;
	}

	public InetAddress getIP() {
		return IPAddress;
	}

	public int getPort() {
		return port;
	}

	public void run() {

		//while (true) {
			System.out.println("Inside the thread.");
			// Message message = new Message(position, IPAddress, port);

			for (int i = 0; i < cl.size(); i++) {
				sendData ="hello".getBytes();// cl.getBody(i).getBytes();
				InetAddress ip=cl.getIP(i);
				int port=cl.getPort(i);
				sendPacket = new DatagramPacket(sendData, sendData.length,
						ip, port);
				//System.out.println(sendData+", "+ sendData.length+", "+ip+":"+port);
				try {

					serverSocket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	//}
}
