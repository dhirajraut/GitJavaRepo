package login;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

public interface Login extends EJBObject{
	public boolean checkLogin (String userId, String password) throws RemoteException;
}
