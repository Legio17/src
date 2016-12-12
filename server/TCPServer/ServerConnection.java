package server.TCPServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;

import database.connection.connect;
import database.methods.InsertExecutor;
import database.methods.database_methods;


public class ServerConnection {

	private Socket clientSocket;
	private ObjectOutputStream outToClient;
	private ObjectInputStream inFromClient;
	private dbClientList<ServerConnection> dbClientList;
	private Connection con = null;
	private String name;
	private String data;
	private InsertExecutor ie;
	String[] array;

	public ServerConnection(Socket connectionSocket,
			dbClientList<ServerConnection> dbClientList) {
		try {
			con = connect.PostgreSQLJDBC("SEP2_data", "Postgres");
			clientSocket = connectionSocket;
			this.dbClientList = dbClientList;
			this.ie = new InsertExecutor(con);
			outToClient = new ObjectOutputStream(
					connectionSocket.getOutputStream());
			inFromClient = new ObjectInputStream(
					connectionSocket.getInputStream());
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String getPlayerName() {
		return name;
	}

	public void setPlayerName(String name) {

		this.name = name;
	}

	public void check() {

		try {
			data = (String) inFromClient.readObject();
			System.out.println("data: " + data);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		if (data.contains(":")) {
			array = data.split(":");
			if (array[0].equals("00")) {
				updateDB();
			} else if (array[0].equals("01")) {
				updateInfoDB();
			}
		} else {
			send();
		}
	}

	public void updateDB() {
		try {
			ie.insertPlayer(array[1]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(array[1]);
	}

	public void updateInfoDB() {
		System.out.println("Info sent to database");
		try {
			ie.updateScoreInfo(array[1], array[2], array[5]);
			ie.updateRanks();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println("Info sent to database");
	}

	public void send() {
		// while (true) {
		// String name = null;
		String info = null;
		String info2 = null;
		try {

			// name = (String) inFromClient.readObject();
			setPlayerName(data);

			info = database_methods.getInfoByName(con, data);
			info2 = database_methods.getTop5(con);

			// System.out.println(info);

			// Send reply to client.

			Iterator<ServerConnection> iterator = dbClientList.iterator();
			// System.out.println(""+iterator.next());

			while (iterator.hasNext()) {
				// System.out.println(dbServer.getC());
				if (dbClientList.contains(dbServer.getC())) {
					// System.out.println("here");
					// System.out.println("Result: "+dbClientList.getCon(dbServer.getC()));
					dbClientList.getCon(dbServer.getC()).outToClient
							.writeObject(info + ", " + info2);
					break;
				}

			}
			con.close();
			// System.out.println("Server reply: " + replyMessage);
			// outToClient.writeObject(replyMessage);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	// }

}