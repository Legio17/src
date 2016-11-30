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
	private DatagramSocket serverSocket;
	private ServerMain server;

	public BroadcastToClients(ServerMain server) {
		serverSocket = server.getServerSocket();
		this.server = server;
		cl=server.getConnectionList();
	}

	public void run() {
		while (true) {
			if(server.isNewData()){
				for (int i = 0; i < cl.size(); i++) {
					
					if (server.getData() != null){
						sendPacket = new DatagramPacket(server.getData(), server.getData().length, cl.getIP(i), cl.getPort(i));
						
						try {
							serverSocket.send(sendPacket);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					/*System.out.println("send data"+new String(server.getData())+" "+ server.getData().length+" "+
							cl.getIP(i)+" "+ cl.getPort(i));*/
			}
				/*sendPacket = new DatagramPacket(ipAddress.toString().getBytes(), ipAddress.toString().getBytes().length,
						cl.getIP(i), cl.getPort(i));
				serverSocket.send(newCon.getIP());*/
			}
		}
	}
}
