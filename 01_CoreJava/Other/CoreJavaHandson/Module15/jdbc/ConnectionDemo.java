package Module15.jdbc;

import java.sql.*;

public class ConnectionDemo{
	public static void main(String[] args) {
   		Connection con = null;
  		ManageConnections mc = null;
  		Statement st=null;
   		ResultSet rs=null;
   		try{
   			mc=new ManageConnections();
   			con=mc.getConnection();
   			st=con.createStatement();
   			rs=st.executeQuery("select * from emp");
   			while(rs.next()){
       			int eno=rs.getInt(1);
       			String name = rs.getString("ename");
       			System.out.println(eno+"\t"+name);
   			}
  		}
   		catch(SQLException sqe){
   			sqe.printStackTrace();
   		}
   		catch(ClassNotFoundException cfe){
   			cfe.printStackTrace();
   		}
	}

}
