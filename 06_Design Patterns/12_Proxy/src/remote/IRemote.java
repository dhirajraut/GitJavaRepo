package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemote extends Remote {
	public String getResponse() throws RemoteException;
}
