import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;


import login.Login;
import login.LoginHome;

public class LoginEJBClient {

	public static void main(String[] args) {
		
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY,
									"weblogic.jndi.WLInitialContextFactory");
		properties.put(Context.PROVIDER_URL,
									"t3://localhost:7001");
		
		try {
			InitialContext ic = new InitialContext(properties);
			Object object = ic.lookup("LoginJNDI");
			LoginHome loginHome = 
					(LoginHome)PortableRemoteObject.narrow(object, LoginHome.class);
			Login loginProxy = loginHome.create();
			
			boolean success = loginProxy.checkLogin("Dhiraj", "Raaut");
			System.out.println("Success = " + success);
			
		}
		catch (NamingException e) {
			e.printStackTrace();
		}
		catch (CreateException e) {
			e.printStackTrace();
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
