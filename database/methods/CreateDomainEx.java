package database.methods;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDomainEx {
	
	private Connection connection;
	
	public CreateDomainEx(Connection connection){
		this.connection = connection;	
	}
	
	public void addNewDomain(String name, String type, String defaultValue, String[] check) throws SQLException{
		
		String sql, chooice;
		Statement st = connection.createStatement();
		
		sql = "CREATE DOMAIN " + name + " AS " + type;
		
		if (defaultValue != null){
			sql += " DEFAULT " + sqlMet.addQ(defaultValue);
		}
		
		sql += checkChooice(check);
		System.out.println(sql);
		
		sqlMet.executeStatement(st, sql);
	}
	
	public void addNewDomain(String name, String type, int expectedDigits) throws SQLException{
		
		String sql, chooice;
		Statement st = connection.createStatement();
		
		sql = "CREATE DOMAIN " + name + " AS " + type;
		
		
		sql += digitsExpect(expectedDigits);
		System.out.println(sql);
		
		sqlMet.executeStatement(st, sql);
	}
	
	public String checkChooice(String[] data){
		int data_lenght = data.length;
		int inputNumber = 0;
		String sql = " CHECK(VALUE IN(";
		
		for (String s : data) {
			if (inputNumber == data_lenght - 1) {
				sql += (sqlMet.addQ(s)) + "));";
			} else {
				sql += sqlMet.addQ(s) + ",";
			}
			inputNumber++;
		}
		return sql;
	}
	
	public String digitsExpect(int nrOfDigits)
	{
		String sql = " CHECK (VALUE ~ '^\\d{" + nrOfDigits +"}$');";
		return sql;
		
	}
}
	
	

