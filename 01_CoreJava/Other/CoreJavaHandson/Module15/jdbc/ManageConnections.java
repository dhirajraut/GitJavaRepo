package Module15.jdbc;

import java.sql.*;

public class ManageConnections {
	private Connection conn;
	
	public Connection getConnection() throws SQLException,ClassNotFoundException{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		conn=DriverManager.getConnection("jdbc:odbc:javadsn", "scott", "tiger");
		System.out.println("Connection done");				
		return conn;
	}

	public void closeConnection() throws SQLException{
		if (conn!=null)			
			conn.close();
			
	}

}
