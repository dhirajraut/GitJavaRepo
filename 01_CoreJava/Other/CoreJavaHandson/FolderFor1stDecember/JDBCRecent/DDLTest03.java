import java.io.IOException;
import java.sql.*;

import ioservices.MyIOService;

public class  DDLTest03 {
	public static void main (String[] args) throws IOException
		{	
			try {	// JDBC-ODBC Bridge
					String bridgeDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
					String bridgeUrl = "jdbc:odbc:javaDSN";
					
					// OCI/Thin Driver
					String ociThinDriver = "oracle.jdbc.driver.OracleDriver";
					 
					// OCI url
					String ociUrl = "jdbc:oracle:oci:@CHANDRA";
					
					// Thin url
					String thinUrl = "jdbc:oracle:thin:@rac940:1521:CHANDRA";
					
					Class.forName(bridgeDriver);
					Connection conn = DriverManager.getConnection(bridgeUrl,"scott","tiger");

					Statement st = conn.createStatement();
					System.out.println("Enter table name :");
					String tableName = MyIOService.getString();

					String sql = "create table "+tableName+"(first number(5), second varchar2(20))";
					st.executeUpdate(sql);
					System.out.println ("Table created....");
					st.close(); conn.close(); 
				}
			catch (SQLException e)
				{	e.printStackTrace();}
			catch (IOException e)
				{	e.printStackTrace();	}
			catch (ClassNotFoundException e)
				{	e.printStackTrace();	}
			}
}
