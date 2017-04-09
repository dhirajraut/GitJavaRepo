package Module15.jdbc;
import java.sql.*;

class UsingPreparedStatement {
	public static void main(String[] args) {
		try{
			DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
			Connection conn = DriverManager.getConnection("jdbc:odbc:javadsn","scott","tiger");
						
			PreparedStatement pst = conn.prepareStatement("insert into emp (empno, ename, sal) values (?,?,?) ");

			pst.setInt(1,1005);
			pst.setString(2,"Rhodes");
			pst.setFloat(3,10000);
			int firstRecord = pst.executeUpdate();
					
		    pst = conn.prepareStatement("insert into emp (empno, ename, sal) values (?,?,?) ");
		    pst.setInt(1,1006);
		    pst.setString(2,"Rosy");
		    pst.setFloat(3,15000);
		    
		    int secondRecord = pst.executeUpdate();
		    		
		    Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery("select * from emp");
		    int bno;
		    String bname;
		    while(rs.next()){
		    	bno=rs.getInt(1);
		    	bname = rs.getString(2);
		    	System.out.println(bno+"\t"+bname);
		    }	
		}
		catch(SQLException sqe){
			sqe.printStackTrace();
		}
		
		
	}

}
