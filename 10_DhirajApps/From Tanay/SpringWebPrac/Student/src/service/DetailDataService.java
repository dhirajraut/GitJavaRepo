package service;

import java.sql.SQLException;

import model.StudentList;

public interface DetailDataService {
	StudentList showDetail(int id) throws ClassNotFoundException, SQLException;

}
