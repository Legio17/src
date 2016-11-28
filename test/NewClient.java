package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class NewClient extends Thread {
	public void run() {

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));
		DatagramSocket clientSocket = null;
		try {
			clientSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		InetAddress IPAddress = null;
		try {
			IPAddress = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		String sentence = "Error reading input";
		try {
			sentence = inFromUser.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, IPAddress, 9876);
		try {
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		try {
			clientSocket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String modifiedSentence = new String(receivePacket.getData());
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
	}
	
	public static void main(String[] args){
		NewClient client = new NewClient();

		client.start();
	}
}
