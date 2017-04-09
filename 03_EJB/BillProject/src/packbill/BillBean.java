package packbill;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import packdiscount.DiscountHomeL;
import packdiscount.DiscountL;

public class BillBean implements SessionBean, BillInterface {
	private int billNo;
	private String custNm;
	private ArrayList al;
	
	public void ejbActivate() throws EJBException{}

	public void ejbPassivate() throws EJBException{}

	public void ejbRemove() throws EJBException{}

	public void setSessionContext(SessionContext arg0) throws EJBException {}
	
	public void ejbCreate(String custNm )throws EJBException {
		billNo = getNextBillNo();
		this.custNm = custNm;
		this.al = new ArrayList();
	}

	private static int nextBillNo = 1;
	
	private int getNextBillNo() throws EJBException {
		return nextBillNo++;
	}
	
	public void addNewItem(int itemNo, int itemQty) throws EJBException {
		ItemDetails id = new ItemDetails(itemNo, itemQty);
		al.add(id);
	}

	public Collection listItems() throws EJBException {
		
		return al;
	}

	public float printBill() throws EJBException {
		
		try {
			Context ic = new InitialContext();
			DiscountHomeL dhl = (DiscountHomeL) ic.lookup("DiscountBeanL");
			DiscountL dl = dhl.create();
			
			float discount = dl.getDiscount(2000, 10);
			return 2000-discount;
		} catch (NamingException e) {
			throw new EJBException("Naming Exception", e);
		} catch (CreateException e) {
			throw new EJBException("Create Exception", e);
		}	
	}
}
