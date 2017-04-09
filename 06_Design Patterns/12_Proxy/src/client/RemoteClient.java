package client;

import java.rmi.Naming;

import remote.IRemote;
import remote.RemoteImpl;

public class RemoteClient {
	public static void main(String args[]) {
		try {
			IRemote service = (RemoteImpl)Naming.lookup("rmi://127.0.0.1/ProxyPattern");
			System.out.println("We have received " + service.getResponse());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
