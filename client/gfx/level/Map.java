package client.gfx.level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import client.Game;
import client.gfx.SpriteSheet;
import client.gfx.SpriteSheetLoader;
import client.network.utilities.SimpleMethods;

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
		
	}
	
	public void render(Graphics g, int tileSize, int tileBorder, int offSetX, int offSetY){
		for (int column = 0; column < map.length; column++)
		{
			for (int row = 0; row < map[0].length; row ++){
				
				g.drawImage(drawTile(column, row), (tileSize*column)+offSetX, (tileSize*row)+offSetY, null); 
			}
		}
	}
	
	public BufferedImage drawTile(int column, int row){
		
		String tile_type = map[column][row];
		
		if(tile_type.equalsIgnoreCase("0")){
			return SimpleMethods.retriveFromListByName("grass", tileListNames, tileList);
		} else if(tile_type.equals("1")) {
			return SimpleMethods.retriveFromListByName("rock", tileListNames, tileList);
		} else{
			return SimpleMethods.retriveFromListByName("voidT", tileListNames, tileList);
		}
		
	}
	
}
