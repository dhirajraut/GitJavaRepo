import java.sql.* ;

public class DBConnector {

	 public static void main( String args[] ) {
	  try
	     {
	      // Load the database driver
	      Class.forName( "oracle.jdbc.driver.OracleDriver" ) ;

	      // Get a connection to the database
	      String url = "jdbc:oracle:thin:@INFPW03699.PWIODC.LNTINFOTECH.COM:1521:DhirajDB";
	      Connection conn = DriverManager.getConnection( url, "dhiraj", "dhiraj" ) ;

	      // Print all warnings
	      for( SQLWarning warn = conn.getWarnings(); warn != null; warn = warn.getNextWarning() )
	         {
	          System.out.println( "SQL Warning:" ) ;
	          System.out.println( "State  : " + warn.getSQLState()  ) ;
	          System.out.println( "Message: " + warn.getMessage()   ) ;
	          System.out.println( "Error  : " + warn.getErrorCode() ) ;
	         }

	      // Get a statement from the connection
	      Statement stmt = conn.createStatement() ;

	      // Execute the query
	      ResultSet rs = stmt.executeQuery( "SELECT FIRSTNAME FROM EMPLOYEES" ) ;

	      // Loop through the result set
	      while( rs.next() )
	         System.out.println( rs.getString(1) ) ;

	      // Close the result set, statement and the connection
	      rs.close() ;
	      stmt.close() ;
	      conn.close() ;
	     }
	  catch( SQLException se )
	     {
	      System.out.println( "SQL Exception:" ) ;

	      // Loop through the SQL Exceptions
	      while( se != null )
	         {
	          System.out.println( "State  : " + se.getSQLState()  ) ;
	          System.out.println( "Message: " + se.getMessage()   ) ;
	          System.out.println( "Error  : " + se.getErrorCode() ) ;

	          se = se.getNextException() ;
	         }
	     }
	  catch( Exception e )
	     {
	      System.out.println( e ) ;
	     }
	 }
}
