package ca.main.game.network;

import java.net.DatagramPacket;

/**
 * A class with statics methods for reading the data in datagram packets
 * 
 * @author Filip Hudec, Signe Rasmussen, Peter Miodrag Varanic, Adam Szekely,
 *         Ana Iulia Chifor
 *
 */
public class UDPMethods {
	
	/**
	 * @param datagram the datagram packet to read the string from
	 * @return data from the datagram packet as a string
	 */
	public static String DatagramPacketToString(DatagramPacket datagram){
		String strData = new String(datagram.getData(), 0, datagram.getLength());
		return strData;
	}
	
	/**
	 * A method for reading the first 2 characters in the string of the data from a datagram packet
	 * @param datagram the datagram packet to read the 2 first characters from
	 * @return 2 first characters
	 */
	public static String IndentifyDatagram(DatagramPacket datagram){
		String identifier = DatagramPacketToString(datagram);
		return identifier.substring(0, 2);
	}
}
