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
	private int ticTacToeNr;

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

	public void run() {

		while (true) {
			byte[] data = new byte[35];
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
	 * Deletes the player, who quit the game, from the OtherPlayersList so his/her
	 * character disappears from the screen.
	 * 
	 * @param array
	 *            contains the information about the other player who quit the
	 *            game and has to be deleted from the list
	 */
	private void quitGame(String[] array) {
		for (int i = 0; i < game.getOtherPlayers().size(); i++) {
			if (game.getOtherPlayers().get(i).getIpAddress().equals(array[1])) {
				game.getOtherPlayers().removeOtherPlayer(i);
				break;
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
	 *            the data that needs to be broadcasted to the other players so it
	 *            is known that the sender wants to start a game
	 */
	public void sendSearchingForPlayer(String data) {
		data = "04:" + data + ":";
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
		data = "06:" + data + ":" + ticTacToeNr + ":";
		DatagramPacket packet = new DatagramPacket(data.getBytes(),
				data.getBytes().length, ipAddress, port);

		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
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

	public void sendEndTicTacToe(String data) {
		data = "07:" + data + ":";
		DatagramPacket packet = new DatagramPacket(data.getBytes(),
				data.getBytes().length, ipAddress, port);
		System.out.println("Sending remove " + data);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends data to the server for quitting the game so the player who quits
	 * will disappear from the screen of all the other clients.
	 * 
	 * @param data
	 *            is the information sent to the server
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

	public String getClientName() {
		return name;
	}

	private void movePlayer(String[] array) {

		double coordinateX = Double.parseDouble(array[2]);
		double coordinateY = Double.parseDouble(array[3]);
		array[4] = array[4].substring(00, 02);
		boolean found = false;
		int index = 0;
		for (int i = 0; i < game.getOtherPlayers().size(); i++) {
			if (array[1].equalsIgnoreCase(game.getOtherPlayers().get(i)
					.getIpAddress())) {
				found = true;
				index = i;
				break;
			}
		}
		if (!found) {
			game.getOtherPlayers().addOtherPlayer(new PlayerMP(game, array[1]));
		}

		game.getOtherPlayers().get(index)
				.setCoord(coordinateX, coordinateY, array[4]);

	}

	private void searchForPlayer(String[] array) {
		String player1 = array[1];
		int gameCreatedNum = Integer.parseInt(array[2]);
		if (game.getTicTacToeGameList().size() == gameCreatedNum) {
			game.getTicTacToeGameList().add(new TicTacToe15x15(game, player1));
		}
	}

	/**
	 * Matching....
	 * 
	 * @param array
	 */
	private void matchPlayers(String[] array) {

		String player2 = array[1];
		player2Set = false;

		for (int i = 0; i < game.getTicTacToeGameList().size(); i++) {
			if (game.getTicTacToeGameList().get(i).getPlayer2().equals(player2)) {
				player2Set = true;
			}
		}

		if (!player2Set) {
			for (int j = 0; j < game.getTicTacToeGameList().size(); j++) {
				if (game.getTicTacToeGameList().get(j).getPlayer2()
						.equals(game.getPlayer().getName())) {
					return;
				}

				if (game.getTicTacToeGameList().get(j).getPlayer2()
						.equals("NotSet")) {
					for (int games = 0; games < game.getTicTacToeGameList()
							.size(); games++) {
						System.out
								.println("======== TEST =========================="
										+ " game nr " + games);
						System.out.println(game.getTicTacToeGameList()
								.get(games).getPlayer2()
								+ " " + player2);
						System.out.println(game.getTicTacToeGameList()
								.get(games).getPlayer2().length()
								+ " " + player2.toString());
						System.out.println(game.getTicTacToeGameList()
								.get(games).getPlayer2().equals(player2));
						if (game.getTicTacToeGameList().get(games).getPlayer2()
								.equals(player2)) {
							return;
						}
					}
					game.getTicTacToeGameList().get(j).setPlayer2(player2);
					if (amIPlayer1(j) || amIPlayer2(j)) {
						game.setDisplayTicTacToe(true);
						ticTacToeNr = j;
						System.out.println("Matching players....."
								+ game.getTicTacToeGameList().get(j)
										.getPlayer1()
								+ " "
								+ game.getTicTacToeGameList().get(j)
										.getPlayer2() + " " + ticTacToeNr);
					}
				}
			}
		}
	}

	private boolean amIPlayer1(int i) {
		return game.getTicTacToeGameList().get(i).getPlayer1()
				.equals(game.getPlayer().getName());
	}

	private boolean amIPlayer2(int i) {
		return game.getTicTacToeGameList().get(i).getPlayer2()
				.equals(game.getPlayer().getName());
	}

	private void ticTacToeMark(String[] array) {

		int receivedTicTacToeNr = Integer.parseInt(array[4]);
		if (ticTacToeNr == receivedTicTacToeNr) {
			int col = Integer.parseInt(array[1]);
			int row = Integer.parseInt(array[2]);

			game.getTicTacToeGameList().get(ticTacToeNr)
					.mark(col, row, array[3]);
		}
	}

	private void removeTicTacToe(String[] array) {
		String player1 = array[2].trim();
		String player2 = "";
		int removeAtPos = -1;

		for (int i = 0; i < game.getTicTacToeGameList().size(); i++) {
			if (game.getTicTacToeGameList().get(i).getPlayer1().trim()
					.equals(player1)) {
				removeAtPos = i;
				System.out.println("game Existence " + i);
				player2 = game.getTicTacToeGameList().get(i).getPlayer2()
						.trim();
			}
		}

		if (removeAtPos != -1) {
			if (game.getPlayer().getName().trim().equals(player1)) {
				System.out.println("cond1 " + game.getPlayer().getName());
				game.setDisplayGame(false);
				game.setTicTacFinished(false);
			} else if (game.getPlayer().getName().trim().equals(player2)) {
				System.out.println("cond2 " + game.getPlayer().getName());
				game.setDisplayGame(false);
				game.setTicTacFinished(false);
			}
			game.getTicTacToeGameList().remove(removeAtPos);
		}
	}

	public int getTicTacToeNr() {
		return ticTacToeNr;
	}
}
