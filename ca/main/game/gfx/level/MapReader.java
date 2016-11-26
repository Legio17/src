package ca.main.game.gfx.level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapReader {

	private static String[][] map;

	/**
	 * @param path
	 * loads information about map construction from txt file
	 */
	public MapReader(String path) {
		try {
			readFromFile(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/**
	 * @param path
	 * @throws IOException
	 * refines infomations about map loaded into 2-dimensional array(column, row)
	 */
	public void readFromFile(String path) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(path));
		BufferedReader temp_br = new BufferedReader(new FileReader(path)); //just to know how many lines does file have
		try {
			String line = br.readLine();
			String temp_line = temp_br.readLine();
			int currentLine = 0;
			int nrOfLines = 0;
			
			while (temp_line != null){
				nrOfLines ++;
				temp_line = temp_br.readLine();
			}
			temp_br.close();
			
			map = new String[line.length()][nrOfLines];

			while (line != null) {
				
				int column = 0;
				for (char ch : line.toCharArray()) {
					map[column][currentLine] = ch+"";
					column ++;
				}
				currentLine ++;
				line = br.readLine();
			}


		} finally {
			br.close();
		}
	}
	
	/**
	 * @return 2-dimensional array of map(column, row)
	 */
	public String[][] getMap(){
		return map;
		
	}
}
