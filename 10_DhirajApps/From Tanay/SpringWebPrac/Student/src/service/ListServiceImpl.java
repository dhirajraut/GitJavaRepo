package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ListDao;

public class ListServiceImpl implements ListService {
ListDao ListDao;
	public ArrayList showList() throws ClassNotFoundException, SQLException {
		
		return ListDao.showList();
	}
	public ListDao getListDao() {
		return ListDao;
	}
	public void setListDao(ListDao listDao) {
		ListDao = listDao;
	}

}
