package login;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface LoginHome extends EJBHome {

	public Login create() throws RemoteException, CreateException;
}
