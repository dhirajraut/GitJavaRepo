package packdiscount;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class DiscountBean implements SessionBean {
	SessionContext sc;
	
	public void ejbActivate() throws EJBException{}

	public void ejbPassivate() throws EJBException{}

	public void ejbRemove() throws EJBException{}

	public void setSessionContext(SessionContext arg0) throws EJBException{
		this.sc = arg0;
	}
	public void ejbCreate() throws EJBException{}
	
	public float getDiscount(float amt, float percent) throws EJBException {
		return amt * percent/100;
	}
}
