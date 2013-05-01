package DBLayer;

import java.sql.*;

public class DBConnection1 {
	//constants used to get access to the database
			//SQL Server
		private static final String driver = "jdbc:sqlserver://balder.ucn.dk";
		
		//jdbc:sqlserver://balder.ucn.dk:1067
	    private static final String  databaseName = ";databaseName=dmae0912_7";
	//jdbc:sqlserver://balder.ucn.dk;user=dmae0912_7;password=IsAllowed;
	    
	  //SQL Server
	      private static String  userName = "; user=dmae0912_7";
	      private static String password = ";password=IsAllowed";
	     
	    
	      private DatabaseMetaData dma;
	      private static Connection con;
	      // an instance of the class is generetated
	      private static DBConnection1  instance = null;
	      
	      private DBConnection1()
	      {
	    	  String url = driver + databaseName + userName + password;
	    	  
	    	  try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (Exception e) {
				System.out.println("Can not find the driver");
	            System.out.println(e.getMessage());
			}
	    	  
	    	  try{
	              //connection to the database
	             
	              con = DriverManager.getConnection(url);
	              //set autocommit
	              con.setAutoCommit(true);
	              dma = con.getMetaData(); // get meta data
	              System.out.println("Connection to " + dma.getURL());
	              System.out.println("Driver " + dma.getDriverName());
	              System.out.println("Database product name " + dma.getDatabaseProductName());
	    	  }
	    	  catch(Exception e){

	              System.out.println("Problems with the connection to the database");
	              System.out.println(e.getMessage());
	              System.out.println(url);
	          }
	      }//end of constructor
	      
	    //closeDb: closes the connection to the database
	      public static void closeConnection()
	      {
	         	try{
	              con.close();
	              System.out.println("The connection is closed");
	          }
	           catch (Exception e){
	              System.out.println("Error trying to close the database " +  e.getMessage());
	           }
	      }//end closeDB
	      
	    //getDBcon
	      public  Connection getDBcon()
	      {
	         return con;
	      }
	      
	      //this method is used to get the instance of the connection
	      public static DBConnection1 getInstance()
	      {
	          if (instance == null)
	          {
	            instance = new DBConnection1();
	          }
	          return instance;
	      }

	      public static void startTransaction()
	      { try{
	          con.setAutoCommit(false);
	          }
	        catch(Exception e){
	          System.out.println("fail start transaction");
	          System.out.println(e.getMessage());
	        }
	      }
	      public static void commitTransaction()
	      { try{
	          con.setAutoCommit(true);
	          }
	        catch(Exception e){
	          System.out.println("fail commit transaction");
	          System.out.println(e.getMessage());
	        }
	      }
	      public static void rollbackTransaction()
	      { try{
	          con.rollback();
	          con.setAutoCommit(true);
	          }
	        catch(Exception e){
	          System.out.println("fail rollback transaction");
	          System.out.println(e.getMessage());
	        }
	      }

}
