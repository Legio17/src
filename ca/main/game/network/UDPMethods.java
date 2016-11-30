package ca.main.game.network;

import java.net.DatagramPacket;

public class UDPMethods {

	public static String DatagramPacketToString(DatagramPacket datagram){
		//receivePacket = new DatagramPacket(receiveData, receiveData.length);
		
		String strData = new String(datagram.getData(), 0, datagram.getLength());
		return strData;
	}
	
	public static String IndentifyDatagram(DatagramPacket datagram){
		String identifier = DatagramPacketToString(datagram);
		return identifier.substring(0, 2);
	}
}
