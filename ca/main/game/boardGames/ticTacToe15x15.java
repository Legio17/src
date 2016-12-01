package ca.main.game.boardGames;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import ca.main.game.Game;
import ca.main.game.gfx.BufferImageLoader;
import ca.main.game.gfx.panels.Board;
import ca.main.game.gfx.panels.BoardManager;

public class TicTacToe15x15 {

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
	
	private String currentMark; 
	
	private String[][] array;
	
	private String player1;
	private String player2;

	public TicTacToe15x15(Game game, String player1) {
		this.player1 = player1;
		loader = new BufferImageLoader();
		bm = game.getBoardManager();

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
			
			currentMark = "X";

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
		g.drawImage(select, posX, posY, null);
		
		for (int col = 0; col < array.length; col ++){
			for (int row = 0; row < array[0].length; row ++){
				if (array[col][row] == "X"){
					g.drawImage(cross, posXCor+(size*col), posYCor+(size*row), null);
				} else if(array[col][row] == "O"){
					g.drawImage(circle, posXCor+(size*col), posYCor+(size*row), null);
				}
			}
		}
	}
	
	public void incPosX(){
		if (selectorPosX + 1 <= max){
			posX += size;
			selectorPosX += 1;
		}		
	}
	
	public void decPosX(){
		if (selectorPosX - 1 >= min){
			posX -= size;
			selectorPosX -= 1;
		}
	}
	
	public void incPosY(){
		if (selectorPosY + 1 <= max){
			posY += size; 
			selectorPosY += 1;
		}
	}
	
	public void decPosY(){
		if (selectorPosY - 1 >= min){
			posY -= size;
			selectorPosY -= 1;
		}
	}
	
	public void initializeArray(){
		array = new String[15][15];
		
		for (int col = 0; col < array.length; col ++){

			for (int row = 0; row < array[0].length; row++){
				array[col][row] = null;
				
			}
		}
	}
	
	public void mark(){
		if (array[selectorPosX][selectorPosY] == null){
			array[selectorPosX][selectorPosY] = currentMark;
			switchMark();
			//System.out.println(Count.isThereFive(5, array));
		}
	}
	
	public void mark(int col, int row){
		if (array[col][row] == null){
			array[col][row] = currentMark;
			switchMark();
		}
	}

	private void switchMark() {
		if (currentMark.equals("X"))currentMark = "O";
		else currentMark = "X";
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getPlayer1() {
		return player1;
	}
	
	public String getPlayer2() {
		return player2;
	}
	
	
	

}
