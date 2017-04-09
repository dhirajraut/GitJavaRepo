import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectionpool.*;

public class TestConnectionPool
	{	public static void main(String[] args)
			{	ConnectionPool cp = null;
				Connection con = null ;
				// Create new connection pool.
				try {
						cp = new ConnectionPool("jdbc:odbc:javaDSN", "sun.jdbc.odbc.JdbcOdbcDriver", "scott", "tiger", 10, 5);
						con = cp.getConnection();
					}
				catch(ClassNotFoundException e)
					{	System.err.println("Class not found.");	}
				catch(SQLException se)
					{	System.err.println("SQL Exception.");}

				// Get a connection
				try {	Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				   		ResultSet rs=st.executeQuery("select EMPNO, ENAME, SAL from EMP");
					
					    while(rs.next())
							{ int no=rs.getInt("EMPNO");
							   String nm=rs.getString("ENAME");
							   float sl = rs.getFloat("SAL");
							   System.out.println(no	+ "     "+nm+"     "+sl);
							 }
						rs.close();																													
						st.close();
						cp.returnConnection(con);
					}

				catch(SQLException e)
					{ System.out.println("Error :"+e); 
						e.printStackTrace();
					}				
			}
	}
