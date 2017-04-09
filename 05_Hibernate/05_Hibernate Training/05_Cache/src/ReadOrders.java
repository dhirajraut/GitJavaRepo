import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Session;

import entities.OrderDetails;


public class ReadOrders {
	public static void main(String args[]) {
		Session session = Util.configureHibernate();
		Criteria cr = session.createCriteria(OrderDetails.class);
		cr.setCacheable(true);
		Iterator itr = cr.list().iterator();
		while (itr.hasNext()) {
			OrderDetails od = (OrderDetails) itr.next();
			System.out.println(od);
		}
	}
}
