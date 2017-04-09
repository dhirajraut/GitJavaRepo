package Module15.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeptInsertUpdate05{
	Connection con;
	Statement st;
	ResultSet rs;
		
	public DeptInsertUpdate05(){
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}
		catch(Exception e){
			System.out.println("error in connection");
		}
	}
		
	public int getMaxDept() throws SQLException{
		rs = st.executeQuery("SELECT MAX(DEPTNO) FROM DEPT");
		int count;
		if (rs != null){
			rs.next();
			count = rs.getInt(1);
			rs.close();
		}
		else
			count = 0;
		return count;
	}
		
	public void addNew(String deptnm, String deptloc) throws SQLException{
		int next = getMaxDept()+10;
		rs = st.executeQuery("SELECT DEPTNO, DNAME, LOC FROM DEPT");
		rs.moveToInsertRow();
		rs.updateInt(1, next);
		rs.updateString(2,deptnm);
		rs.updateString(3, deptloc);
		rs.insertRow();
		rs.close();
	}
		
	public void deleteOld(int deptno) throws SQLException{
		rs = st.executeQuery("select deptno, dname, loc from dept where deptno="+deptno);
		rs.next();
		rs.deleteRow();
		rs.close();
	}
		
	// While using updating on a row, the columns of the table must be specified by name.
	// Specifying them by '*' will throw SQLException
	public void updateName(int deptno, String deptnm) throws SQLException{
		String query = "select dname from dept where deptno="+deptno;
		System.out.println(query);
		rs = st.executeQuery(query);
		rs.next();
		rs.updateString("DNAME", deptnm);
		rs.updateRow();
	}
		
	public void printData() throws SQLException{
		rs = st.executeQuery("select * from dept");
		while (rs.next()){
			int no = rs.getInt(1);
			String nm = rs.getString(2);
			String lc = rs.getString(3);
			System.out.println("No:"+no+" Name:"+nm+"  L:"+lc);
		}
		rs.close();
	}
		
	public void closeConn() throws SQLException{
		rs.close();
		st.close();
		con.close();
	}
		
	public static void main(String [] argv) throws SQLException, IOException{
		DeptInsertUpdate05 diu = new DeptInsertUpdate05();
		try {
			System.out.println("Existing data...");
			diu.printData();
			
			System.out.println("\nUpdating...");
			diu.updateName(40, "FINANCE");
			diu.printData();
			
			System.out.println("\nAdding new Record....");
			diu.addNew("TESTING","TOKYO");
			diu.printData();
			
			System.out.println("\nDeletig record....");
			diu.deleteOld(50);
			diu.printData();
			
			diu.closeConn();
		}
		catch (SQLException e){
			diu.deleteOld(50);
			System.out.println("Error in Updating.  Record deleted. Error:"+e.getMessage());
		}
		
	}
}

