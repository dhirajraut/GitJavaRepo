import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import packdiscount.Discount;
import packdiscount.DiscountHome;

public class TestDiscountEJB {

	public static void main(String[] args) {
		java.util.Properties h = new java.util.Properties();
		h.put(Context.INITIAL_CONTEXT_FACTORY,
		"weblogic.jndi.WLInitialContextFactory");
		h.put(Context.PROVIDER_URL, "t3://localhost:7001");
		
			try {
				Context ic = new InitialContext(h);

				 // Lookup of home object and get home stub.
				Object o = ic.lookup("DiscountEngine");
				DiscountHome bhr = (DiscountHome) PortableRemoteObject.narrow(o, DiscountHome.class);
				Discount disc = bhr.create();

				float f1 = disc.getDiscount(10, 2000.0f);
				float f2 = disc.getDiscount(15, 6000f);
				System.out.println("F1:"+f1+" F2"+f2);
				
			} catch (ClassCastException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (CreateException e) {
				e.printStackTrace();
			}
	}
}