package ca.main.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

import ca.main.game.boardGames.TicTacToe15x15;
import ca.main.game.control.GameObjectTypeB;
import ca.main.game.control.KeyInput;
import ca.main.game.gfx.BufferImageLoader;
import ca.main.game.gfx.FontLoader;
import ca.main.game.gfx.Player;
import ca.main.game.gfx.SpriteSheetLoader;
import ca.main.game.gfx.level.Map;
import ca.main.game.gfx.panels.Board;
import ca.main.game.gfx.panels.BoardManager;
import ca.main.game.network.Client;
import ca.main.game.network.OtherPlayersList;
import ca.main.game.network.TCPClient.DbClient;

public class Game extends Canvas implements Runnable {

	private final String SERVER_IP = "10.52.236.246";
	private static InetAddress myIP;
	private static String myIPString;

	public static final int WIDTH = 94 * 4; // 94 size of one tile without
											// borders
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "java 2D game";
	public static int buffering = 3;// it's not suggested to go over triple with
									// buffering

	private boolean running = false;
	private Thread thread;

	private Graphics g;
	boolean ranOnce;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	private Player player;
	private String playerPose;
	private OtherPlayersList otherPlayers;
	private LinkedList<TicTacToe15x15> ticTacToeGameList;

	private SpriteSheetLoader sprite_sheet_loader;
	private BufferImageLoader imageLoader;

	private Map map1;

	private BoardManager boardManager;
	private boolean sthDisplayed;

	private Board scoreBoard;
	private boolean displayScore;
	private String[] scoreInfo;

	private boolean displayGame;
	private Board fancyBoard;
	private boolean ticTacFinished;
	private String ticTacResult;
	
	private BufferedImage toolPanel;
	private BufferedImage spaceBackground;
	private BufferedImage player1Victory;
	private BufferedImage player2Victory;

	private FontLoader fontLog;
	private FontLoader fontScore;

	private boolean login;

	private Client client;
	private DbClient DbClient;

	private String ipAddress = "ipError";

	private int offSetX;
	private int offSetY;
	
	private int updateOnFifth;

	private ArrayList<GameObjectTypeB> typeBObjects;

	/**
	 * construction of necessary items before game can start
	 */
	
