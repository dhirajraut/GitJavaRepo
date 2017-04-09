package service;

import dao.LoginDao;
import model.Login;

public class LoginServiceImpl implements LoginService{
	LoginDao loginDao;
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	public boolean checkLogin(Login l) {
		// TODO Auto-generated method stub
		return loginDao.checkLogin(l);
	}
	

}
