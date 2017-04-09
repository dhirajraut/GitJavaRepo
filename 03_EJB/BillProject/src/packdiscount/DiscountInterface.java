package packdiscount;

import java.rmi.RemoteException;

public interface DiscountInterface {

	float getDiscount(float amt, float percent) throws RemoteException;
}
