package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.StudentList;

public class DetailDataDaoImpl implements DetailDataDao {
	public StudentList showDetail(int id) throws ClassNotFoundException, SQLException
	{
		java.sql.Connection con;
		String dbname="test";
		
		ArrayList arr=new ArrayList();
		System.out.println("I am In ListDaoImpl");
		String dbUrl="jdbc:mysql://localhost/";
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbUrl + dbname,"","");
		StringBuffer str = new StringBuffer();
		str.append("Select Id,Name,Class,Grade,Address,Comments from tb_student_data where id=?");
		java.sql.PreparedStatement pstmt = con.prepareStatement(str.toString());
		pstmt.setInt(1,id);
		ResultSet rs = pstmt.executeQuery();
		StudentList studentList = new StudentList();
		while(rs.next()){
			studentList.setId(rs.getInt(1));
			studentList.setName(rs.getString(2));
			studentList.setClass1(rs.getInt(3));
			studentList.setGrade(rs.getString(4));
			studentList.setAddress(rs.getString(5));
			studentList.setComment(rs.getString(6));
			arr.add(studentList);
		}
		return studentList;
	}
}
