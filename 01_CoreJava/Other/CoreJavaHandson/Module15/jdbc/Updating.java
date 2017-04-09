package Module15.jdbc;

import java.io.IOException;
import java.sql.*;
import Module13.ioservices.*;

public class Updating {
	static Connection conn=null;
	public static void main(String[]args){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection("jdbc:odbc:javadsn","scott","tiger");
			Updating up = new Updating();
			up.updateData();
		}
		catch(ClassNotFoundException cfe){
			cfe.printStackTrace();
		}
		catch(SQLException sqe){
			sqe.printStackTrace();
		}
	}
	public void updateData(){
		try{
		   PreparedStatement pst=conn.prepareStatement("update emp set sal=? where empno=?");
		   System.out.println("Do you want to update data[y|n]?");
		   char  ch=MyIOService.getChar();
		   while(ch=='y'){
		       System.out.println("Enter empno:");     
		       int empno=MyIOService.getInt();
		       System.out.println("Enter employee's salary:");  
		       double sal=MyIOService.getDouble();
		       pst.setDouble(1,sal);
		       pst.setInt(2,empno);
		       pst.executeUpdate();
		       System.out.println("Do you want to continue[y|n]?");
		       ch=MyIOService.getChar();
		   }
		}
		catch(SQLException sqe){
			sqe.printStackTrace();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
