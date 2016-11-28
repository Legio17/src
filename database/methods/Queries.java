package database.methods;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {
private Connection connection;
	
	public Queries(Connection connection){
		this.connection = connection;	
	}
	
	public void queryOrderBy(String attribute1,String attribute2, String tableName, String value1) throws SQLException{
		
		String sql;
		Statement st = connection.createStatement();
		
		sql = "SELECT" + attribute1 + "," + attribute2 + " FROM " + tableName + "ORDER BY" + value1;
		
		System.out.println(sql);
		
		sqlMet.executeStatement(st, sql);
	}

}
