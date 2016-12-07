package ca.main.game.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ca.main.game.Game;

/**
 * @author Legio-PC
 * loads up all attributes for the player including spawn position
 * this also includes graphics used for rendering player
 */
public class Player {
	
	private double x;
	private double y;
	
	private double velX = 0; //both velocities are purely for increasing smoothness of movement
	private double velY = 0; //they are not necessary, but movement looks nicer
	
	private String playerName;
	private int size;
	
	SpriteSheetLoader sprite_sheet_loader;
	SpriteSheet player_sheet;
	
	private BufferedImage player;
	private BufferedImage player_right;
	private BufferedImage player_left;
	private BufferedImage player_up;
	private BufferedImage player_down;
	private BufferedImage player_sit;
	
	
	/**
	 * @param x
	 * @param y
	 * @param game
	 * @param model_nr
	 * creates and assign player to world
	 */
	public Player(double x, double y, Game game, int model_nr){
		this.x = x;
		this.y = y;
		this.size = 96;
		
		sprite_sheet_loader = game.getSpriteSheetLoader();//fetch SpriteSheet from Game class
		player_sheet = sprite_sheet_loader.retrivePlayerModel(model_nr);
		loadBasicPositioning();
		player = player_down; //player facing this pos after spawned
	}
	
	/**
	 * @param x
	 * @param y
	 * @param game
	 * @param model_name
	 * creates and assign player to world
	 */
	public Player(double x, double y, Game game, String model_name){
		this.x = x;
		this.y = y;
		
		sprite_sheet_loader = game.getSpriteSheetLoader();//fetch SpriteSheet from Game class
		player_sheet = sprite_sheet_loader.retrivePlayerModel(model_name);
		loadBasicPositioning();
		player = player_down; //player facing this pos after spawned
	}
	
	
	/**
	 *  handles player actions in real-time
	 */
	public void tick(){
		x += velX;
		y += velY;
		
		//prevent player to leave s // implement here later!
	}
	
	
	/**
	 * @param g
	 * draws player to the game
	 */
	public void render(Graphics g){
		g.drawImage(player, (int)x, (int)y, null); //null for image observer
												   //x,y need to be casted, doesn't take doubles
	}
	
	
	/**
	 * @return player X position
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * @return player Y position
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * @param x sets players x position
	 */
	public void setX(double x){
		this.x = x;
	}
	
	/**
	 * @param y sets player y position
	 */
	public void setY(double y){
		this.y = y;
	}
	
	public void setCoord(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setCoord(double x, double y, String playerPos){
		this.x = x;
		this.y = y;
		setPlayerPos(playerPos);
	}
	
	
	
	public void setVelX(double velX){
		if (velX + this.velX > this.velX){player = player_right;} //use turn right sprite
		else if(velX + this.velX == this.velX){} //keep last sprite used
		else{player = player_left;} //use left sprite
		this.velX = velX;
	} 

	
	public void setVelY(double velY){
		if (velY + this.velY > this.velY){player = player_down;} //use turn down sprite
		else if(velY + this.velY == this.velY){} //keep last sprite used
		else{player = player_up;} //use up sprite
		this.velY = velY;
	}
	
	public void moveX(double x){
		this.x += x;
	}
	public void moveY(double y){
		this.y += y;
	}
	
	/**
	 * loads all images for different positions, they are used based on what direction is facing 
	 */
	public void loadBasicPositioning(){
		int size = 96;
		player_right = player_sheet.grabImage(3, 1, 96, 96,  1);
		player_left = BufferedImageMet.flipVertically(player_right);
		player_up = player_sheet.grabImage(1, 1, 96, 96, 1);
		player_down = player_sheet.grabImage(5, 1, 96, 96, 1);
		player_sit = player_sheet.grabImage(10, 1, size, size, 1);
	}
	
	public void setPlayerName(String name)
	{
		this.playerName = name;
	}
	
	public String getName()
	{
		return playerName;
	}
	
	public int[] getCenter()
	{
		int[] cord = new int[2];
		cord[0] = (int)(getX()+getX()+size)/2;
		cord[1] = (int)(getY()+getY()+size)/2;
		return cord;
	}

	public void ticTac15x15() {
		if (player != player_sit) player = player_sit;
		else player = player_down;	
	}
	
	public void setPlayerPos(String playerPos){
	     switch (playerPos) {
         case "01":
         case "02":player = player_down; break;
         case "03":
         case "04":player = player_left; break;
         case "05":ticTac15x15(); break;
         case "06":player = player_right; break;
         case "07":
         case "08":player = player_up; break;
         case "09":
         default:
             throw new IllegalArgumentException("Invalid player position: " + playerPos);
     }
	}
}
