package login;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class LoginBean implements SessionBean {

	public void ejbActivate() throws EJBException, RemoteException {}

	public void ejbPassivate() throws EJBException, RemoteException {}

	public void ejbRemove() throws EJBException, RemoteException {}

	public void setSessionContext(SessionContext arg0) 
								throws EJBException, RemoteException {}

	public void ejbCreate () throws EJBException {}
	
	public boolean checkLogin (String userId, String password) throws EJBException{
		if ("dhiraj".equalsIgnoreCase(userId) && "raut".equalsIgnoreCase(password)){
			return true;
		}
		return false;
	}
}
