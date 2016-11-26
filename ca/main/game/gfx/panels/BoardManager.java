package ca.main.game.gfx.panels;

import java.util.ArrayList;

import ca.main.game.Game;
import ca.main.game.utilities.simpleMethods;

public class BoardManager {
	
	private ArrayList<Board> boardList;
	private ArrayList<String> boardListNames;
	
	private Game game;
	private int gameWidth;
	private int gameHeight;
	
	public BoardManager(Game game){
		boardList = new ArrayList<Board>();
		boardListNames = new ArrayList<String>();
		this.game = game;
		gameWidth = game.getWidth();
		gameHeight = game.getHeight();
		
		loadAllBoards();
	}
	
	public void loadAllBoards(){
		boardList.add(new Board(gameWidth, gameHeight,"fancyBoard", 762, 566,"/img/boards/fancyWood_background2.png"));
		boardListNames.add(boardList.get(boardList.size() - 1).getBoardName());
		
		boardList.add(new Board(gameWidth, gameHeight,"scoreBoard", 640, 420,"/img/boards/score_background.png","/img/boards/paper_background.png"));
		boardListNames.add(boardList.get(boardList.size() - 1).getBoardName());
		
		boardList.add(new Board(gameWidth, gameHeight,"gameBoard", 640, 420,"/img/boards/score_background.png","/img/boards/paper_background.png", "/img/boards/grid00_background.png"));
		boardListNames.add(boardList.get(boardList.size() - 1).getBoardName());
	}
	
	public Board retriveByName(String name){
		return simpleMethods.retriveFromListByName_Board(name, boardListNames, boardList);
	}
	
	
}
