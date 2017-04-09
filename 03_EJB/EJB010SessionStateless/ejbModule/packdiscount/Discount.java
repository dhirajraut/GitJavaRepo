package packdiscount;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface Discount extends EJBObject {
	public float getDiscount(float amt, float percent)
		throws RemoteException;
}
