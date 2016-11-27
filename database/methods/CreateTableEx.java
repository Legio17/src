package database.methods;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableEx {
	
	private Connection connection;
	
	public CreateTableEx(Connection connection){
		this.connection = connection;	
	}
	
	public void createTable(String tableName ,String[][] nameAndAtr, String[] acces, boolean delCas, boolean updtCas ) throws SQLException{
		
		String sql = "CREATE TABLE "+tableName+"(";
		Statement st = connection.createStatement();
		
		for (int row1 = 0; row1 < nameAndAtr.length; row1++){
			
			sql += nameAndAtr[row1][0] + " " + nameAndAtr[row1][1] + ",";
			
		}
		
		if (acces != null){
			
			String key = "";
			for (int row2 = 0; row2 < acces.length; row2++){
				key += buildKey(acces[row2]);
				
				if (row2 != acces.length - 1){
					key += ","; 
				}

			}
			sql += key;
		}
		
		if (delCas)sql+=" ON DELETE CASCADE";
		if (updtCas)sql+=" ON UPDATE CASCADE";
		sql += ");";
		System.out.println(sql);
		
		sqlMet.executeStatement(st, sql);
	}
	
	public String buildKey(String key){
		String[] parts = key.split(":");
		String refinedKey = "";
		

		if(parts[0].equals("pk")){
			refinedKey += "PRIMARY KEY "+parts[1]; 
		} 
		else if(parts[0].equals("fk")){
			refinedKey += "FOREIGN KEY "+parts[1]; 
		} 
		else if(parts[0].equals("uq")){
			refinedKey += "UNIQUE "+parts[1]; 
		} 
			
		if (parts.length == 4){
			refinedKey += " REFERENCES " + parts[3];
		}
		
		return refinedKey;
		
	}
}	
