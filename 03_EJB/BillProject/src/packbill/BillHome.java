package packbill;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface BillHome extends EJBHome {
	public Bill create(String custNm )throws CreateException, RemoteException;
}
