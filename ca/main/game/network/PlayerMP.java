package ca.main.game.network;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ca.main.game.Game;
import ca.main.game.gfx.BufferedImageMet;
import ca.main.game.gfx.Player;
import ca.main.game.gfx.SpriteSheet;
import ca.main.game.gfx.SpriteSheetLoader;


public class PlayerMP{

	private String ipAddress;
	private double x, y;
	private double velX = 0; //both velocities are purely for increasing smoothness of movement
	private double velY = 0; //they are not necessary, but movement looks nicer
	private String playerName;
	private int size;
	
	SpriteSheetLoader sprite_sheet_loader;
	SpriteSheet player_sheet;
	
	private BufferedImage player;
	
	/**
	 * @param x
	 * @param y
	 * @param game
	 * @param model_nr
	 * creates and assign player to world
	 */
	public PlayerMP(Game game, String ipAddress) {
		sprite_sheet_loader = game.getSpriteSheetLoader();
		player_sheet = sprite_sheet_loader.retrivePlayerModel("applejack");
		this.ipAddress = ipAddress;
		x = 100;
		y = 100;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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
	
	/*
	
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
	*/
	public void moveX(double x){
		this.x += x;
	}
	public void moveY(double y){
		this.y += y;
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
	
	/**
	 *  handles player actions in real-time
	 */
	public void tick(){
		x += velX;
		y += velY;
	}
	
/*
	
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
	*/
	
}
