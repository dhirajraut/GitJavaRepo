package service;

import java.sql.SQLException;

import dao.DetailDataDao;
import model.StudentList;

public class DetailDataServiceImpl implements DetailDataService {
	DetailDataDao detailDataDao;
	public StudentList showDetail(int id) throws ClassNotFoundException, SQLException
	{
		return detailDataDao.showDetail(id);
	}
	public void setDetailDataDao(DetailDataDao detailDataDao) {
		this.detailDataDao = detailDataDao;
	}
}
