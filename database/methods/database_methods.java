package database.methods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Legio-PC methods for updating and changing content of tables
 * @param <T>
 */
public class database_methods {
	/*
	 * public static void addEmployee(Connection connection, String[] data)
	 * throws SQLException {
	 * 
	 * String sql; String tableName = "employee"; Statement st =
	 * connection.createStatement();
	 * 
	 * sql = sqlMet.sqlCmdBuild(data, tableName);
	 * 
	 * System.out.println(sql); sqlMet.executeStatement(st, sql);
	 * 
	 * }
	 * 
	 * public static void addBooking(Connection connection, String[] data)
	 * throws SQLException {
	 * 
	 * String sql; Statement st = connection.createStatement();
	 * 
	 * sql =
	 * "insert into booking values((SELECT COUNT(DISTINCT booking_id) FROM Booking),"
	 * ; sql += sqlMet.sqlCmdBuildValuesPart(data);
	 * 
	 * System.out.println(sql); sqlMet.executeStatement(st, sql); }
	 * 
	 * public static void addToTable(Connection connection, String tableName,
	 * String[] data) throws SQLException {
	 * 
	 * String sql; Statement st = connection.createStatement();
	 * 
	 * sql = sqlMet.sqlCmdBuild(data, tableName);
	 * 
	 * System.out.println(sql); sqlMet.executeStatement(st, sql); }
	 * 
	 * public static void updateEmployeeSalary(Connection connection, int empid)
	 * throws SQLException {
	 * 
	 * String sql; Statement st = connection.createStatement();
	 * 
	 * }
	 * 
	 * public static void updateAtribute(Connection connection, String
	 * tableName, String nameOfAtribute, String oldAtribute, String newAtribute)
	 * throws SQLException {
	 * 
	 * String sql; Statement st = connection.createStatement();
	 * 
	 * sql = "UPDATE " + tableName; sql += " SET " + nameOfAtribute + "=" +
	 * sqlMet.addQ(newAtribute); sql += " WHERE " + nameOfAtribute + "=" +
	 * sqlMet.addQ(oldAtribute) + ";";
	 * 
	 * System.out.println(sql); sqlMet.executeStatement(st, sql); }
	 * 
	 * public static void updateAtribute(Connection connection, String
	 * tableName, String nameOfAtributeSeachedBy, String nameOfAtributeChanged,
	 * String oldAtribute, String newAtribute) throws SQLException {
	 * 
	 * String sql; Statement st = connection.createStatement();
	 * 
	 * sql = "UPDATE " + tableName; sql += " SET " + nameOfAtributeChanged + "="
	 * + sqlMet.addQ(newAtribute); sql += " WHERE " + nameOfAtributeSeachedBy +
	 * "=" + sqlMet.addQ(oldAtribute) + ";";
	 * 
	 * System.out.println(sql); sqlMet.executeStatement(st, sql); }
	 * 
	 * public static void dropTable(Connection connection, String tableName)
	 * throws SQLException {
	 * 
	 * String sql; Statement st = connection.createStatement();
	 * 
	 * sql = "DROP TABLE " + tableName + ";";
	 * 
	 * System.out.println(sql); sqlMet.executeStatement(st, sql); }
	 * 
	 * public static void deleteFromTable(Connection connection, String
	 * tableName, String columnName, String value) throws SQLException {
	 * 
	 * String sql; Statement st = connection.createStatement();
	 * 
	 * sql = "DELETE FROM " + tableName; sql += " WHERE " +
	 * sqlMet.addQ(columnName) + "=" + sqlMet.addQ(value) + ";";
	 * 
	 * System.out.println(sql); sqlMet.executeStatement(st, sql); }
	 * 
	 * public static void updateBookingPrice(Connection connection, int
	 * bookingID) throws SQLException {
	 * 
	 * String houseName = getHouseNameByID(connection, bookingID); int nrOfDays
	 * = getNumberOfDaysForBookingByID(connection, bookingID); double
	 * priceperday = getPriceByHouseName(connection, houseName); double
	 * finalPrice = priceperday * nrOfDays; finalPrice =
	 * sqlMet.round(finalPrice, 2);
	 * 
	 * database_methods.updateAtribute(connection, "booking", "booking_id",
	 * "amount_to_pay", bookingID + "", finalPrice + "");
	 * 
	 * }
	 * 
	 * public static int getNumberOfDaysForBookingByID(Connection connection,
	 * int bookingID) throws SQLException { String sqlBooking; Statement
	 * stBooking = null; int nrOfDaysInt = 0;
	 * 
	 * sqlBooking = "SELECT booking_id, rented_days FROM booking"; stBooking =
	 * connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * ResultSet.CONCUR_UPDATABLE); ResultSet rsBooking =
	 * stBooking.executeQuery(sqlBooking);
	 * 
	 * while (rsBooking.next()) { String bookingIDstr = "" + bookingID; if
	 * (bookingIDstr.equals(rsBooking.getString("booking_id"))) { String
	 * nrOfDays = rsBooking.getString("rented_days"); nrOfDaysInt =
	 * Integer.parseInt(nrOfDays); break; } }
	 * 
	 * return nrOfDaysInt; }
	 */
	public static String getInfoByName(Connection connection, String name)
			throws SQLException {
		String sqlGameHistory;
		Statement stGameHistory = null;
		String info = "";

		sqlGameHistory = "SELECT player1, player2, date, type, result_player1 FROM GAME_HISTORY";

		stGameHistory = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rsGameHistory = stGameHistory.executeQuery(sqlGameHistory);
		
		String sqlScoreInfo;
		Statement stScoreInfo = null;
		String info2 = "";

		sqlScoreInfo = "SELECT login, rank, winloss_ratio, wins, losses FROM SCORE_INFO";

		stScoreInfo = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rsScoreInfo = stScoreInfo.executeQuery(sqlScoreInfo);

		while (rsGameHistory.next()) {
			if (name.equalsIgnoreCase(rsGameHistory.getString("player1"))) {
				info = rsGameHistory.getString("player1") + ", "
						+ rsGameHistory.getString("player2") + ", "
						+ rsGameHistory.getString("date") + ", "
						+ rsGameHistory.getString("type") + ", "
						+ rsGameHistory.getString("result_player1");
				break;
			}
		}
		
		while (rsScoreInfo.next()) {
			if (name.equalsIgnoreCase(rsScoreInfo.getString("login"))) {
				info2 = rsScoreInfo.getString("rank") + ", "
						+ rsScoreInfo.getString("winloss_ratio") + ", "
						+ rsScoreInfo.getString("wins") + ", "
						+ rsScoreInfo.getString("losses");
				break;
			}
		}
		return info+", "+info2;
	}
	/*
	 * public static double getPriceByHouseName(Connection con, String
	 * houseName) throws SQLException { String sqlPrice; Statement stPrice =
	 * null; double price = 0;
	 * 
	 * sqlPrice = "SELECT housename, priceperday FROM house"; stPrice =
	 * con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * ResultSet.CONCUR_UPDATABLE); ResultSet rsPrice =
	 * stPrice.executeQuery(sqlPrice);
	 * 
	 * while (rsPrice.next()) {
	 * 
	 * if (houseName.equals(rsPrice.getString("housename"))) { houseName =
	 * rsPrice.getString("housename"); price =
	 * Double.parseDouble(rsPrice.getString("priceperday")); break; } }
	 * 
	 * return price;
	 * 
	 * }
	 * 
	 * public static ArrayList<String> getAllBookingID(Connection connection)
	 * throws SQLException { String sqlBookingIDs; Statement stBooking = null;
	 * 
	 * sqlBookingIDs = "SELECT booking_id FROM booking"; stBooking =
	 * connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * ResultSet.CONCUR_UPDATABLE); ResultSet rsBooking =
	 * stBooking.executeQuery(sqlBookingIDs); ArrayList<String> list = new
	 * ArrayList<>();
	 * 
	 * while (rsBooking.next()) { list.add(rsBooking.getString("booking_id")); }
	 * 
	 * return list; }
	 * 
	 * public static void updateAllBookingPrices(Connection con) throws
	 * SQLException { ArrayList<String> list = new ArrayList<>(); list =
	 * getAllBookingID(con);
	 * 
	 * for (int i = 0; i < list.size(); i++) { updateBookingPrice(con,
	 * Integer.parseInt(list.get(i))); } }
	 */
}
