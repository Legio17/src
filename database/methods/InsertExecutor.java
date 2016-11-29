package database.methods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertExecutor {

	
	private Connection connection;
	
	public InsertExecutor(Connection connection){
		this.connection = connection;	
	}
	
	public void insertToGameHistory(String player1, String player2, String type,String date, String result) throws SQLException{
		String sql;
		String sqlUpdate;
		String sqlRetrivePlayers;
		Statement st = connection.createStatement();
		Statement stRetrive = connection.createStatement();
		//INSERT INTO TABLE_NAME VALUES (value1,value2,value3,...valueN);
		
		
		sql = "INSERT INTO Game_History VALUES(";
        sql += "(SELECT COUNT(*) FROM Game_History)"+",'"+player1 +"','" + player2 + "','" + date + "','" + type + "','" + result +"');";
        
		if (result.equals("won")){
			sqlRetrivePlayers = "SELECT login, wins, losses FROM score_info";
			stRetrive = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rsRetrive = stRetrive.executeQuery(sqlRetrivePlayers);
			
	        while(rsRetrive.next()){
	        	if(rsRetrive.getString("login").equalsIgnoreCase("player1")){
	        		int wins=Integer.parseInt(rsRetrive.getString("wins"))+1;
	        		int losses= Integer.parseInt(rsRetrive.getString("losses"));
	        		double ratio= (double) wins/losses;
	        		
	        	}
	        }
		}
		sqlMet.executeStatement(st, sql);
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void insertPlayer(String login) throws SQLException{
		String sqlRetrivePlayers, model;
		Statement stWriteTo = connection.createStatement(), stRetrive = connection.createStatement();
		
		model = "applejack";
		boolean exists = false;
		

		sqlRetrivePlayers = "SELECT login FROM player_info";
		stRetrive = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rsRetrive = stRetrive.executeQuery(sqlRetrivePlayers);
		
        while(rsRetrive.next()){

        	if (rsRetrive.getString("login").equals(login)){
        		
        		return;
        	}
        }
        String sqlAddLogin;
		sqlAddLogin = "INSERT INTO PLAYER_INFO VALUES('"+login+"','"+model+"');";
		
		sqlMet.executeStatement(stWriteTo, sqlAddLogin);
	}
}
