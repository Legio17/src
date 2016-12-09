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
	
	public void updateScoreInfo(String player1, String player2, String result) throws SQLException{
		String sql;
		String sqlUpdate="";
		String sqlRetrivePlayers;
		Statement st = connection.createStatement();
		Statement stRetrive = connection.createStatement();
        
        // in case player1 wins the game
		if (result.equals("won")){
			sqlRetrivePlayers = "SELECT login, wins, losses FROM score_info";
			stRetrive = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rsRetrive = stRetrive.executeQuery(sqlRetrivePlayers);
			
	        while(rsRetrive.next()){
	        	if(rsRetrive.getString("login").equalsIgnoreCase(player1)){
	        		int wins=Integer.parseInt(rsRetrive.getString("wins"))+1;
	        		int losses= Integer.parseInt(rsRetrive.getString("losses"));
	        		double ratio;
	        		if(wins==0){
	        			ratio=0;
	        		}
	        		else if(losses==0)
	        		{
	        			ratio = wins;
	        		}
	        		else {ratio= (double) wins/losses;}
	        		sqlUpdate="UPDATE Score_info SET wins="+wins+" WHERE  login='"+player1+"'";
	        		sqlMet.executeStatement(st, sqlUpdate);
	        		sqlUpdate="UPDATE Score_info SET winloss_ratio="+ratio+" WHERE  login='"+player1+"'";
	        		sqlMet.executeStatement(st, sqlUpdate);
	        	}
	        	else if(rsRetrive.getString("login").equalsIgnoreCase(player2)){
	        		int wins=Integer.parseInt(rsRetrive.getString("wins"));
	        		int losses= Integer.parseInt(rsRetrive.getString("losses"))+1;
	        		double ratio;
	        		if(wins==0){
	        			ratio=0;
	        		}
	        		else if(losses==0)
	        		{
	        			ratio = wins;
	        		}
	        		else {ratio= (double) wins/losses;}
	        		sqlUpdate="UPDATE Score_info SET losses="+losses+" WHERE  login='"+player2+"'";
	        		sqlMet.executeStatement(st, sqlUpdate);
	        		sqlUpdate="UPDATE Score_info SET winloss_ratio="+ratio+" WHERE  login='"+player2+"'";
	        		sqlMet.executeStatement(st, sqlUpdate);
	        	}
	        }
		}
		
		// in case player2 wins
		else if (result.equals("lost")){
			sqlRetrivePlayers = "SELECT login, wins, losses FROM score_info";
			stRetrive = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rsRetrive = stRetrive.executeQuery(sqlRetrivePlayers);
			
	        while(rsRetrive.next()){
	        	if(rsRetrive.getString("login").equalsIgnoreCase(player1)){
	        		System.out.println("here");
	        		int wins=Integer.parseInt(rsRetrive.getString("wins"));
	        		int losses= Integer.parseInt(rsRetrive.getString("losses"))+1;
	        		double ratio;
	        		if(wins==0){
	        			ratio=0;
	        		}
	        		else if(losses==0)
	        		{
	        			ratio = wins;
	        		}
	        		else {ratio= (double) wins/losses;}
	        		sqlUpdate="UPDATE Score_info SET losses="+losses+" WHERE  login='"+player1+"'";
	        		sqlMet.executeStatement(st, sqlUpdate);
	        		sqlUpdate="UPDATE Score_info SET winloss_ratio="+ratio+" WHERE  login='"+player1+"'";
	        		sqlMet.executeStatement(st, sqlUpdate);
	        	}
	        	else if(rsRetrive.getString("login").equalsIgnoreCase(player2)){
	        		int wins=Integer.parseInt(rsRetrive.getString("wins"))+1;
	        		int losses= Integer.parseInt(rsRetrive.getString("losses"));
	        		double ratio;
	        		if(wins==0){
	        			ratio=0;
	        		}
	        		else if(losses==0)
	        		{
	        			ratio = wins;
	        		}
	        		else {ratio= (double) wins/losses;}
	        		sqlUpdate="UPDATE Score_info SET wins="+wins+" WHERE  login='"+player2+"'";
	        		sqlMet.executeStatement(st, sqlUpdate);
	        		sqlUpdate="UPDATE Score_info SET winloss_ratio="+ratio+" WHERE  login='"+player2+"'";
	        		sqlMet.executeStatement(st, sqlUpdate);
	        	}
	        }
		}
	} 

	public void insertPlayer(String login) throws SQLException{
		String sqlRetrivePlayers, sqlCreateScoreInfo, model;
		Statement stWriteTo = connection.createStatement(), stRetrive = connection.createStatement();
		
		boolean exists = false;
		

		sqlRetrivePlayers = "SELECT login FROM player_info";
		stRetrive = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rsRetrive = stRetrive.executeQuery(sqlRetrivePlayers);
		
        while(rsRetrive.next()){

        	if (rsRetrive.getString("login").equalsIgnoreCase(login)){
        		System.out.println("already exists");
        		return;
        	}
        }
        String sqlAddLogin;
		sqlAddLogin = "INSERT INTO PLAYER_INFO VALUES('"+login+"');";
		sqlCreateScoreInfo = "INSERT INTO SCORE_INFO VALUES('"+login+"','0','0','0','0');";
		
		sqlMet.executeStatement(stWriteTo, sqlAddLogin);
		sqlMet.executeStatement(stWriteTo, sqlCreateScoreInfo);
	}
	
	public void updateRanks() throws SQLException{
		Statement st = connection.createStatement();
		Statement stRetrive = connection.createStatement();
		String sqlRetrivePlayers;
		String sqlUpdatePlayerRank;
		
		sqlRetrivePlayers = "SELECT rank, winloss_ratio, login FROM score_info ORDER BY winloss_ratio DESC;";
		stRetrive = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rsRetrive = stRetrive.executeQuery(sqlRetrivePlayers);
		
		int rank =1;
		while(rsRetrive.next()){
			sqlUpdatePlayerRank="UPDATE Score_info SET rank="+rank+" WHERE login='"+rsRetrive.getString("login")+"'";;
			rank++;
    		sqlMet.executeStatement(st, sqlUpdatePlayerRank);
        }		
	}
}
