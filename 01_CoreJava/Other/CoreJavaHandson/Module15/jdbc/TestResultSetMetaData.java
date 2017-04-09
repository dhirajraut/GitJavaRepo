package Module15.jdbc;

import java.sql.*;

public class TestResultSetMetaData {
	static Connection conn=null;
	public static void main(String[]args){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection("jdbc:odbc:javadsn","scott","tiger");
			TestResultSetMetaData rsmd = new TestResultSetMetaData();
			rsmd.resultSetMetadataDemo();
		}
		catch(ClassNotFoundException cfe){
			cfe.printStackTrace();
		}
		catch(SQLException sqe){
			sqe.printStackTrace();
		}
	}
	public void resultSetMetadataDemo() {
		try{
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select * from emp");
			ResultSetMetaData rsmd=rs.getMetaData();
			System.out.println("number of columns :  "+rsmd.getColumnCount());
			System.out.print(rsmd.getColumnLabel(1));
			System.out.println("-->"+rsmd.getColumnTypeName(1));
			System.out.print(rsmd.getColumnLabel(2));
			System.out.println("-->"+rsmd.getColumnTypeName(2));
		}
		catch(SQLException sqe){
			sqe.printStackTrace();
		}
	}
}

