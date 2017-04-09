package Module15.jdbc;

import java.sql.*;

public class TestDataBaseMetaData {
	static Connection conn=null;
	public static void main(String[]args){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection("jdbc:odbc:javadsn","scott","tiger");
			TestDataBaseMetaData rsmd = new TestDataBaseMetaData();
			rsmd.databaseMetaDataDemo();
		}
		catch(ClassNotFoundException cfe){
			cfe.printStackTrace();
		}
		catch(SQLException sqe){
			sqe.printStackTrace();
		}
	}
	public void databaseMetaDataDemo(){
		try{
			DatabaseMetaData dmd=conn.getMetaData();
		    System.out.println("The Driver Name : "+dmd.getDriverName());
		    System.out.println("Column Length : "+dmd.getMaxColumnNameLength());
		    System.out.println("User Name : "+dmd.getUserName());
		    System.out.println("Database Product Name : "+dmd.getDatabaseProductName());
		    System.out.println("Row Size : "+dmd.getMaxRowSize());
		    System.out.println("Support StoredProcedure : "+dmd.supportsStoredProcedures());
		}
		catch(SQLException sqe){
			sqe.printStackTrace();
		}
	}
}
