
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;



public class TestBlob {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	public TestBlob()
	{
		
			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				conn=DriverManager.getConnection("jdbc:odbc:mydsn");
				stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
   ResultSet.CONCUR_READ_ONLY);
				
				//code to insert BLOB into table
				PreparedStatement pstmt = conn.prepareStatement ("INSERT INTO t1 VALUES (?,?)");
				pstmt.setInt (1, 100);
				File fBlob = new File ( "c://word.doc" );
				FileInputStream is=null;
				try {
					is = new FileInputStream ( fBlob );
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pstmt.setBinaryStream (2, is, (int) fBlob.length() );
				pstmt.execute ();
				// code to retrieve BLOB from  table */
				ResultSet rs= stmt.executeQuery("SELECT * FROM t1"); 
				while(rs.next()) { 
				int val1 = rs.getInt(1); 
				InputStream val2 = rs.getBinaryStream(2); 
				System.out.println("val1"+val1);
				try {
					System.out.println("val2"+val2.read());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				} rs.close(); 


			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void main(String args[])
	{
		new TestBlob();
	}

}