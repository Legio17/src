package ca.main.game.gfx.level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ca.main.game.Game;
import ca.main.game.gfx.SpriteSheet;
import ca.main.game.gfx.SpriteSheetLoader;
import ca.main.game.utilities.simpleMethods;

public class Map {
	
	SpriteSheetLoader sprite_sheet_loader;
	SpriteSheet tile_sheet;
	
	private ArrayList<BufferedImage> tileList;
	private ArrayList<String> tileListNames;
	
	private TileReader tr;
	private MapReader mr;
	private static String[][] map;
	
	public Map(Game game, String mapTxtPath){
		
		mr = new MapReader(mapTxtPath);
		map = mr.getMap();
		
		tr = new TileReader(game);
		tileList = tr.getImageList();
		tileListNames = tr.getImageListNames();
		
		System.out.println(map.length);
		System.out.println(map[0].length);
	}
	
	public void render(Graphics g, int tileSize, int tileBorder){
		for (int column = 0; column < map.length; column++)
		{
			for (int row = 0; row < map[0].length; row ++){
				
				g.drawImage(drawTile(column, row), (tileSize*column), (tileSize*row), null); 
			}
		}
	}
	
	public BufferedImage drawTile(int column, int row){
		
		String tile_type = map[column][row];
		
		if(tile_type.equalsIgnoreCase("0")){
			return simpleMethods.retriveFromListByName("grass", tileListNames, tileList);
		} else if(tile_type.equals("1")) {
			return simpleMethods.retriveFromListByName("rock", tileListNames, tileList);
		} else{
			return simpleMethods.retriveFromListByName("voidT", tileListNames, tileList);
		}
		
	}
	
}
