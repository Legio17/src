package database.methods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Adam Szekely, Ana-Iulia Chifor, Filip Hudec, Peter Miodrag Varanic, Signe Rasmussen
 *
 */
public class InsertExecutor {

	
	private Connection connection;
	
	public InsertExecutor(Connection connection){
		this.connection = connection;	
	}
	
	/**
	 * A method that adds information about a finished game to the Game_History table, and then it will update the Score_Info table
	 * with the new wins/losses values, win/loss ratio and rank for each player
	 * @param player1 -adds the name of the 1st player to the Game_History table
	 * @param player2 -adds the name of the 2nd player to the Game_History table
	 * @param type -adds the type of the board (3X3 or 5X5) to the Game_History table
	 * @param date -adds the date when the game was played to the Game_History table
	 * @param result -adds the result of the 1st player (won/lost/tie) to the Game_History table
	 * @throws SQLException
	 */
	
	public void insertToGameHistory(String player1, String player2, String type,String date, String result) throws SQLException{
		String sql;
		String sqlUpdate="";
		String sqlRetrivePlayers;
		Statement st = connection.createStatement();
		Statement stRetrive = connection.createStatement();
		//INSERT INTO TABLE_NAME VALUES (value1,value2,value3,...valueN);
		
		
		sql = "INSERT INTO Game_History VALUES(";
        sql += "(SELECT COUNT(*) FROM Game_History)"+",'"+player1 +"','" + player2 + "','" + date + "','" + type + "','" + result +"');";
        
        // in case player1 wins the game
		if (result.equals("won")){
			sqlRetrivePlayers = "SELECT login, wins, losses FROM score_info";
			stRetrive = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rsRetrive = stRetrive.executeQuery(sqlRetrivePlayers);
			
	        while(rsRetrive.next()){
	        	if(rsRetrive.getString("login").equalsIgnoreCase("player1")){
	        		int wins=Integer.parseInt(rsRetrive.getString("wins"))+1;
	        		int losses= Integer.parseInt(rsRetrive.getString("losses"));
	        		double ratio= (double) wins/losses;
	        		sqlUpdate="UPDATE Score_info SET wins="+wins+" WHERE  login='"+player1+"'";
	        		sqlUpdate="UPDATE Score_info SET winloss_ratio="+ratio+" WHERE  login='"+player1+"'";
	        	}
	        	else if(rsRetrive.getString("login").equalsIgnoreCase("player2")){
	        		int wins=Integer.parseInt(rsRetrive.getString("wins"));
	        		int losses= Integer.parseInt(rsRetrive.getString("losses"))+1;
	        		double ratio= (double) wins/losses;
	        		sqlUpdate="UPDATE Score_info SET wins="+losses+" WHERE  login='"+player2+"'";
	        		sqlUpdate="UPDATE Score_info SET winloss_ratio="+ratio+" WHERE  login='"+player2+"'";
	        	}
	        }
	        sqlMet.executeStatement(st, sqlUpdate);
		}
		
		// in case player2 wins
		else if (result.equals("lost")){
			sqlRetrivePlayers = "SELECT login, wins, losses FROM score_info";
			stRetrive = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rsRetrive = stRetrive.executeQuery(sqlRetrivePlayers);
			
	        while(rsRetrive.next()){
	        	if(rsRetrive.getString("login").equalsIgnoreCase("player1")){
	        		int wins=Integer.parseInt(rsRetrive.getString("wins"));
	        		int losses= Integer.parseInt(rsRetrive.getString("losses")+1);
	        		double ratio= (double) wins/losses;
	        		sqlUpdate="UPDATE Score_info SET wins="+losses+" WHERE  login='"+player1+"'";
	        		sqlUpdate="UPDATE Score_info SET winloss_ratio="+ratio+" WHERE  login='"+player1+"'";
	        	}
	        	else if(rsRetrive.getString("login").equalsIgnoreCase("player2")){
	        		int wins=Integer.parseInt(rsRetrive.getString("wins")+1);
	        		int losses= Integer.parseInt(rsRetrive.getString("losses"));
	        		double ratio= (double) wins/losses;
	        		sqlUpdate="UPDATE Score_info SET wins="+wins+" WHERE  login='"+player2+"'";
	        		sqlUpdate="UPDATE Score_info SET winloss_ratio="+ratio+" WHERE  login='"+player2+"'";
	        	}
	        }
	        sqlMet.executeStatement(st, sqlUpdate);
		}
		
		
		sqlMet.executeStatement(st, sql);
	} 

	public void insertPlayer(String login) throws SQLException{
		String sqlRetrivePlayers, sqlCreateScoreInfo, model;
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
		sqlCreateScoreInfo = "INSERT INTO SCORE_INFO VALUES((SELECT COUNT(*) FROM SCORE_INFO),'"+login+"','0','0','0','0');";
		
		sqlMet.executeStatement(stWriteTo, sqlAddLogin);
		sqlMet.executeStatement(stWriteTo, sqlCreateScoreInfo);
	}
}