	public void init() {
		requestFocus();
		
		try {
			myIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		}
		myIPString = cutLocalIp(myIP.toString());
		
		sprite_sheet_loader = new SpriteSheetLoader();
		imageLoader = new BufferImageLoader();
		boardManager = new BoardManager(this);
		loadBoards();
		
		typeBObjects = new ArrayList<GameObjectTypeB>(); //unsused
		//typeBObjects.add(new GameObjectTypeB(this,50,50,100,100,"Legio")); //unused

		fontLog = new FontLoader(this);
		fontScore = new FontLoader(this);
		map1 = new Map(this, "res/maps/map01.txt");// load map

		addKeyListener(new KeyInput(this));// add keyLister to main game

		player = new Player(300, 200, this, "pinkamena");
		playerPose = "02";
		otherPlayers = new OtherPlayersList();
		ticTacToeGameList = new LinkedList<>();

		login = true;
		updateOnFifth = 0;
		ticTacResult = "";
		
		offSetX = 0;
		offSetY = 0;

		try {
			toolPanel = imageLoader
					.loadImage("/img/toolPanel.png");
			spaceBackground = imageLoader
					.loadImage("/img/space.png");
			
			player1Victory = imageLoader
					.loadImage("/img/boards/tic-tac-toe/playerboard_player1Win.png");
			player2Victory = imageLoader
					.loadImage("/img/boards/tic-tac-toe/playerboard_player2Win.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		ranOnce = false;
		displayScore = false;
		displayGame = false;
		sthDisplayed = false;
		ticTacFinished = false;

		
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
	      addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	            clickActions(me); 
	          } 
	      }); 
	}

	/**
	 * start game thread
	 */
	public synchronized void start() {
		if (running)
			return; // if game is already running don't do anything

		running = true;
		thread = new Thread(this);
		thread.start();

		client = new Client(this, SERVER_IP, 1099);
		client.start();

	}

	/**
	 * stop game thread
	 */

	public synchronized void stop() {

		try {
			client.sendQuit(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}

		System.exit(0);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run() limits amount of updates that games can
	 * make per second
	 */
	public void run() {
		init();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 50D; // how much ns does our game tick
												// take

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		int ticks = 0; // counter for ticks, tick == update basically
		int frames = 0; // counter for frames
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			if (delta >= 1) { // check if game needs tick update
				tick();
				delta--;
				ticks++;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				// System.out.println("frames: "+frames+" ticks: "+ticks); //
				// show frames and ticks per second
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}

	/**
	 * game objects that requires update each time tick happens
	 */
	private void tick() {

		if (login == false && displayGame == false) {
			//System.out.println(player.getX() + " " + player.getY()); //player coordinates
			client.sendPlayerPos((ipAddress + ":" + player.getX() + ":"
					+ player.getY() + ":" + playerPose));
			

			player.tick();// updates player position
		}

	}

	/**
	 * draw all graphics onto player screen screen overwritten every time new
	 * render happens
	 */
	private void render() {

		BufferStrategy bs = this.getBufferStrategy(); // set buffer strategy
														// from our Canvas

		if (bs == null) {
			createBufferStrategy(buffering); // if game's buffer strategy is
												// null, create one
			return;
		}
		g = bs.getDrawGraphics();

		// ////////// Everything we want to draw on our screen /////////
		
		// ====================== login ================================
		if (login) {
			fancyBoard.render(g);
			fontLog.renderNick(g, 0, 170, 190, 45);
			
		// ====================== TicTacToe Game =======================
		} else if (displayGame
				&& client.getTicTacToeNr() < ticTacToeGameList.size()) {
			ticTacToeGameList.get(client.getTicTacToeNr()).render(g); // TODO
																		// new
			if (ticTacFinished) {
				if (ticTacResult.equals("X"))
					g.drawImage(player1Victory, 0, 0, null);
				else if (ticTacResult.equals("O"))
					g.drawImage(player2Victory, 0, 0, null);
			}
		} else {
			
		// ====================== Game lobby ============================	
			g.drawImage(spaceBackground, -100, -100, null);
			map1.render(g, 94, 1, 0, 0); // 94 - borders are already ignored in grab
			//g.drawImage(toolPanel, 0, 540, null);

			
			//typeBObjects.get(0).renderZone(g); used
			for (int i = 0; i < otherPlayers.size(); i++) {
				otherPlayers.get(i).render(g);
			}

			if (displayScore) {
				scoreBoard.render(g);
				fontScore.renderScore(g, 1, 280, 63, 13, 55, scoreInfo);
			}

		}
		// ///////// end of drawing here! /////////////////////////////
		g.dispose();
		bs.show();
	}

	/**
	 * @param e
	 *            controls for player on key down(key pressed)
	 */
	public void keyPressed(KeyEvent e) {
		// String ipAddress="ipError";
		/*
		 * try { ipAddress = InetAddress.getLocalHost().getHostAddress(); }
		 * catch (UnknownHostException e1) { e1.printStackTrace(); }
		 */
		int key = e.getExtendedKeyCode();
		// ================= Controls login ======================
		if (login) {
			if (key == KeyEvent.VK_ENTER) {
				login = false;
				player.setPlayerName(fontLog.getNickName());
				DbClient = new DbClient("client", this, SERVER_IP, 1098);
				client.sendLoginRequest(ipAddress + ":" + player.getName());
				DbClient.sendName("00:" + player.getName());
				System.out.println(player.getName());
			} else if (key == KeyEvent.VK_BACK_SPACE) {
				if (fontLog.getNickName().length() != 0) {
					fontLog.setNickName(fontLog.getNickName().substring(0,
							fontLog.getNickName().length() - 1));
				}
			} else {
				String c = Character.toString((char) key);
				if (fontLog.retrieveCharExistance(c, 0)
						&& fontLog.getNickName().length() != 9) {
					fontLog.addToNickName(c);
				}

			}

			// ================= Controls Tic-Tac-Toe ================
		} else if (displayGame && ticTacFinished) {
			if (key == KeyEvent.VK_ENTER) {
				setDisplayGame(false);
				ticTacFinished = false;
				if (ticTacToeGameList.get(client.getTicTacToeNr()).getPlayer1()
						.equals((getPlayer().getName()))) {
					client.sendEndTicTacToe(client.getTicTacToeNr() + ":"
							+ getPlayer().getName());
				}
				System.out.println(client.getTicTacToeNr() + "");
			}
		} else if (displayGame) {
			if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
				ticTacToeGameList.get(client.getTicTacToeNr()).incPosX(); 
			} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
				ticTacToeGameList.get(client.getTicTacToeNr()).decPosX();
			} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
				ticTacToeGameList.get(client.getTicTacToeNr()).incPosY();
			} else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
				ticTacToeGameList.get(client.getTicTacToeNr()).decPosY();
			} else if (key == KeyEvent.VK_ENTER) {
				if (ticTacToeGameList.get(client.getTicTacToeNr()).yourTurn()) {
					client.sendTicToeToeMark(ticTacToeGameList.get(
							client.getTicTacToeNr()).getSelectorXpos()
							+ ":"
							+ ticTacToeGameList.get(client.getTicTacToeNr())
									.getSelectorYpos()
							+ ":"
							+ ticTacToeGameList.get(client.getTicTacToeNr())
									.getLocalMark());
				}

			} else if (key == KeyEvent.VK_Q || key == KeyEvent.VK_ESCAPE) {
				if (!displayGame && !sthDisplayed) {
					displayGame = true;
					sthDisplayed = true;
				} else if (displayGame) {
					displayGame = false;
					sthDisplayed = false;
				}
			}

			// ================= Controls Lobby ======================
		} else {

			if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
				player.setVelX(5);
				playerPose = "06";
			} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
				player.setVelX(-5);
				playerPose = "04";
			} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
				player.setVelY(5);
				playerPose = "02";
			} else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
				player.setVelY(-5);
				playerPose = "08";
			}
			// Special actions
			else if (key == KeyEvent.VK_5) {
				player.ticTac15x15();
				playerPose = "05";
				client.sendSearchingForPlayer(player.getName() + ":"
						+ ticTacToeGameList.size());
			} else if (key == KeyEvent.VK_6) {
				playerPose = "05";
				client.sendMatchPlayers((player.getName()));
			} else if (key == KeyEvent.VK_E) {
				if (!displayScore && !sthDisplayed) {

					DbClient = new DbClient("client", this, SERVER_IP, 1098);
					DbClient.start();

					DbClient.sendName(player.getName());
					try {
						thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					scoreInfo = DbClient.getAllInfo();
					displayScore = true;
					sthDisplayed = true;

				} else if (displayScore) {
					displayScore = false;
					sthDisplayed = false;
					DbClient.stop();
				}

			} else if (key == KeyEvent.VK_Q) {
				if (!displayGame && !sthDisplayed) {
					displayGame = true;
					sthDisplayed = true;
				} else if (displayGame) {
					displayGame = false;
					sthDisplayed = false;
				}
			}
		}
	}

	/**
	 * @param e
	 *            * controls for player on key released
	 */
	public void keyReleased(KeyEvent e) {

		int key = e.getExtendedKeyCode();

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			player.setVelX(0);

		} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			player.setVelX(0);

		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			player.setVelY(0);

		} else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			player.setVelY(0);

		}
	}

	private void loadBoards() {
		scoreBoard = boardManager.retriveByName("scoreBoard");
		fancyBoard = boardManager.retriveByName("fancyBoard");
	}

	/**
	 * @return SpriteSheetLoader game SpriteSheetLoader stored inside game main
	 *         class it is shared for all classes that require graphics loaded
	 *         from SpriteSheets
	 */
	public SpriteSheetLoader getSpriteSheetLoader() { // fetches "main"
														// spritesheet when
														// other classes need
														// models
		return sprite_sheet_loader;
	}

	public void setDisplayGame(boolean displayGame) {
		this.displayGame = displayGame;

		if (displayGame)
			sthDisplayed = true;
		else
			sthDisplayed = false;
	}
	
	public void addToOffSetX(double offSetX){
		this.offSetX += offSetX;
	}
	
	public void addToOffSetY(double offSetY){
		this.offSetY += offSetY;
	}

	public void setTicTacFinished(boolean ticTacFinished, String lastMark) {
		this.ticTacFinished = ticTacFinished;
		ticTacResult = lastMark;
		System.out.println(ticTacResult);
	}

	public void setTicTacFinished(boolean ticTacFinished) {
		this.ticTacFinished = ticTacFinished;
	}

	public BoardManager getBoardManager() {
		return boardManager;
	}

	public String getServerIp() {
		return SERVER_IP;
	}
	
	public String getMyIP(){
		return myIPString;
	}

	public void setRunning(boolean b) {
		running = b;

	}
	
	public boolean isInsideSolidObject(){
		for (int objectANr = 0; objectANr < typeBObjects.size(); objectANr++){
			if(typeBObjects.get(0).isInside(player.getCenter(), 0, 0))return true;
			
		}
		return false;
	}

	/**
	 * @return game frame width
	 */
	public int getFrameWidth() {
		return WIDTH;
	}

	public BufferImageLoader getImageLoader() {
		return imageLoader;
	}

	/**
	 * @return game frame height
	 */
	public int getFrameHeight() {
		return HEIGHT;
	}

	public OtherPlayersList getOtherPlayers() {
		return otherPlayers;
	}

	public LinkedList<TicTacToe15x15> getTicTacToeGameList() {
		return ticTacToeGameList;
	}
	
	public void clickActions(MouseEvent click){
		System.out.println(click.getX()+" "+click.getY());
		
	}

	public DbClient getdbClient() {
		return DbClient;
	}

	public Player getPlayer() {
		return player;
	}
	
	public ArrayList<GameObjectTypeB> getSolidObjects(){
		return typeBObjects;
	}
	
	public String cutLocalIp(String localIp){
		for (int i = 0; i < localIp.length(); i++){
			if(localIp.charAt(i)=='/'){
				return localIp.substring(i+1, localIp.length());
			}
		}
		return "error";
	}

	public Graphics getG() {
		return g;
	}

	/**
	 * @param args
	 *            building up JAVA frame, adding items and game, starting game
	 */
	public static void main(String args[]) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		// ============= Main Frame ==========================
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack(); // basically pack everything together/extends window class
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				game.setRunning(false);
			}
		});
		game.start();// call on game to start
	}

	public void setDisplayTicTacToe(boolean b) {
		displayGame = true;

	}
}
