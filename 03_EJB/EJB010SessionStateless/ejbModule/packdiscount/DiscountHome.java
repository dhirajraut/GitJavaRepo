package packdiscount;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface DiscountHome extends EJBHome {
	public Discount create()
		throws CreateException, RemoteException;
}