package database.methods;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

public class sqlMet {

	public static String sqlCmdBuild(String[] data, String tableName) {
		String sql;
		sql = "insert into " + tableName;
		sql += sqlCmdBuildValuesFull(data);
		return sql;

	}

	public static String sqlCmdBuildValuesFull(String[] data) {
		String sql = " values(";
		sql += loadToString(data);
		return sql;
	}

	public static String sqlCmdBuildValuesPart(String[] data) {
		String sql = "";
		sql += loadToString(data);
		return sql;
	}

	public static String loadToString(String[] data) {
		int data_lenght = data.length;
		int inputNumber = 0;
		String sql = "";

		for (String s : data) {
			if (inputNumber == data_lenght - 1) {
				sql += (sqlMet.addQ(s)) + ");";
			} else {
				sql += sqlMet.addQ(s) + ",";
			}
			inputNumber++;
		}
		return sql;
	}

	public static String addQ(String str) {
		return "'" + str + "'";
	}

	public static void executeStatement(Statement st, String sql) {
		try {
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static String cutWinLossRatio(String ratio){
		if(ratio.length() > 4){
			return ratio.substring(0, 4);
		}
		return ratio;
	}
	
}
