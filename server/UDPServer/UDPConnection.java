package server.UDPServer;

import java.net.InetAddress;

public class UDPConnection {

	private InetAddress ipAddress;
	private int port;
	private String name;

	public UDPConnection(byte[] data, InetAddress ipAddress, int port) {
		this.port = port;
		this.ipAddress = ipAddress;
	}
	
	public UDPConnection(InetAddress ipAddress, int port, String name) {
		this.port = port;
		this.ipAddress = ipAddress;
		this.name = name;
	}

	public int getPort()
	{
		return port;
	}
	public String getName()
	{
		return name;
	}
	
	public InetAddress getIP()
	{
		return ipAddress;
	}
	
}
