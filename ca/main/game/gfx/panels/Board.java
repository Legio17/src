package ca.main.game.gfx.panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import ca.main.game.Game;
import ca.main.game.gfx.BufferImageLoader;
import ca.main.game.gfx.Sprite;


public class Board {
	
	private Sprite solidSprite = null;
	private Sprite writableSprite = null;
	private Sprite gridSprite = null;
	private BufferImageLoader loader;
	
	private BufferedImage solidBackground;
	private BufferedImage writableBackground;
	private BufferedImage gridBackground;
	
	private int gameWidth;
	private int gameHeight;
	private int width;
	private int height;
	private String name;
	
	public Board(int gameWidth, int gameHeight, String name, int width, int height, String solidBgPATH){
		loader = new BufferImageLoader();
		

		solidSprite = loadImage(solidBgPATH);
		solidBackground = solidSprite.grabImage(0, 0, width, height);
		
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.width = width;
		this.height = height;
		this.name =  name;
	}
	
	public Board(int gameWidth, int gameHeight, String name, int width, int height, String solidBgPATH, String writableBgPATH){
		loader = new BufferImageLoader();
		

		solidSprite = loadImage(solidBgPATH);
		solidBackground = solidSprite.grabImage(0, 0, width, height);
		
		writableSprite = loadImage(writableBgPATH);
		writableBackground = writableSprite.grabImage(0, 0, width, height);
		
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.width = width;
		this.height = height;
		this.name =  name;
	}
	
	public Board(int gameWidth, int gameHeight, String name, int width, int height, String solidBgPATH, String writableBgPATH, String gridBgPATH){
		loader = new BufferImageLoader();
		

		solidSprite = loadImage(solidBgPATH);
		solidBackground = solidSprite.grabImage(0, 0, width, height);
		
		writableSprite = loadImage(writableBgPATH);
		writableBackground = writableSprite.grabImage(0, 0, width, height);
		
		gridSprite = loadImage(gridBgPATH);
		gridBackground = gridSprite.grabImage(0, 0, width, height);
		
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.width = width;
		this.height = height;
		this.name =  name;
	}
	


	public Sprite loadImage(String path){
		
		Sprite sprite = null;
		try {
			sprite = new Sprite(loader.loadImage(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(solidBackground, (gameWidth-width)/2, (gameHeight-height)/2 , null); 
		g.drawImage(writableBackground, (gameWidth-width)/2, (gameHeight-height)/2 , null);
		g.drawImage(gridBackground, (gameWidth-width)/2, (gameHeight-height)/2 , null); 	
	}
	
	public String getBoardName(){
		return name;
	}
	
	

}