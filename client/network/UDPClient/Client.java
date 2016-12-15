package client.network.UDPClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import client.Game;
import client.boardGames.TicTacToe15x15;

/**
 * A class representing a client that receives data packets from a server or
 * send data packets to it
 * 
 * @author Filip Hudec, Signe Rasmussen, Peter Miodrag Varanic, Adam Szekely,
 *         Ana Iulia Chifor
 *
 */
public class Client extends Thread {

	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
	private int port;
	private String name;
	private boolean player2Set;
	private int yourGameID;

	/**
	 * 
	 * @param game
	 *            the game/GUI that based on your actions sends data to the
	 *            server through the client. The also utilizes the data the
	 *            client receives.
	 * @param ipAddress
	 *            the IP address of the server that the client should connect to
	 * @param port
	 *            the port of the server that the client should connect through
	 */
	public Client(Game game, String ipAddress, int port) {
		this.game = game;
		this.port = port;
		player2Set = false;

		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The run method for the Thread which continuously receives new data and
	 * act on it accordingly
	 */
	public void run() {

		while (true) {
			byte[] data = new byte[40];
			DatagramPacket packet = new DatagramPacket(data, data.length);

			try {
				socket.receive(packet);

			} catch (IOException e) {
				e.printStackTrace();
			}
			String message = new String(packet.getData());
			String[] array = message.split(":");

			if (array[0].equals("03")) {
				movePlayer(array);
			} else if (array[0].equals("04")) {
				searchForPlayer(array);
			} else if (array[0].equals("05")) {
				matchPlayers(array);
			} else if (array[0].equals("06")) {
				ticTacToeMark(array);
			} else if (array[0].equals("07")) {
				removeTicTacToe(array);
			} else if (array[0].equals("08")) {
				quitGame(array);
			}
		}
	}

	/**
	 * Sends data with this information:
	 * (ipAddress+":"+player.getX()+":"+player.getY()+":"+playerPose) The local
	 * IP address send together with the coordinates as well as which pose the
	 * player avatar is having.
	 * 
	 * @param data
	 *            the position of the player as a string
	 */
	public void sendPlayerPos(String data) {
		data = "03:" + data;
		DatagramPacket packet = new DatagramPacket(data.getBytes(),
				data.getBytes().length, ipAddress, port);

		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends data with the identifier 04 and it takes for example the local
	 * player's name as a parameter. This method is used for signaling that the
	 * local player is searching for someone else to play tic-tac-toe with.
	 * 
	 * @param data
	 *            the data that needs to be broadcast to the other players so it
	 *            is known that the sender wants to start a game
	 */
	public void sendSearchingForPlayer(String data) {
		data = "04:" + data + ":";
		String name = data.split(":")[1];
		for (int gameListNr = 0; gameListNr < game.getTicTacToeGameList()
				.size(); gameListNr++) {
			if (name.equals(game.getTicTacToeGameList().get(gameListNr)
					.getPlayer1())) {
				return;
			}
		}
		DatagramPacket packet = new DatagramPacket(data.getBytes(),
				data.getBytes().length, ipAddress, port);

		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send that you are trying to enter a tic-tac-toe game with a searching
	 * player
	 * 
	 * @param data
	 */
	public void sendMatchPlayers(String data) {
		data = "05:" + data + ":";
		DatagramPacket packet = new DatagramPacket(data.getBytes(),
				data.getBytes().length, ipAddress, port);

		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send where the player want to put their tic-tac-toe mark
	 * 
	 * @param data
	 */
	public void sendTicToeToeMark(String data) {
		data = "06:" + data + ":" + yourGameID + ":";
		DatagramPacket packet = new DatagramPacket(data.getBytes(),
				data.getBytes().length, ipAddress, port);

		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send when the user login to the game to make them connect to the server
	 * 
	 * @param data
	 */
	public void sendLoginRequest(String data) {
		data = "00:" + data;
		DatagramPacket packet = new DatagramPacket(data.getBytes(),
				data.getBytes().length, ipAddress, port);

		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send when the user tries to exit a tic-tac-toe game. It's purpose is send
	 * data with the necessary information to remove the tic-tac-toe game of the
	 * list of games and set the display to false.
	 * 
	 * @param data
	 */
	public void sendEndTicTacToe(String data) {
		data = "07:" + data + ":";
		DatagramPacket packet = new DatagramPacket(data.getBytes(),
				data.getBytes().length, ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * For when the user exits the application so the server can remove the client from the connection list
	 * @param data
	 */
	public void sendQuit(String data) {
		data = "08:" + data + ":";
		DatagramPacket packet = new DatagramPacket(data.getBytes(),
				data.getBytes().length, ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return ID you the tic-tac-toe game the user is playing
	 */
	public int getYourGameID() {
		return yourGameID;
	}

	/*
	 * @param array containing the a players new position. this method will update their position variables in the Game class. 
	 */
	private void movePlayer(String[] array) {

		double coordinateX = Double.parseDouble(array[2]);
		double coordinateY = Double.parseDouble(array[3]);
		boolean found = false;
		int index = 0;
		for (int i = 0; i < game.getOtherPlayers().size(); i++) {
			if ((array[1].equals(game.getOtherPlayers().get(i).getIpAddress()))
					&& (array[5]
							.equals(game.getOtherPlayers().get(i).getName()))) {
				found = true;
				index = i;
				break;
			}
		}
		if (!found) {
			game.getOtherPlayers().addOtherPlayer(
					new PlayerMP(game, array[1], array[5]));
		}

		game.getOtherPlayers().get(index)
				.setCoord(coordinateX, coordinateY, array[4]);
	}

	/*
	 * recieving hosting request from server
	 * 
	 * @param array data with information about player 1 needed to create a new tic-tac-toe
	 */
	private void searchForPlayer(String[] array) {
		String player1 = array[1];
		int gameID = Integer.parseInt(array[2]);

		for (int i = 0; i < game.getTicTacToeGameList().size(); i++) {
			if ((game.getTicTacToeGameList().get(i).getPlayer1()
					.equals(player1))
					|| (game.getTicTacToeGameList().get(i).getGameID() == gameID)) {
				return;
			}
		}
		game.getTicTacToeGameList().add(
				new TicTacToe15x15(game, player1, gameID));
	}

	/*
	 * When a user tries to join a tic-tac-toe game this code will be executed.
	 * The user will whom is trying to join a game will be set as player 2 for
	 * the first available game
	 * 
	 * @param array
	 *            with data about which user that tries to join a game
	 */
	private void matchPlayers(String[] array) { // Receiving player2 who wants
												// to join someponies game

		String player2 = array[1];
		player2Set = false;

		for (int i = 0; i < game.getTicTacToeGameList().size(); i++) {
			if (game.getTicTacToeGameList().get(i).getPlayer2().equals(player2)) {
				player2Set = true;
			}
		}

		if (!player2Set) {
			for (int gameListNr = 0; gameListNr < game.getTicTacToeGameList()
					.size(); gameListNr++) {
				if ((game.getTicTacToeGameList().get(gameListNr).getPlayer2()
						.equals("NotSet"))) {
					game.getTicTacToeGameList().get(gameListNr)
							.setPlayer2(player2);// set player2 for game locally

					if (game.getTicTacToeGameList().get(gameListNr)
							.getPlayer1().equals(game.getPlayer().getName())) {
						game.setDisplayGame(true);
						yourGameID = game.getTicTacToeGameList()
								.get(gameListNr).getGameID();
					} else if (game.getTicTacToeGameList().get(gameListNr)
							.getPlayer2().equals(game.getPlayer().getName())) {
						game.setDisplayGame(true);
						yourGameID = game.getTicTacToeGameList()
								.get(gameListNr).getGameID();
					}
					return;
				}

			}
		}
	}

	/*
	 * 
	 * @param array
	 *            of data for where to put a tic-tac-toe mark
	 */
	private void ticTacToeMark(String[] array) {

		int GameID = Integer.parseInt(array[4]);
		if (yourGameID == GameID) {
			int col = Integer.parseInt(array[1]);
			int row = Integer.parseInt(array[2]);

			for (int gameListNr = 0; gameListNr < game.getTicTacToeGameList()
					.size(); gameListNr++) {
				if (game.getTicTacToeGameList().get(gameListNr).getGameID() == yourGameID) {
					game.getTicTacToeGameList().get(gameListNr)
							.mark(col, row, array[3]);
				}
			}

		}
	}

	/*
	 * @param array
	 *            the data that contains the information for which game to
	 *            remove
	 */
	private void removeTicTacToe(String[] array) {
		String player1 = array[1].trim();
		String player2 = "";
		int removeAtPos = -1;

		for (int i = 0; i < game.getTicTacToeGameList().size(); i++) {
			if (game.getTicTacToeGameList().get(i).getPlayer1().trim()
					.equals(player1)) {
				removeAtPos = i;
				player2 = game.getTicTacToeGameList().get(i).getPlayer2()
						.trim();
			}
		}

		if (removeAtPos != -1) {
			if (game.getPlayer().getName().trim().equals(player1)) {
				game.setDisplayGame(false);
				game.setTicTacFinished(false);
			} else if (game.getPlayer().getName().trim().equals(player2)) {
				game.setDisplayGame(false);
				game.setTicTacFinished(false);
			}
			game.getTicTacToeGameList().remove(removeAtPos);
		}
	}

	/*
	 * Removes a player from the game when they quit it
	 * 
	 * @param array data with the ip and name of the player quiting the game
	 */
	private void quitGame(String[] array) {
		for (int i = 0; i < game.getOtherPlayers().size(); i++) {
			if ((game.getOtherPlayers().get(i).getIpAddress().equals(array[1]))
					&& (game.getOtherPlayers().get(i).getName()
							.equals(array[2]))) {
				game.getOtherPlayers().removeOtherPlayer(i);
				break;
			}
		}
	}

}
