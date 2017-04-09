package Module15.jdbc;

import java.sql.*;

public class JdbcHelper {
	Connection con;
	ManageConnections mc;
	Statement st;
	JdbcHelper(){
		try{
			mc=new ManageConnections();
			con=mc.getConnection();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void printData(){
		ResultSet rs=null;
		try{
			st=con.createStatement();
			rs=st.executeQuery("select * from emp where sal>2000");
			while(rs.next()){
				int eno=rs.getInt("empno"); 
				String ename=rs.getString("ename"); 
				double sal=rs.getDouble("sal"); 
				System.out.println(eno+"\t"+ename+"\t"+sal); 
			}
			rs.close();
			st.close();
			mc.closeConnection();
		}
		catch(SQLException e ) { 
			e.printStackTrace(); 
		}
	}
	public void insertData(){
		try {
			st=con.createStatement();
		    st.executeUpdate("insert into emp (empno, ename, job, sal, deptno) values (112,"+"'jack'"+","+"'clerk'"+","+"10000,30) ");

			st.close (); 
		    con.close ();

		}
		catch(SQLException e ) { 
			e.printStackTrace(); 
		}
	}
			
	public static void main(String[] args) {
		JdbcHelper demo=new JdbcHelper();
	  //  demo.printData();
	    demo.insertData();
	}
}
