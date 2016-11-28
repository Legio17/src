package database.connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class connect
{
   public static Connection PostgreSQLJDBC(String schema, String password) throws ClassNotFoundException, SQLException {

        
      Connection c = null;
      
      c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", password);
      c.close();
    		
         try {
            Class.forName("org.postgresql.Driver");
            
         } catch (ClassNotFoundException e){ 
            
            Class.forName("postgresql-9.4.1212.jre6");
         }
         
         try{ 
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", password); 
            c.setSchema(schema);//name of your schema

         } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
         }
         
         System.out.println("Opened database successfully");
         return c;
         
         
   }
}
