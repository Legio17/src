package ca.main.game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import ca.main.game.Game;
import ca.main.game.boardGames.TicTacToe15x15;

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
	private ArrayList<String> ipList;

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

			// System.out.println(message.substring(0, 2));
			// System.out.println(message);
			String[] array = message.split(":");

			if (array[0].equals("03")) {
				movePlayer(array);
			}
			if (array[0].equals("04")) {
				searchForPlayer(array);
			}
			if (array[0].equals("05")) {
				matchPlayers(array);
			}
			if (array[0].equals("06")) {
				ticTacToeMark(array);
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
		data = "06:" + data + ":";
		System.out.println("Client sending mark position: " + data);
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

	public String getClientName() {
		return name;
	}

	private void movePlayer(String[] array) {

		double coordinateX = Double.parseDouble(array[2]);
		double coordinateY = Double.parseDouble(array[3]);
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
				.setCoord(coordinateX, coordinateY, array[4].substring(00, 02));

	}

	private void searchForPlayer(String[] array) {
		String player1 = array[1];
		game.getTicTacToeGameList().add(new TicTacToe15x15(game, player1));
		System.out.println("TicTacToe game CREATED"
				+ (game.getTicTacToeGameList().get(0) != null));
	}
	
	/**
	 * Matching....
	 * @param array
	 */
	private void matchPlayers(String[] array) {
		String player2 = array[1];
		System.out.println("PLAYER1 " + amIPlayer1());
		System.out.println("PLAYER2 " + amIPlayer2(player2));
		
		for(int i = 0; i < game.getTicTacToeGameList().size(); i++){
			if(game.getTicTacToeGameList().get(i).getPlayer2() == null){
				game.getTicTacToeGameList().get(i).setPlayer2(player2);
			}
		}
		System.out.println("Matching players....."
				+ game.getTicTacToeGameList().get(0).getPlayer1() + " "
				+ game.getTicTacToeGameList().get(0).getPlayer2());
		if (amIPlayer1() || amIPlayer2(player2)) {
			game.setDisplayTicTacToe(true);
		}
	}

	private boolean amIPlayer1() {
		return game.getTicTacToeGameList().get(0).getPlayer1()
				.equals(game.getPlayer().getName());
	}

	private boolean amIPlayer2(String player2) {
		return player2.equals(game.getPlayer().getName());
	}

	private void ticTacToeMark(String[] array) {
		System.out.println("Client recieving mark info: " + array[0] + " "
				+ array[1] + " " + array[2] + " " + array[3]);
		int col = Integer.parseInt(array[1]);
		int row = Integer.parseInt(array[2]);

		System.out.println(col + " " + row);
		game.getTicTacToeGameList().get(0).mark(col, row, array[3]);

	}
}
