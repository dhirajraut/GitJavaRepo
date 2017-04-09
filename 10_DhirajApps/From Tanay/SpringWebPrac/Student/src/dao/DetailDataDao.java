package dao;

import java.sql.SQLException;

import model.StudentList;

public interface DetailDataDao {
	StudentList showDetail(int id) throws ClassNotFoundException, SQLException;

}
