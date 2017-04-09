package packdiscount;

import javax.ejb.EJBLocalObject;

public interface DiscountL extends EJBLocalObject {
	public float getDiscount(float amt, float percent);
}
