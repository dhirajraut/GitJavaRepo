package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Login;

public class LoginDaoImpl implements LoginDao {

	public boolean checkLogin(Login l) {
		
		
		java.sql.Connection con;
		String dbname="test";
	boolean flag= false;
	try {
			
		System.out.println("I am In Dao");
		String dbUrl="jdbc:mysql://localhost/";
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbUrl + dbname,"","");
		StringBuffer str = new StringBuffer();
		str.append("Select * from tb_login where name = ?");       						
		java.sql.PreparedStatement pstmt = con.prepareStatement(str.toString());
		pstmt.setString(1, l.getName());
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			if((rs.getString(1).equals(l.getName()))&& (rs.getString(2).equals(l.getPassword()))){
				flag=true;
				break;
			}	
			if(rs.getInt(3)<=3){
				String s = "Update tb_login set attempts = ? where name = ? ";
				PreparedStatement ps = con.prepareStatement(s);
				ps.setInt(1,rs.getInt(3)+1);
				ps.setString(2,l.getName());
				ps.executeUpdate();
			}
		}

		
	}
	catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return flag;
	
	}
}
