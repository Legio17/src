package ca.main.game.gfx;

import java.awt.image.BufferedImage;

public class Sprite {

	private BufferedImage image;

	public Sprite(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage grabImage(int fromX, int fromY, int width, int height){
		
		BufferedImage img = image.getSubimage(fromX, fromY, fromX + width, fromY + height);
		return img;
	}
}