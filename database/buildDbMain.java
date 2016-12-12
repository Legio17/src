package database;

import java.sql.Connection;
import java.sql.SQLException;


import database.connection.connect;
import database.methods.CreateDomainEx;
import database.methods.CreateTableEx;

public class buildDbMain
{
   public static void main(String args[]) throws ClassNotFoundException, SQLException
   {
	  Connection con = null;
	  con = connect.PostgreSQLJDBC("SEP2_data", "jens");
	  
	  CreateDomainEx exD = new CreateDomainEx(con); 
	  CreateTableEx exT = new CreateTableEx(con); 

	  boolean ignore = false;
	  
	  //create domains
	  if (!ignore)createDomains(exD);
	  
	  //create tables
	  if (!ignore)createTables(exT);
	

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
