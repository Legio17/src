package ca.main.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ca.main.game.control.KeyInput;
import ca.main.game.gfx.FontLoader;
import ca.main.game.gfx.Player;
import ca.main.game.gfx.SpriteSheetLoader;
import ca.main.game.gfx.level.Map;
import ca.main.game.gfx.panels.Board;
import ca.main.game.gfx.panels.BoardManager;
import ca.main.game.network.GameClient;
import ca.main.game.network.GameServer;

public class Game extends Canvas implements Runnable{

	public static final int WIDTH = 94*4; // 94 size of one tile without borders
	public static final int HEIGHT = WIDTH / 12 *9; 
	public static final int SCALE = 2;
	public final String TITLE = "java 2D game";
	public static int buffering = 3;// it's not suggested to go over triple with buffering
	

	private boolean running = false;
	private Thread thread;
	
	private Graphics g;	
	boolean ranOnce;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	private Player player;
	private SpriteSheetLoader sprite_sheet_loader;
	
	private Map map1;
	
	private BoardManager boardManager;
	private boolean sthDisplayed;
	private Board scoreBoard;
	private boolean displayScore;
	private Board gameBoard;
	private boolean displayGame;
	private Board fancyBoard;
	
	private FontLoader fontLog;
	
	private boolean login;
	
	private GameClient socketClient;
	private GameServer socketServer;
	
	/**
	 * 	construction of necessary items before game can start
	 */
	public void init(){
		requestFocus();
		sprite_sheet_loader = new SpriteSheetLoader();
		boardManager = new BoardManager(this);
		loadBoards();
		
		fontLog = new FontLoader(this);
		
		map1 = new Map(this,"res/maps/map01.txt");//load map
		
		addKeyListener(new KeyInput(this));//add keyLister to main game
		
		player = new Player(100,100,this,"applejack");
		
		login = true;
		
		ranOnce = false;
		displayScore = false;
		displayGame = false;
		sthDisplayed = false;
		
		socketClient.sendData("ping".getBytes());
		
	}

	/**
	 * start game thread
	 */
	public synchronized void start(){
		if (running) return; //if game is already running don't do anything 
			
		running = true;
		thread = new Thread(this);
		thread.start();
		
		if (JOptionPane.showConfirmDialog(this, "start server? ") == 0){
			socketServer = new GameServer(this, 4200);
			socketServer.start();
		}
		
		socketClient = new GameClient(this, "localhost", 4200);
		socketClient.start();
	}
	
	
	/**
	 * 	stop game thread
	 */
	public synchronized void stop(){
		if (!running) return; //if game is already dead ignore
		
		running = false;
		try{
			thread.join(); //groups up all running threads and kills them
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * limits amount of updates that games can make per second
	 */
	public void run(){
		init();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D; //how much ns does our game tick take
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		int ticks = 0; //counter for ticks, tick == update basically
		int frames = 0;	//counter for frames
		long timer = System.currentTimeMillis();
		
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime)/nsPerTick;
			lastTime = now;
			
			if (delta >= 1){ //check if game needs tick update
				tick();
				delta--; 
				ticks ++;
			}
			render();
			frames ++;
			
			if (System.currentTimeMillis() - lastTimer >= 1000 )
			{
				lastTimer += 1000;
				System.out.println("frames: "+frames+" ticks: "+ticks); // show frames and ticks per second
				ticks = 0;
				frames= 0;
			}
		}
		stop();
	}
	
	/**
	 * game objects that requires update each time tick happens
	 */
	private void tick(){
		player.tick();//updates player position
		
		
	}
	
	/**
	 * draw all graphics onto player screen
	 * screen overwritten every time new render happens
	 */
	private void render(){
		
		BufferStrategy bs = this.getBufferStrategy(); //set buffer strategy from our Canvas
		
		if (bs == null){
			createBufferStrategy(buffering); //if game's buffer strategy is null, create one
			return;
		}
		g = bs.getDrawGraphics();


		//////////// Everything we want to draw on our screen /////////
		if (login){
			fancyBoard.render(g);	
			fontLog.renderNick(g, 0, 170, 190, 30);
		} else {
			map1.render(g, 94, 1); //94 - borders are already ignored in grab image
			player.render(g);
			
			if (displayScore) scoreBoard.render(g);
			else if (displayGame) gameBoard.render(g);
		}
		/////////// end of drawing here! /////////////////////////////
		g.dispose();
		bs.show();
	}
	
	
	/**
	 * @param e
	 * controls for player on key down(key pressed)
	 */
	public void keyPressed(KeyEvent e){
		int key = e.getExtendedKeyCode();
		
		if (login){
			if(key == KeyEvent.VK_ENTER){
				login = false;
				player.setPlayerName(fontLog.retriveNickName());
				System.out.println(player.getPlayerName());
			} else{
				String c = Character.toString((char)key);
				fontLog.addToNickName(c);
				
			}
		} else{
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
			player.setVelX(5);
		}else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
			player.setVelX(-5);
		}else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
			player.setVelY(5);
		}else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
			player.setVelY(-5);
			
		// Special actions
		}else if(key == KeyEvent.VK_E){
			if (!displayScore && !sthDisplayed){ 
				displayScore = true;
				sthDisplayed = true;
			}
			else if (displayScore){
				displayScore = false;
				sthDisplayed = false;
			}
			
		}else if(key == KeyEvent.VK_Q){
			if (!displayGame && !sthDisplayed){
				displayGame = true;
				sthDisplayed = true;
			}
			else if (displayGame){
				displayGame = false;
				sthDisplayed = false;
			}
		}}
	}
	
	/**
	 * @param e
	 * * controls for player on key released
	 */
	public void keyReleased(KeyEvent e){
		int key = e.getExtendedKeyCode();
		
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
			player.setVelX(0);
		}else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
			player.setVelX(0);
		}else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
			player.setVelY(0);
		}else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
			player.setVelY(0);
		}
	}
	
	private void loadBoards() {
		scoreBoard = boardManager.retriveByName("scoreBoard");
		gameBoard = boardManager.retriveByName("gameBoard");
		fancyBoard = boardManager.retriveByName("fancyBoard");
	}
	
	

	/**
	 * @return SpriteSheetLoader
	 * game SpriteSheetLoader stored inside game main class
	 * it is shared for all classes that require graphics loaded from SpriteSheets
	 */
	public SpriteSheetLoader getSpriteSheetLoader() { //fetches "main" spritesheet when other classes need models
		return sprite_sheet_loader;
	}
	
	/**
	 * @return game frame width
	 */
	public int getFrameWidth(){
		return WIDTH;
	}
	
	/**
	 * @return game frame height
	 */
	public int getFrameHeight(){
		return HEIGHT;
	}
	
	/**
	 * @param args
	 * building up JAVA frame, adding items and game, starting game
	 */
	public static void main(String args[]){
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE)); 
		game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE)); 
		game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		//============= Main Frame ==========================
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack(); //basically pack everything together/extends window class
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();//call on game to start
	}
}
