package server.UDPServer;

import java.net.InetAddress;

public class UDPConnection {

	private InetAddress ipAddress;
	private int port;

	public UDPConnection(byte[] data, InetAddress ipAddress, int port) {
		this.port = port;
		this.ipAddress = ipAddress;
	}
	
	public UDPConnection(InetAddress ipAddress, int port) {
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
