package client.boardGames;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Game;
import client.gfx.BufferImageLoader;
import client.gfx.FontLoader;
import client.gfx.panels.Board;
import client.gfx.panels.BoardManager;
import client.network.TCPClient.DbClient;

public class TicTacToe15x15 {

	private Game game;

	private BufferImageLoader loader;

	private BoardManager bm;
	private Board gameBoard;

	private BufferedImage cross;
	private BufferedImage circle;
	private BufferedImage select;

	private final int posXCor;
	private final int posYCor;
	private final int size;

	private int posX;
	private int posY;

	private int selectorPosX;
	private int selectorPosY;

	private int max;
	private int min;

	private String localMark;
	private String lastMark;
	
	private FontLoader player1Font;
	private FontLoader player2Font;

	private String[][] array;

	private String latestCol;
	private String latestRow;

	private String player1;
	private String player2;
	private String localPlayer;
	private DbClient dbClient;

	public TicTacToe15x15(Game game, String player1) {
		localPlayer = game.getPlayer().getName();
		//dbClient = game.getdbClient();

		this.player1 = player1;
		player2 = "NotSet";
		this.game = game;
		loader = new BufferImageLoader();
		bm = game.getBoardManager();

		player1Font = new FontLoader(game);
		player2Font = new FontLoader(game);
		
		posXCor = 204;
		posYCor = 17;
		size = 36;
		init();
	}

	public void init() {
		try {
			
			posX = posXCor;
			posY = posYCor;

			selectorPosX = 0;
			selectorPosY = 0;

			min = 0;
			max = 14;
			lastMark = "O";

			initializeArray();

			cross = loader.loadImage("/img/boards/tic-tac-toe/cross.png");
			circle = loader.loadImage("/img/boards/tic-tac-toe/circle.png");
			select = loader.loadImage("/img/boards/tic-tac-toe/select.png");

			gameBoard = bm.retriveByName("ticTacToeBoard");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void render(Graphics g) {
		gameBoard.render(g);
		player1Font.renderWordOnPosition(g, 2, 56, 65, player1, 13);
		player2Font.renderWordOnPosition(g, 2, 56, 260, player2, 13);
		g.drawImage(select, posX, posY, null);

		for (int col = 0; col < array.length; col++) {
			for (int row = 0; row < array[0].length; row++) {
				if (array[col][row] == null) {

				} else if (array[col][row].equals("X")) {
					g.drawImage(cross, posXCor + (size * col), posYCor
							+ (size * row), null);
				} else if (array[col][row].equals("O")) {
					g.drawImage(circle, posXCor + (size * col), posYCor
							+ (size * row), null);
				}
			}
		}

	}

	public void incPosX() {
		if (selectorPosX + 1 <= max) {
			posX += size;
			selectorPosX += 1;
		}
	}

	public void decPosX() {
		if (selectorPosX - 1 >= min) {
			posX -= size;
			selectorPosX -= 1;
		}
	}

	public void incPosY() {
		if (selectorPosY + 1 <= max) {
			posY += size;
			selectorPosY += 1;
		}
	}

	public void decPosY() {
		if (selectorPosY - 1 >= min) {
			posY -= size;
			selectorPosY -= 1;
		}
	}

	public void initializeArray() {
		array = new String[15][15];

		for (int col = 0; col < array.length; col++) {

			for (int row = 0; row < array[0].length; row++) {
				array[col][row] = null;

			}
		}

	}

	public String getResult() {
		if (getPlayer1().equals(localPlayer)) {
			if (lastMark.equals("X")) {
				return "won";
			} else
				return "lost";
		}
		return null;
	}

	public void mark(int col, int row, String mark) {
		if (array[col][row] != null)
			return;

		array[col][row] = mark;
		lastMark = mark;
		StringBuffer buf = new StringBuffer();

		/*
		 * for (int i = 0; i < array.length; i++) { for (int j = 0; j <
		 * array.length; j++) { buf.append(array[j][i]); buf.append('\t'); }
		 * buf.append('\n'); } System.out.println(buf.toString());
		 */
		if (Count.isThereFive(5, array)) {
			game.setTicTacFinished(true, lastMark);
			
			if(getPlayer1().equals(localPlayer))
			{
				dbClient = new DbClient("client", game, game.getServerIp(), 1098);	
			String info = "01:" + getPlayer1() + ":" + getPlayer2() + ":"
					+ "15X15" + ":" + "12/05/2016" + ":" + getResult();
			System.out.println(info);
		    dbClient.sendName(info);

		}}

	}

	public String getLatestCol() {
		return latestCol;
	}

	public String getLatestRow() {
		return latestRow;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
		if (localPlayer.equals(player1))
			localMark = "X";
		else
			localMark = "O";
	}

	public String getLocalMark() {
		return localMark;
	}

	public String getPlayer1() {
		return player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public int getSelectorXpos() {
		return selectorPosX;
	}

	public int getSelectorYpos() {
		return selectorPosY;
	}

	public boolean yourTurn() {
		if (lastMark.equals(localMark))
			return false;
		return true;
	}

}
