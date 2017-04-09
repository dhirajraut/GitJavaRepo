package packdiscount;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface DiscountHomeL extends EJBLocalHome {
	public DiscountL create() throws CreateException;
}
