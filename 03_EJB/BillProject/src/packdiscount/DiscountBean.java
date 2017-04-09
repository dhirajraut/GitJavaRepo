package packdiscount;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class DiscountBean implements SessionBean, DiscountInterface {

	public void ejbActivate() throws EJBException{}

	public void ejbPassivate() throws EJBException{}

	public void ejbRemove() throws EJBException {}

	public void setSessionContext(SessionContext arg0) throws EJBException{}

	public float getDiscount(float amt, float percent) throws EJBException{
		return amt * percent / 100;
	}
	
	public void ejbCreate() throws EJBException{}
}
