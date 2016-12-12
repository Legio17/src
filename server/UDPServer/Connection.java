package server.UDPServer;

import java.net.InetAddress;

public class Connection {

	private InetAddress ipAddress;
//	private DatagramSocket socket;
	private int port;
	private byte[] data;

	public Connection(byte[] data, InetAddress ipAddress, int port) {
		
		this.port = port;
		this.ipAddress = ipAddress;
		/*try {
			this.socket = new DatagramSocket();	
		} catch (SocketException e) {
			e.printStackTrace();
		}*/ 
	}
	
	public Connection(InetAddress ipAddress, int port) {
		this.port = port;
		this.ipAddress = ipAddress;
	}

	public int getPort()
	{
		return port;
	}
	
	public InetAddress getIP()
	{
		return ipAddress;
	}
}
