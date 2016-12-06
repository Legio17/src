package ca.main.game.network;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

import ca.main.game.network.TCPServer.dbServer;

public class StartServers {

	public static void main(String[] args) throws AlreadyBoundException,
			NotBoundException, IOException {
		dbServer dbServer;
		ServerMain serverMain;
		dbServer = new dbServer();
		dbServer.start();
		serverMain = new ServerMain();
		System.out.println("UDP server socket created");
		BroadcastToClients btc = new BroadcastToClients(serverMain);
		btc.start();
		serverMain.start();
	}
}
