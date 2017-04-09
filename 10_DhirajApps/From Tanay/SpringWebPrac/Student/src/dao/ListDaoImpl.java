package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.StudentList;
public class ListDaoImpl implements ListDao {

	StudentList studentList;
	
	public ArrayList showList() throws ClassNotFoundException, SQLException {
		java.sql.Connection con;
		String dbname="test";
	
		ArrayList arr=new ArrayList();
		System.out.println("I am In ListDaoImpl");
		String dbUrl="jdbc:mysql://localhost/";
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbUrl + dbname,"","");
		StringBuffer str = new StringBuffer();
		str.append("Select Name,Id from tb_student_data ");
		java.sql.PreparedStatement pstmt = con.prepareStatement(str.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			StudentList studentList = new StudentList();
			studentList.setName(rs.getString(1));
			studentList.setId(rs.getInt(2));
			arr.add(studentList);
			System.out.println(rs.getString(1));
			System.out.println(rs.getInt(2));
			
		}
		for ( int i=0;i<arr.size();i++){
			StudentList s = (StudentList)arr.get(i);
			System.out.println(s.getName());
		}

		return arr;
	}
	public void setStudentList(StudentList studentList) {
		this.studentList = studentList;
	}

}