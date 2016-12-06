package ca.main.game.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ca.main.game.Game;
import ca.main.game.utilities.simpleMethods;

public class FontLoader {
	
	SpriteSheetLoader sprite_sheet_loader;
	
	private ArrayList<String[]> charFonts; 
	private ArrayList<BufferedImage[]> imgFonts; 
	
	private String[] fontLogChar; // font for login
	private String nickName;
	
	private String[] fontInfoDisChar;
	private String displayInfo;
	
	public FontLoader(Game game){
		charFonts = new ArrayList<String[]>();
		imgFonts = new ArrayList<BufferedImage[]>();
		sprite_sheet_loader = game.getSpriteSheetLoader();
		nickName = "";
		
		init();


	}
	
	public void init(){
		initializeFontCharArrays();
		loadCharToFont(fontLogChar,"fontLog", 8, 8, 87, 101, 1); //load letter images for login
		loadCharToFont(fontInfoDisChar,"fontStats", 26, 1, 13, 30, 0);
	}
	
	private void initializeFontCharArrays() {
		fontLogChar = new String[]{"!","0","1","2","3","4","5","6"
				,"7","8","9","A","B","C","D","E"
				,"F","G","H","I","J","K","L","M"
				,"N","O","P","Q","R","S","T","U"
				,"V","W","X","Y","Z","a","b","c"
				,"d","e","f","g","h","i","j","k"
				,"l","m","n","o","p","q","r","s"
				,"t","u","v","w","x","y","z"," "};
		
		fontInfoDisChar = new String[]{"a","b","c"
				,"d","e","f","g","h","i","j","k"
				,"l","m","n","o","p","q","r","s"
				,"t","u","v","w","x","y","z"};
		
	}

	public void loadCharToFont(String[] fontLog2, String pngFontName, int nrOfCol, int nrOfRow, int width, int height, int border){
		charFonts.add(fontLog2);
		BufferedImage[] tempArr = new BufferedImage[(fontLog2.length)];
		SpriteSheet fontSheet;
		fontSheet = sprite_sheet_loader.retriveFont(pngFontName);
		System.out.println(fontSheet == null);
		int row = 1;
		for (int col = 1; col <= nrOfRow; col++ ){
			BufferedImage img;
			img = fontSheet.grabImage(col, row, width, height, border);
			tempArr[((row-1)*nrOfCol)+col-1] = img;
			if (col == nrOfCol){
				col = 0;
				row ++;
				if (row > nrOfRow) break;
			}
			
		}
		imgFonts.add(tempArr);
	}
	
	public BufferedImage retriveByName(String charName, int fontNr){
		int pos = simpleMethods.retrivePositionArray(charName, charFonts.get(fontNr));
		if (pos==-1)return null;
		return imgFonts.get(fontNr)[pos];
	}
	
	public void renderNick(Graphics g, int fromFontNr, int x, int y, int diff){
		
		for (int i = 0; i < nickName.length(); i++){
			g.drawImage(retriveByName(Character.toString((char)nickName.charAt(i)), fromFontNr), (int)x, (int)y, null);
			x += diff;
		}
	}
	
	public void renderScore(Graphics g, int fromFontNr, int x, int y, int diff, String[] array){
		for (int i = 0; i < array.length-1; i++){
			System.out.println(array[i]);
		}
	}
	
	public void addToNickName(String letter){
		if (letter != null)nickName += letter;

	}
	
	public String getNickName(){
		return nickName;
	}
}
