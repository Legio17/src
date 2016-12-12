package server.UDPServer;

import java.net.InetAddress;

public class Connection {

	private InetAddress ipAddress;
	private int port;

	public Connection(byte[] data, InetAddress ipAddress, int port) {
		this.port = port;
		this.ipAddress = ipAddress;
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
