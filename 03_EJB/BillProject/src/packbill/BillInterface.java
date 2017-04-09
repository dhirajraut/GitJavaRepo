package packbill;

import java.rmi.RemoteException;
import java.util.Collection;

public interface BillInterface {
	public void addNewItem(int itemNo, int itemQty) throws RemoteException;
	public Collection listItems() throws RemoteException;
	public float printBill() throws RemoteException;
}
