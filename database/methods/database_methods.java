package database.methods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Legio-PC methods for updating and changing content of tables
 * @param <T>
 */
public class database_methods {
	
	public static String getInfoByName(Connection connection, String name)
			throws SQLException {
		
		String sqlScoreInfo;
		Statement stScoreInfo = null;
		String info = "";

		sqlScoreInfo = "SELECT login, rank, winloss_ratio, wins, losses FROM SCORE_INFO";

		stScoreInfo = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rsScoreInfo = stScoreInfo.executeQuery(sqlScoreInfo);
		
		while (rsScoreInfo.next()) {
			if (name.equalsIgnoreCase(rsScoreInfo.getString("login"))) {
				info = rsScoreInfo.getString("rank") + ", "
						+ sqlMet.cutWinLossRatio(rsScoreInfo.getString("winloss_ratio"))+ ", "
						+ rsScoreInfo.getString("wins") + ", "
						+ rsScoreInfo.getString("losses");
				break;
			}
		}
		return info;
	}
	
	public static String getTop5(Connection connection)
			throws SQLException {		
		String sqlScoreInfo;
		Statement stScoreInfo = null;
		String info2 = "";
		int i=0;
		
		sqlScoreInfo = "SELECT login FROM SCORE_INFO";

		stScoreInfo = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rsScoreInfo = stScoreInfo.executeQuery(sqlScoreInfo);

			while (rsScoreInfo.next())
			{
				info2 += rsScoreInfo.getString("login") + ", ";
				i++;
				if(i==5)
				break;
			}
		
		return info2;
	}
}
