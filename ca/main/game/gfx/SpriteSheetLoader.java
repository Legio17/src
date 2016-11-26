package ca.main.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Legio-PC
 *	upon start of game SpriteSheetLoader loads all models into sprite sheet arrays
 *	arrays are divided into categories based on what type of sheet they hold
 *	sheets can by retrieved by call on arrayList and specification of model either,
 *	by name or number of model
 */
public class SpriteSheetLoader{
	
	private SpriteSheet spriteSheet = null;
	private BufferImageLoader loader;
	
	private ArrayList<SpriteSheet> player_model_list = new ArrayList<SpriteSheet>();   
	private ArrayList<String> player_model_listNames = new ArrayList<String>();
	
	private ArrayList<SpriteSheet> tile_list = new ArrayList<SpriteSheet>();   
	private ArrayList<String> tile_listNames = new ArrayList<String>();
	
	private ArrayList<SpriteSheet> font_list = new ArrayList<SpriteSheet>();
	private ArrayList<String> font_listNames = new ArrayList<String>();
	
	public SpriteSheetLoader(){
		loader = new BufferImageLoader();
		
		//===================== player models ==================
		loadPlayerModel("/sheets/player_models/", "applejack");
		
		//===================== maps ===========================
		loadTile("/sheets/tiles/", "terrain");
		
		//===================== load fonts =====================
		loadFont("/sheets/fonts/", "fontLog");
	}
	
	
	/**
	 * @param folder_path
	 * @param player_model_name
	 * loads player model sheet into player model sheet array
	 */
	public void loadPlayerModel(String folder_path,String player_model_name){
		
		String path = folder_path + player_model_name + ".png";
		
		try {
			spriteSheet = new SpriteSheet(loader.loadImage(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player_model_list.add(spriteSheet);
		player_model_listNames.add(player_model_name);
		
	}
	
	
	/**
	 * @param folder_path
	 * @param png_name
	 * loads tile model sheet into tile model sheet array
	 */
	public void loadTile(String folder_path,String png_name){
		
		String path = folder_path + png_name + ".png";
		
		try {
			spriteSheet = new SpriteSheet(loader.loadImage(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tile_list.add(spriteSheet);
		tile_listNames.add(png_name);
		
	}
	
	public void loadFont(String folder_path,String png_name){
		
		String path = folder_path + png_name + ".png";
		
		try {
			spriteSheet = new SpriteSheet(loader.loadImage(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		font_list.add(spriteSheet);
		font_listNames.add(png_name);
		
	}
	
	/**
	 * @param number
	 * @return 
	 * Retrieves player sheet by number in player_model_list
	 */
	public SpriteSheet retrivePlayerModel(int number){
		return player_model_list.get(number);
	}
	
	/**
	 * @param modelName
	 * @return
	 * Retrieves player sheet by name of sheet stored in player_model_listNames
	 * Name is same as actual name of png file being loaded from folder
	 */
	public SpriteSheet retrivePlayerModel(String modelName){
		int pos = 0;
		
		for (String s: player_model_listNames) {  
			if (s.equals(modelName)) return player_model_list.get(pos);
			pos ++;
		}
		System.out.println("player model does not exist!");
		return null;
	}
	
	/**
	 * @param number
	 * @return 
	 * Retrieves tile sheet by number in tile_list
	 */
	public SpriteSheet retriveTerrainTile(int number){
		return tile_list.get(number);
	}
	
	
	/**
	 * @param modelName
	 * @return
	 * Retrieves tile sheet by name of sheet stored in tile_listNames
	 * Name is same as actual name of png file being loaded from folder
	 */
	public SpriteSheet retriveTerrainTile(String modelName){
		int pos = 0;
		
		for (String s: tile_listNames) {  
			if (s.equals(modelName)) return tile_list.get(pos);
			pos ++;
		}
		System.out.println("terrain model does not exist!");
		return null;
	}
	
	public SpriteSheet retriveFont(String modelName){
		int pos = 0;
		
		for (String s: font_listNames) {  
			if (s.equals(modelName)) return font_list.get(pos);
			pos ++;
		}
		System.out.println("font does not exist!");
		return null;
	}
}
