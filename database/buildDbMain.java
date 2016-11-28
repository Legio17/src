package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

import database.connection.connect;
import database.methods.CreateDomainEx;
import database.methods.CreateTableEx;
import database.methods.database_methods;



public class buildDbMain
{
   public static void main(String args[]) throws ClassNotFoundException, SQLException
   {
	  Connection con = null;
	  con = connect.PostgreSQLJDBC("SEP2_data", "Postgres");
	  
	  CreateDomainEx exD = new CreateDomainEx(con); 
	  CreateTableEx exT = new CreateTableEx(con); 
	  
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

   }
   
   public static void createDomains(CreateDomainEx exD ) throws SQLException
   {

	   exD.addNewDomain("TYPE","VARCHAR(3)","3X3",new String[]{"3X3","5X5"});
	   exD.addNewDomain("RESULT", "VARCHAR","null", new String[]{"won","lost","tie"});	   
   }
   
   public static void createTables(CreateTableEx exT ) throws SQLException
   {
	  // exT.createTable("PLAYER_INFO", new String[][]{{"login","VARCHAR NOT NULL"},{"model","VARCHAR NOT NULL"}}, new String[]{"pk:(login)"},false,false);
	 //  exT.createTable("SCORE_INFO",  new String[][]{{"ID", "INTEGER"},{"login","VARCHAR NOT NULL"},{"rank","INTEGER NOT NULL"},{"winloss_ratio","REAL"},{"wins","INTEGER"},{"losses","INTEGER"}}, new String[]{"pk:(ID)","fk:(login):rf:PLAYER_INFO(login)"}, true, true);
	  // exT.createTable("RANK_INFO", new String[][]{{"ID", "INTEGER"},{"login","VARCHAR NOT NULL"},{"rank","INTEGER NOT NULL"}}, new String[]{"pk:(ID)","fk:(login):rf:PLAYER_INFO(login)"}, true,true);
	   exT.createTable("GAME_HISTORY", new String[][]{{"ID", "INTEGER"},{"player1","VARCHAR NOT NULL"},{"player2","VARCHAR NOT NULL"},{"date","DATE"},{"type","TYPE"}, {"result_player1","RESULT"}}, new String[]{"pk:(ID)"}, false, false);
   }
   
//   public static void addSomeEmployee(Connection con) throws SQLException
//   {
//	   database_methods.addEmployee(con, new String[]{"106", "Twilight", "Sparkle", "1234567890", "12-dec-1992", "12-dec-2016", "1000", "Fredesgade 11", "Horsens", "8700", "metalTS@gmail.com", "F","0", "PT", "RECP"});
//	   database_methods.addEmployee(con, new String[]{"107", "Sunset", "Shimmer", "1599633215", "11-dec-1990", "02-nov-2016", "1000", "Filly Street 11", "Fillydephlia", "9620", "SunnySun62@viking.dk", "F","0", "PT", "MNGR"});
//	   
//   } 
//   
//   public static void makeSomeUpdate(Connection con) throws SQLException
//   {
//	   database_methods.updateAtribute(con, "employee", "fname", "Twilight", "Jessica");
//	   database_methods.updateAtribute(con, "booking", "booking_id", "amount_to_pay", "0", "3000");
//	   
//   }
   
}
