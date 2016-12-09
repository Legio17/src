package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

import database.connection.connect;
import database.methods.CreateDomainEx;
import database.methods.CreateTableEx;
import database.methods.InsertExecutor;
import database.methods.database_methods;



public class buildDbMain
{
   public static void main(String args[]) throws ClassNotFoundException, SQLException
   {
	  Connection con = null;
	  con = connect.PostgreSQLJDBC("SEP2_data", "Postgres");
	  
	  CreateDomainEx exD = new CreateDomainEx(con); 
	  CreateTableEx exT = new CreateTableEx(con); 
	  InsertExecutor insertEx = new InsertExecutor(con);
	  
	  //JavaInputMethod.addEmployee(con); // create new employee by entering data from keyboard
	  boolean ignore = false;
	  
	  //create domains
	  if (ignore)createDomains(exD);
	  
	  //create tables
	  if (!ignore)createTables(exT);
	  
//	  //add branch
//	  if (!ignore)addSomeBranch(con);
//	  
//	  //add update some tables and atributes
//	  if (!ignore)makeSomeUpdate(con);
//	  
//	  //database_methods.dropTable(con, "parttime");
//	  //database_methods.deleteFromTable(con, "house", "houseName", "Solgaarden");
//	  //database_methods.deleteFromTable(con, "customer","fname", "bob");
//	  //database_methods.updateBookingPrice(con, 0);
//	  database_methods.updateAllBookingPrices(con);
	  
	
	  
//	  insertEx.insertPlayer("LEGIO");
//	  insertEx.insertPlayer("Mio");
//	  insertEx.insertPlayer("Iulia");
//	  insertEx.insertPlayer("Signe");
//	  insertEx.insertPlayer("Filip");
//	  insertEx.insertToGameHistory("Mio", "LEGIO", "15X15", "2016-11-26", "lost");
//	  insertEx.insertToGameHistory("Adam", "Mio", "15X15", "2016-11-26", "lost");
//	  insertEx.updateRanks();

   }
   
   public static void createDomains(CreateDomainEx exD ) throws SQLException
   {
	   exD.addNewDomain("TYPE","VARCHAR(5)","3X3",new String[]{"3X3","15X15"});
	   exD.addNewDomain("RESULT", "VARCHAR","null", new String[]{"won","lost","tie"});	   
   }
   
   public static void createTables(CreateTableEx exT ) throws SQLException
   {
	   exT.createTable("PLAYER_INFO", new String[][]{{"login","VARCHAR NOT NULL"}}, new String[]{"pk:(login)"},false,false);
	   exT.createTable("SCORE_INFO",  new String[][]{{"login","VARCHAR NOT NULL"},{"rank","INTEGER NOT NULL"},{"winloss_ratio","REAL"},{"wins","INTEGER"},{"losses","INTEGER"}}, new String[]{"pk:(login,rank)","fk:(login):rf:PLAYER_INFO(login)"}, true, true);
	   
   }
   
   
   
}
