package client.control;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Game;
import client.gfx.BufferImageLoader;

public class GameObjectTypeB extends GameObject{

	private String owner;
	private BufferImageLoader imageLoader;
	
	private BufferedImage zone;
	
	public GameObjectTypeB(Game game,int centerPosX, int centerPosY, int width, int height, String owner) {
		super(centerPosX, centerPosY, width, height);
		this.owner = owner;
		
		imageLoader = game.getImageLoader();
		
		try {
			zone = imageLoader.loadImage("/img/boards/tic-tac-toe/seachZone.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void renderZone(Graphics g){
		g.drawImage(zone, getCor1PosX(), getCor1PosY(), getWidth(), getHeight(), null);
	}
	
	
	
}


