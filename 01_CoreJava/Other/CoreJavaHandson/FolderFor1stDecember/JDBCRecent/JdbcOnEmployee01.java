import java.sql.*;
class JdbcOnEmployee01
	{ public static void main(String [] argc)
		{ try {  
			// JDBC-ODBC Bridge
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
					   
		   Statement st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY , ResultSet.CONCUR_READ_ONLY);
		   ResultSet rs=st.executeQuery("select EMPNO, ENAME, SAL from EMP");

		   while(rs.next())
				{ int no=rs.getInt(1);
				   String nm=rs.getString("ENAME");
				   float sl = rs.getFloat("SAL");
				   System.out.println(no	+ "     "+nm+"     "+sl);
				 }
			rs.close();																													
			st.close();
			conn.close();																													
		}// End try block
		
			catch(SQLException e)
						{ System.out.println("Error :"+e); 
							e.printStackTrace();
						}
			catch(ClassNotFoundException e)
						{ System.out.println("Error :"+e); }
			} // End main
		} // End class
