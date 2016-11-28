package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

import ca.main.game.Game;

public class NewClient extends Thread {
	private DatagramSocket clientSocket;
	private String serverIPAddress;
	private int port;
	private InetAddress inetIPAddress;
	private byte[] sendDataTestMessage;
	private byte[] sendDataPosition;
	private byte[] receiveData;
	private String sentence;
	private int[] playerPosition;
	
	public NewClient(){
		try {
			clientSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		serverIPAddress = "10.52.237.183"; //mio test
		port = 1099;
		try {
			inetIPAddress = InetAddress.getByName(serverIPAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		receiveData = new byte[1024];
		sentence = "Error reading input";
		playerPosition = new int[2];
	}
	
	public void run() {

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));
		
		try {
			sentence = inFromUser.readLine();
			sendDataTestMessage = new byte[sentence.getBytes().length];
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendDataTestMessage = sentence.getBytes();
		
		///
		playerPosition[0] = 5;
		playerPosition[1] = 7;
		String s = Arrays.toString(playerPosition);
		sendDataPosition = new byte[s.getBytes().length];
		///
		
		DatagramPacket sendPacket = new DatagramPacket(sendDataTestMessage,
				sendDataTestMessage.length, inetIPAddress, port); //port mio chose to be open all the time
		
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
