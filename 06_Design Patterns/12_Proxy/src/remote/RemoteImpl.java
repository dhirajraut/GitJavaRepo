package remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteImpl extends UnicastRemoteObject implements IRemote {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RemoteImpl() throws RemoteException {
	}

	public String getResponse() throws RemoteException {
		return "Response From Server";
	}

	public static void main (String args[]) {
		try {
			IRemote service = new RemoteImpl();
			Naming.bind("ProxyPattern", service);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
