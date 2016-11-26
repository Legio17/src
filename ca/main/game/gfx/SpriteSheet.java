package ca.main.game.gfx;

import java.awt.image.BufferedImage;


/**
 * @author Legio-PC
 * Sprite Sheet is grid Image that server to store more smaller images
 * these can be retrieved by call on theyrs position inside grid 
 */
public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	
	/**
	 * @param col - col position inside spriteSheet grid
	 * @param row - row position inside spriteSheet grid
	 * @param width - width of actual image
	 * @param height - height of actual image
	 * @param pixelSize - I don't even know why I'm using this one, maybe for uneven images
	 * @param borderPixels - thickens of border around image, default 1
	 * @return
	 * retrieves BufferedImage from sprite sheet
	 */
	public BufferedImage grabImage(int col, int row, int width, int height, int borderPixels){
		
		BufferedImage img = image.getSubimage((col * width)+borderPixels - width, //+1 to ignore frame
											  (row * height)+borderPixels - height, 
											   width-2*borderPixels, height-2*borderPixels);
		return img;
	}
}
