package server.TCPServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import database.connection.connect;
import database.methods.InsertExecutor;
import database.methods.database_methods;

/**
 * A class representin a connection to the server.
 * 
 * @author Adam Szekely, Peter Miodrag Varanic, Filip Hudec, Signe Rasmussen,
 *         Ana Iulia Chifor
 *
 */
public class ServerConnection {

	private ObjectOutputStream outToClient;
	private ObjectInputStream inFromClient;
	private dbClientList<ServerConnection> dbClientList;
	private Connection con = null;
	private String name;
	private String data;
	private InsertExecutor ie;
	String[] array;

	/**
	 * Constructor initializing connectionSocket, dbClientList, outToClient,
	 * inFromClient, con and ie.
	 * 
	 * @param connectionSocket
	 *            used for getting the information from the incoming data and
	 *            sending the outgoing data.
	 * @param dbClientList
	 *            is an ArraySet used for storing the connections in it
	 * @param outToClient
	 *            is a variable used for storing information about the outgoing
	 *            data
	 * @param inFromClient
	 *            is a variable used for storing information about the incoming
	 *            data
	 * @param con
	 *            is a Connection with what it is possible to connect to the
	 *            database
	 * @param ie
	 *            is an InsertExecutor with what it is possible to insert and
	 *            update tables in the database
	 * 
	 */
	public ServerConnection(Socket connectionSocket,
			dbClientList<ServerConnection> dbClientList) {
		try {
			con = connect.PostgreSQLJDBC("SEP2_data", "peter28mio07");
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

	/**
	 * Returns the name of the player in this ServerConnection.
	 * 
	 * @return the name of the player
	 */
	public String getPlayerName() {
		return name;
	}

	/**
	 * Sets the name of the player in this ServerConnection.
	 * 
	 * @param name
	 *            the name of the player
	 */
	public void setPlayerName(String name) {

		this.name = name;
	}

	/**
	 * Checks the type of the information received from the client. If the
	 * received data starts with 00 then the player name has to be inserted into
	 * the database. If it starts with 01 then the game history has to be
	 * updated in the database. Else the score information about the player and
	 * the top five players have to be sent to the player whose name was
	 * received in the incoming data.
	 */
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
			sendScoreInfo();
		}
	}

	/**
	 * Inserts the player name into the database.
	 */
	public void updateDB() {
		try {
			ie.insertPlayer(array[1]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(array[1]);
	}

	/**
	 * Updates score information in the database and updates ranks.
	 */
	public void updateInfoDB() {
		System.out.println("Info sent to database");
		try {
			ie.updateScoreInfo(array[1], array[2], array[5]);
			ie.updateRanks();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends, score information about the player, and the top five players, to
	 * the player whose name was received in the incoming data.
	 */
	public void sendScoreInfo() {
		String info = null;
		String info2 = null;
		try {

			setPlayerName(data);
			info = database_methods.getInfoByName(con, data);
			info2 = database_methods.getTop5(con);
			Iterator<ServerConnection> iterator = dbClientList.iterator();

			while (iterator.hasNext()) {
				if (dbClientList.contains(dbServer.getC())) {
					dbClientList.getCon(dbServer.getC()).outToClient
							.writeObject(info + ", " + info2);
					break;
				}
			}
			con.close();

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

}
