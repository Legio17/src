package test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class NewServer extends Thread {
	private DatagramSocket serverSocket;
	private byte[] receiveData, sendData;
	private DatagramPacket receivePacket,sendPacket;
	private String position;
	private InetAddress IPAddress;
	private int port;
	private String capitalizedSentence;
	
	public NewServer()
	{
	try {
		serverSocket = new DatagramSocket(1099);
	} catch (SocketException e) {
		e.printStackTrace();
	}
	receiveData = new byte[1024];
	sendData = new byte[1024];	
	}
	
	
	public void run() {
				
		while (true) {
			receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			try {
				serverSocket.receive(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			position = new String(receivePacket.getData());
			IPAddress = receivePacket.getAddress();
			port = receivePacket.getPort();

			capitalizedSentence = sentence.toUpperCase();
			sendData = capitalizedSentence.getBytes();
			sendPacket = new DatagramPacket(sendData,
					sendData.length, IPAddress, port);
			try {
				serverSocket.send(sendPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){
		NewServer server = new NewServer();
		
		server.start();
	}
}
