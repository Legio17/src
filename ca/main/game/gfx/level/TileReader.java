package ca.main.game.gfx.level;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ca.main.game.Game;
import ca.main.game.gfx.SpriteSheet;
import ca.main.game.gfx.SpriteSheetLoader;

public class TileReader {
	
	private ArrayList<BufferedImage> tileList;
	private ArrayList<String> tileListNames;
	
	private SpriteSheetLoader sprite_sheet_loader;
	private SpriteSheet terrainSheet;
	
	
	public TileReader(Game game){

		sprite_sheet_loader = game.getSpriteSheetLoader();//fetch SpriteSheet from Game class
		terrainSheet = sprite_sheet_loader.retriveTerrainTile("terrain");
		tileList = new ArrayList<BufferedImage>();
		tileListNames = new ArrayList<String>();
		loadToList();
	}
	
	public void loadImageToList(String imageName, SpriteSheet terrainSheet, int column, int row, int size, int border){
		BufferedImage image = null;
		image = terrainSheet.grabImage(column, row, size, size, border);
		tileList.add(image);
		tileListNames.add(imageName);
	}
	
	public void loadToList()
	{
		loadImageToList("grass", terrainSheet, 2, 1, 96, 1);
		loadImageToList("rock", terrainSheet, 1, 1, 96, 1);
		loadImageToList("voidT", terrainSheet, 8, 8, 96, 1);
	}
	
	public ArrayList<BufferedImage> getImageList(){
		return tileList;
	}
	
	public ArrayList<String> getImageListNames(){
		return tileListNames;
	}
	
}
